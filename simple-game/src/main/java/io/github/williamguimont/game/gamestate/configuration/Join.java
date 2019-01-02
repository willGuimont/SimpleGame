package io.github.williamguimont.game.gamestate.configuration;

import java.util.Scanner;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.World;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.game.player.NetworkPlayer;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.game.player.RealPlayer;
import io.github.williamguimont.network.Client;
import io.github.williamguimont.network.NetworkStream.NetworkException;
import io.github.williamguimont.utils.Serializator;
import io.github.williamguimont.utils.Serializator.SerializationException;

public class Join extends BaseGameState {

    public Join(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You are about to host a game");
        System.out.println("Enter the ip of the host");

        String ip = scanner.nextLine();

        System.out.println("Enter the port to use");

        if (scanner.hasNextInt()) {
            int port = scanner.nextInt();
            Game game = getGame();

            try {
                Client client = new Client(ip, port);
                client.sendData("ready");
                // C1
                String c1Serialized = client.getData();
                Character c1 = (Character) Serializator.loadFromString(c1Serialized);
                Player p1 = new NetworkPlayer(c1, client);
                game.setPlayer1(p1);

                // C2
                Character c2 = CharacterChoser.choseCharacter();
                String c2Serialized = Serializator.serializeToString(c2);
                client.sendData(c2Serialized);
                Player p2 = new RealPlayer(c2);
                game.setPlayer2(p2);

                // World
                String worldSizeString = client.getData();
                int worldSize = Integer.decode(worldSizeString);
                World world = new World(worldSize);
                game.setWorld(world);

                setNextState(new ExitConfig(game));
            } catch (NetworkException | SerializationException e) {
                setNextState(new MainMenu(game));
                return;
            }

        } else {
            setNextState(new Join(getGame()));
        }
    }
}