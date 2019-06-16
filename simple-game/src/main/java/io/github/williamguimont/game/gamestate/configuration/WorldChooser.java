package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.World;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.utils.State;

import java.util.Scanner;

public class WorldChooser extends BaseGameState {

    private State nextState;

    public WorldChooser(Game game, State nextState) {
        super(game);
        this.nextState = nextState;
    }

    @Override
    public void execute() {
        System.out.println("Enter the size of the world");
        System.out.println("(Between 5 and 20)");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int size = scanner.nextInt();

            if (size < 5 || size > 20) {
                setNextState(new WorldChooser(getGame(), nextState));
                return;
            }

            System.out.println("Enter the type of the world");
            System.out.println("1. Grassland\n2. Street\n3. Mountain");
            int type = scanner.nextInt();

            if (type < 1 || type > 3) {
                setNextState(new WorldChooser(getGame(), nextState));
                return;
            }

            World.Type worldType = getTypeFromNumber(type);
            getGame().setWorld(new World(size, worldType));
            setNextState(nextState);
        } else {
            setNextState(new WorldChooser(getGame(), nextState));
        }
    }

    private World.Type getTypeFromNumber(int type) {
        switch (type) {
            case 1:
                return World.Type.Grassland;
            case 2:
                return World.Type.Street;
            case 3:
                return World.Type.Mountain;
            default:
                return null;
        }
    }
}