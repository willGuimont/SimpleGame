package io.github.williamguimont.game;

import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.characters.Character.FacingDirection;

public class World {

    public enum Type {Grassland, Street, Mountain}

    private final Character[] field;
    private final int size;
    private Type type;

    public World(int size, Type type) {
        field = new Character[size];
        this.size = size;
        this.type = type;
    }

    void addCharacter(Character c, int position, FacingDirection facingDirection) {
        c.setInWorld(this, position, facingDirection);
        field[position] = c;
    }

    public void move(int from, int to) {
        if (isCaseEmpty(to)) {
            field[to] = field[from];
            field[from] = null;
        }
        // Else lose turn
    }

    public boolean isCaseEmpty(int position) {
        if (position < 0 || position >= size)
            return false;
        return field[position] == null;
    }

    public Character getAtPosition(int position) {
        if (position < 0 || position >= size)
            return null;
        return field[position];
    }

    public int getSize() {
        return size;
    }

    public Type getType() {
        return type;
    }
}