package io.github.williamguimont.game.gamestate.configuration;

import java.util.Scanner;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.utils.State;

public class Network extends BaseGameState {

    public Network(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Network menu");
        System.out.println("1. Host a game");
        System.out.println("2. Join a game");
        System.out.println("3. Back to main menu");

        Scanner scanner = new Scanner(System.in);
        State next = null;
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                next = new Host(getGame());
                break;
            case 2:
                next = new Join(getGame());
                break;
            case 3:
                next = new MainMenu(getGame());
                break;
            default:
                System.out.println("Input error ! Please enter a number between 1 and 3");
                next = new MainMenu(getGame());
                break;
            }
        } else {
            System.out.println("Input error ! Please enter a number");
            next = new Network(getGame());
        }
        setNextState(next);
    }
}