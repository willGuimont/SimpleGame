package io.github.williamguimont.game.gamestate.configuration;

import java.util.Scanner;

import io.github.williamguimont.game.Game.GameConfig;
import io.github.williamguimont.game.gamestate.configuration.Local;
import io.github.williamguimont.game.gamestate.configuration.Network;
import io.github.williamguimont.game.gamestate.configuration.Solo;
import io.github.williamguimont.utils.State;

public class MainMenu extends BaseConfigState {

    public MainMenu(GameConfig config) {
        super(config);
    }

    @Override
    public void execute() {
        System.out.println("  _____ _                 _         _____                      \n"
                + " / ____(_)               | |       / ____|                     \n"
                + "| (___  _ _ __ ___  _ __ | | ___  | |  __  __ _ _ __ ___   ___ \n"
                + " \\___ \\| | '_ ` _ \\| '_ \\| |/ _ \\ | | |_ |/ _` | '_ ` _ \\ / _ \\\n"
                + " ____) | | | | | | | |_) | |  __/ | |__| | (_| | | | | | |  __/\n"
                + "|_____/|_|_| |_| |_| .__/|_|\\___|  \\_____|\\__,_|_| |_| |_|\\___|\n"
                + "                   | |                                         \n"
                + "                   |_|                                         \n");
        System.out.println("Hello to this simple game");
        System.out.println("Please select the mode you want to play");

        System.out.println("1. Solo play");
        System.out.println("2. Local play");
        System.out.println("3. Network play");
        System.out.println("4. Exit");

        Scanner scanner = new Scanner(System.in);
        State next = null;
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            switch (choice) {
            case 1:
                next = new Solo(getConfig());
                break;
            case 2:
                next = new Local(getConfig());
                break;
            case 3:
                next = new Network(getConfig());
                break;
            case 4:
                next = new CancelConfig(getConfig());
                break;
            default:
                System.out.println("Input error ! Please enter a number between 1 and 4");
                next = new MainMenu(getConfig());
                break;
            }
        } else {
            System.out.println("Input error ! Please enter a number");
            next = new MainMenu(getConfig());
        }
        setNextState(next);
        scanner.close();
    }
}