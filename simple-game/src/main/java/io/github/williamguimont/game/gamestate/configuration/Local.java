package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.game.player.RealPlayer;

public class Local extends BaseGameState {

    public Local(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Player one, chose your character");
        Character c1 = CharacterChoser.choseCharacter();
        Player p1 = new RealPlayer(c1);
        getGame().setPlayer1(p1);

        System.out.println("Player two, chose your character");
        Character c2 = CharacterChoser.choseCharacter();
        Player p2 = new RealPlayer(c2);
        getGame().setPlayer2(p2);

        setNextState(new WorldChooser(getGame(), new ExitConfig(getGame())));
    }
}