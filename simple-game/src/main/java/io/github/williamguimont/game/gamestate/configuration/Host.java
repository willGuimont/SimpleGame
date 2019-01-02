package io.github.williamguimont.game.gamestate.configuration;

import java.util.Scanner;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.World;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.game.player.NetworkPlayer;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.game.player.RealPlayer;
import io.github.williamguimont.network.Server;
import io.github.williamguimont.network.NetworkStream.NetworkException;
import io.github.williamguimont.utils.Serializator;
import io.github.williamguimont.utils.Serializator.SerializationException;

public class Host extends BaseGameState {

    public static final int DEFAULT_WORLD_SIZE = 5;

    public Host(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are about to host a game");
        System.out.println("Wich port should be used?");
        if (scanner.hasNextInt()) {
            int port = scanner.nextInt();
            Game game = getGame();

            Character c1 = CharacterChoser.choseCharacter();
            Player p1 = new RealPlayer(c1);
            game.setPlayer1(p1);

            System.out.println("Waiting for the other player to join...");

            try {
                Server server = new Server(port);
                System.out.println("Other player has joined");
                server.getData(); // wait for the other to be ready

                // C1
                String c1Serialized = Serializator.serializeToString(c1);
                server.sendData(c1Serialized);

                // C2
                String serializedCharacter = server.getData();
                Character c2 = (Character) Serializator.loadFromString(serializedCharacter);
                Player p2 = new NetworkPlayer(c2, server);
                game.setPlayer2(p2);

                // World size
                Integer worldSize = DEFAULT_WORLD_SIZE; // TODO?
                server.sendData(worldSize.toString());
                game.setWorld(new World(worldSize));

                setNextState(new ExitConfig(game));
            } catch (NetworkException | SerializationException e) {
                setNextState(new MainMenu(getGame()));
                return;
            }
        } else {
            setNextState(new Host(getGame()));
        }
    }
}