package io.github.williamguimont.game.player;

import java.util.Scanner;

import io.github.williamguimont.game.characters.Character;

public class RealPlayer extends Player {

    public RealPlayer(Character c) {
        super(c);
    }

    @Override
    public void takeTurn() {
        // TODO player with inputs
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 1 || choice > 3) {
            System.out.println("Please enter your action");
            System.out.println("1. Go forward");
            System.out.println("2. Go backward");
            System.out.println("3. Attack");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
        }

        Character c = getCharacter();
        switch (choice) {
        case 1:
            c.goForward();
            break;
        case 2:
            c.goBackward();
            break;
        case 3:
            c.attack();
            break;
        default:
            break;
        }
    }
}