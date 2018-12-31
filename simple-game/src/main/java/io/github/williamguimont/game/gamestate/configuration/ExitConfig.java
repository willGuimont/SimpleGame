package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character.FacingDirection;
import io.github.williamguimont.game.gamestate.BaseGameState;

public class ExitConfig extends BaseGameState {

    public ExitConfig(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        Game game = getGame();
        game.finishSetup();
        setNextState(null); // exit
    }
}