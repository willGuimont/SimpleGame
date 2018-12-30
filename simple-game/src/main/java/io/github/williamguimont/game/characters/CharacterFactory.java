package io.github.williamguimont.game.characters;

public class CharacterFactory {

    public enum CharacterType {
        Rogue, Knight, Mage
    };

    public static Character create(CharacterType type, String name) {
        switch (type) {
        case Rogue:
            return createRogue(name);
        case Knight:
            return createKnight(name);
        case Mage:
            return createMage(name);
        default:
            return null;
        }
    }

    private static Character createRogue(String name) {
        return new Character(name, 10, 10, 2);
    }

    private static Character createKnight(String name) {
        return new Character(name, 20, 20, 3);
    }

    private static Character createMage(String name) {
        return new Character(name, 10, 10, 1);
    }
}