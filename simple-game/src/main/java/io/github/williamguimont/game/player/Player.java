package io.github.williamguimont.game.player;

import io.github.williamguimont.game.World;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.characters.Character.FacingDirection;

public abstract class Player {
    private Character character;

    public Player(Character c) {
        character = c;
    }

    public Character getCharacter() {
        return character;
    }

    public void setWorld(World world, int position, FacingDirection facingDirection)
    {
        character.setInWorld(world, position, facingDirection);
    }

    public abstract void takeTurn();
}