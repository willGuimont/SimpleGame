package io.github.williamguimont.utils;

public interface State {
    void execute();

    State getNextState();
}