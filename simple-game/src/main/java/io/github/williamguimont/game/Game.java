package io.github.williamguimont.game;

import io.github.williamguimont.game.gamestate.configuration.MainMenu;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.utils.StateMachine;

public class Game {

    private StateMachine stateMachine;
    private World world;
    private Player player1;
    private Player player2;
    private boolean isSetup;

    public Game() {
        world = new World(10);
        isSetup = false;
    }

    public void run() {
        config();
        if (isSetup)
            play();
    }

    private void config() {
        stateMachine = new StateMachine(new MainMenu(this));
        while (stateMachine.hasState()) {
            stateMachine.execute();
            stateMachine.transition();
        }
    }

    private void play() {
        System.out.println("Config finished !");
        // TODO game state machine
        // stateMachine = new StateMachine(new MainMenu());
        // while (stateMachine.hasState()) {
        // stateMachine.execute();
        // stateMachine.transition();
        // }
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setPlayer1(Player player) {
        player1 = player;
    }

    public void setPlayer2(Player player) {
        player2 = player;
    }

    public void finishSetup() {
        isSetup = true;
    }

}