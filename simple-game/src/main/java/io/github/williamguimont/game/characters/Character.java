package io.github.williamguimont.game.characters;

import io.github.williamguimont.game.World;

public class Character {

    public enum FacingDirection {
        Left(-1), Right(1);

        private final int value;

        private FacingDirection(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    };

    private String name;
    private int health;
    private final int maxHealth;
    private int strenght;
    private int position;
    private final FacingDirection facingDirection;
    private final World world;

    public Character(String name, int health, int maxHealth, int strenght, int position,
            FacingDirection facingDirection, World world) {
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.strenght = strenght;
        this.position = position;
        this.facingDirection = facingDirection;
        this.world = world;
    }

    private void move(int movement) {
        int nextPos = position + movement;
        if (world.isCaseEmpty(nextPos)) {
            world.move(position, nextPos);
            position = nextPos;
        }
    }

    public void goForward() {
        move(facingDirection.getValue());
    }

    public void goBackward() {
        move(-facingDirection.getValue());
    }

    public void attack() {
        int attackPosition = position + facingDirection.getValue();
        Character other = world.getAtPosition(attackPosition);
        if (other != null) {
            other.takeDamage(strenght);
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    private void addHealth(int change) {
        health += change;
        if (health < 0)
            health = 0;
        else if (health > maxHealth)
            health = maxHealth;
    }

    public void takeDamage(int damage) {
        addHealth(-damage);
    }

    public void heal(int heal) {
        addHealth(heal);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getStrenght() {
        return strenght;
    }

    public int getPosition() {
        return position;
    }

    public FacingDirection getFacingDirection() {
        return facingDirection;
    }
}