package io.github.williamguimont.game;

import io.github.williamguimont.game.characters.Character;

public class World {

    private final Character[] field;
    private final int size;


    public World(int size) {
        field = new Character[size];
        this.size = size;
    }

    public void move(int from, int to) {
        if (isCaseEmpty(to)) {
            field[to] = field[from];
            field[from] = null;
        } else {
            // TODO to do ?
        }
    }

    public boolean isCaseEmpty(int position) {
        if (position < 0 || position >= size)
            return false;
        return field[position] == null ? true : false;
    }

    public Character getAtPosition(int position) {
        if (position < 0 || position >= size)
            return null;
        return field[position];
    }
}