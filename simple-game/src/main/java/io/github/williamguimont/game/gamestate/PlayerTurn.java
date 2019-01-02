package io.github.williamguimont.game.gamestate;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.player.NetworkPlayer;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.utils.State;

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

        player.takeTurn(game);
        game.changeTurn();

        State nextState = null;
        if (game.isGameFinished()) {
            Player p = game.getCurrentPlayer();
            // TODO halp
            if (p instanceof NetworkPlayer) {
                p.takeTurn(game);
            }
            nextState = new EndGame(game);
        } else {
            nextState = new ShowGame(game);
        }

        setNextState(nextState);
    }
}