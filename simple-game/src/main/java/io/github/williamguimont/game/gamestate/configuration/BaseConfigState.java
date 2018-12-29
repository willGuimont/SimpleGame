package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game.GameConfig;
import io.github.williamguimont.game.gamestate.BaseGameState;

public abstract class BaseConfigState extends BaseGameState {

    private GameConfig config;

    public BaseConfigState(GameConfig config) {
        this.config = config;
    }

    public GameConfig getConfig() {
        return config;
    }

}