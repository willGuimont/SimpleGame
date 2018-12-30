package io.github.williamguimont.game.gamestate.configuration;

import java.util.Scanner;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.World;
import io.github.williamguimont.game.gamestate.BaseGameState;

public class WorldChoser extends BaseGameState {

    public WorldChoser(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        // TODO grassland
        // street
        // mountain
        // etc
        System.out.println("Enter the size of the world");
        System.out.println("(15 is recommended)");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();
            getGame().setWorld(new World(size));
            setNextState(new ExitConfig(getGame()));
        } else {
            setNextState(new WorldChoser(getGame()));
        }
    }
}