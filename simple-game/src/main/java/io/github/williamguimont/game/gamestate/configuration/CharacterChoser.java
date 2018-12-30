package io.github.williamguimont.game.gamestate.configuration;

import java.util.Random;
import java.util.Scanner;

import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.characters.CharacterFactory;
import io.github.williamguimont.game.characters.CharacterFactory.CharacterType;

public class CharacterChoser {

    public static Character choseCharacter() {
        int choice = -1;
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name?");
        String name = scanner.nextLine();

        while (!isChoiceValid(choice)) {
            System.out.println("Select your character");
            System.out.println("1. Rogue");
            System.out.println("2. Knight");
            System.out.println("3. Mage");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
        }
        return intToCharacter(choice, name);
    }

    public static Character randomCharacter() {
        Random rand = new Random();
        return intToCharacter(rand.nextInt(4), "CPU");
    }

    private static boolean isChoiceValid(int c) {
        return c >= 1 && c <= 3;
    }

    public static Character intToCharacter(int c, String name) {
        switch (c) {
        case 1:
            return CharacterFactory.create(CharacterType.Rogue, name);
        case 2:
            return CharacterFactory.create(CharacterType.Knight, name);
        case 3:
            return CharacterFactory.create(CharacterType.Mage, name);
        default:
            return null;
        }
    }
}