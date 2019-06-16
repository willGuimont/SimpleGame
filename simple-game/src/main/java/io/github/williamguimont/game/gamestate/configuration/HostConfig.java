package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.game.player.NetworkPlayer;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.game.player.RealPlayer;
import io.github.williamguimont.network.NetworkStream;
import io.github.williamguimont.network.Server;
import io.github.williamguimont.utils.Serializator;

import java.util.Scanner;

public class HostConfig extends BaseGameState {
    public HostConfig(Game game) {
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
            RealPlayer p1 = new RealPlayer(c1);
            game.setPlayer1(p1);
            try {
                System.out.println("Waiting for other player to join");
                Server server = new Server(port);
                server.getData(); // wait for the other to be ready

                // C1
                String c1Serialized = Serializator.serializeToString(c1);
                server.sendData(c1Serialized);

                // C2
                String serializedCharacter = server.getData();
                Character c2 = (Character) Serializator.loadFromString(serializedCharacter);
                Player p2 = new NetworkPlayer(c2, server);
                game.setPlayer2(p2);

                System.out.println(c2.getName() + " has joined !");

                setNextState(new WorldChooser(game, new Host(game, server)));
            } catch (NetworkStream.NetworkException | Serializator.SerializationException e) {
                setNextState(new MainMenu(getGame()));
            }
        } else {
            setNextState(new HostConfig(getGame()));
        }
    }
}
