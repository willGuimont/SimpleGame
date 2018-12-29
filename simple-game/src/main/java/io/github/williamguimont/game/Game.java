package io.github.williamguimont.game;

import io.github.williamguimont.game.gamestate.configuration.MainMenu;
import io.github.williamguimont.utils.StateMachine;

public class Game {

    // TODO
    public class GameConfig {
        private Character player1;
        private Character player2;
        private boolean isFinished;

        public boolean isFinished() {
            return isFinished;
        }
    }

    private StateMachine stateMachine;
    private World world;
    private boolean isSetup;

    public Game() {
        world = new World(10);
        isSetup = false;
    }

    public void config() {
        GameConfig config = new GameConfig();
        stateMachine = new StateMachine(new MainMenu(config));
        while (stateMachine.hasState()) {
            stateMachine.execute();
            stateMachine.transition();
        }

        if (config.isFinished()) {
            // Okay to start
            // TODO setup the game
        }
    }

    public void play() {
        if (isSetup) {
            // TODO game state machine
            // stateMachine = new StateMachine(new MainMenu());
            // while (stateMachine.hasState()) {
            // stateMachine.execute();
            // stateMachine.transition();
            // }
        } else {
            // TODO throw error
        }
    }

}