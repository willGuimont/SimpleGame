package io.github.williamguimont.game.player;

import io.github.williamguimont.game.characters.Character;

public class AI extends Player {

    public AI(Character c) {
        super(c);
    }

    @Override
    public void takeTurn() {
        Character c = getCharacter();
        if (c.canAttack()) {
            c.attack();
        } else {
            c.goForward();
        }
    }
}