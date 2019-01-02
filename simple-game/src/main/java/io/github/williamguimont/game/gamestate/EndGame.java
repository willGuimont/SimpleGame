package io.github.williamguimont.game.gamestate;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.player.Player;

public class EndGame extends BaseGameState {

    Player winner;
    
    public EndGame(Game game) {
        super(game);
        this.winner = game.getWinningPlayer();
    }

    @Override
    public void execute() {
        System.out.println("The winner is " + winner.getCharacter().getName());
        setNextState(null);
    }

}
