package io.github.williamguimont.game.player;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    public enum Turn {
        Nothing, GoForward, GoBackward, Attack;
    };

    private Character character;
    private List<Turn> turns;

    public Player(Character c) {
        character = c;
        turns = new ArrayList<>();
    }

    public Character getCharacter() {
        return character;
    }

    public Turn getLastPlayedTurn() {
        int size = turns.size();
        if (size >= 1)
            return turns.get(size - 1);
        else
            return null;
    }

    public boolean hasLost() {
        return character.isDead();
    }

    public abstract void takeTurn(Game game);

    protected void doTurn(Turn turn) {
        turns.add(turn);
        switch (turn) {
        case GoForward:
            goForward();
            break;
        case GoBackward:
            goBackward();
            break;
        case Attack:
            attack();
            break;
        default:
            break;
        }
    }

    private void goForward() {
        character.goForward();
    }

    private void goBackward() {
        character.goBackward();
    }

    private void attack() {
        character.attack();
    }
}