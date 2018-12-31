package io.github.williamguimont.game.player;

import io.github.williamguimont.game.characters.Character;

public abstract class Player {
    private Character character;

    public Player(Character c) {
        character = c;
    }

    public Character getCharacter() {
        return character;
    }

    public abstract void takeTurn();
}