package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.gamestate.BaseGameState;

public class Host extends BaseGameState {

    public Host(Game game)
    {
        super(game);
    }

    @Override
    public void execute() {
        // TODO host
        System.out.println("Host");
    }
}