package io.github.williamguimont.game.gamestate;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.utils.State;

public abstract class BaseGameState implements State {

    private Game game;
    private State nextState = null;

    public BaseGameState(Game game) {
        this.game = game;
    }

    @Override
    public State getNextState() {
        return nextState;
    }

    public void setNextState(State next) {
        nextState = next;
    }

    public Game getGame() {
        return game;
    }

}