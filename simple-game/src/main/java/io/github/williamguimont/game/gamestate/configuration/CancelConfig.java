package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.gamestate.BaseGameState;

public class CancelConfig extends BaseGameState {

    public CancelConfig(Game game)
    {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Exiting simple game...");
        setNextState(null);
    }
}