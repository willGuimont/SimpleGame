package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.game.player.AI;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.game.player.RealPlayer;

public class Solo extends BaseGameState {

    public Solo(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        Game game = getGame();
        System.out.println("Please select your character");

        Character c1 = CharacterChoser.choseCharacter();
        Player p1 = new RealPlayer(c1);
        game.setPlayer1(p1);

        Character c2 = CharacterChoser.randomCharacter();
        Player p2 = new AI(c2);
        game.setPlayer2(p2);

        setNextState(new WorldChoser(game));
    }
}