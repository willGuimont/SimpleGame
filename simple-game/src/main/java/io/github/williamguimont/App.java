package io.github.williamguimont;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import io.github.williamguimont.game.Game;

public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
