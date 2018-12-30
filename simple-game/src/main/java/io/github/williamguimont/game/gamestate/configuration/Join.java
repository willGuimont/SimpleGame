package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.gamestate.BaseGameState;

public class Join extends BaseGameState {

    public Join(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        // TODO join game
        System.out.println("Join");
    }
}