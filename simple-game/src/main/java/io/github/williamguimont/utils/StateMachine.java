package io.github.williamguimont.utils;

public class StateMachine {
    private State currentState;

    public StateMachine(State initialState) {
        currentState = initialState;
    }

    public void execute() {
        if (currentState != null)
            currentState.execute();
    }

    public void transition() {
        currentState = currentState.getNextState();
    }

    public void setState(State state) {
        currentState = state;
    }

    public boolean hasState() {
        return currentState != null;
    }
}