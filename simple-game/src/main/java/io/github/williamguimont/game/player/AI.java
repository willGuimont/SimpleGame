package io.github.williamguimont.game.player;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;

public class AI extends Player {

    public AI(Character c) {
        super(c);
    }

    @Override
    public void takeTurn(Game game) {
        Character c = getCharacter();
        Turn turn = null;
        if (c.canAttack()) {
            turn = Turn.Attack;
        } else {
            turn = Turn.GoForward;
        }
        doTurn(turn);
    }
}