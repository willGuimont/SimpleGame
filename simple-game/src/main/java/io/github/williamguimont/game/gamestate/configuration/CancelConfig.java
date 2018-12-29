package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game.GameConfig;

public class CancelConfig extends BaseConfigState {

    public CancelConfig(GameConfig config)
    {
        super(config);
    }

    @Override
    public void execute() {
        // TODO cancel config
    }
}