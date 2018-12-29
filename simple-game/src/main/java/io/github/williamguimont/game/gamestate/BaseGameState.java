package io.github.williamguimont.game.gamestate;

import io.github.williamguimont.utils.State;

public abstract class BaseGameState implements State {

    private State nextState = null;

    @Override
    public State getNextState() {
        return nextState;
    }

    public void setNextState(State next)
    {
        nextState = next;
    }

}