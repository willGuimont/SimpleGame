package io.github.williamguimont.game.gamestate;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.player.Player;

public class PlayerTurn extends BaseGameState {

    public PlayerTurn(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        Game game = getGame();
        Player player = game.getCurrentPlayer();
        Character c = player.getCharacter();
        System.out.println("It's the turn of " + c.getName());

        player.takeTurn();
        game.changeTurn();

        if (game.isOnePlayerDead()) {
            setNextState(new EndGame(game, game.getWinningPlayer()));
        } else {
            setNextState(new ShowGame(game));
        }
    }
}