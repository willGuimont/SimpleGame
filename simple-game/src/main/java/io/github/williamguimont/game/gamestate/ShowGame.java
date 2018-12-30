package io.github.williamguimont.game.gamestate;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.World;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.game.player.Player;

public class ShowGame extends BaseGameState {

    public ShowGame(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        Game game = getGame();
        World world = game.getWorld();
        Player p1 = game.getPlayer1();
        Player p2 = game.getPlayer2();
        Character c1 = p1.getCharacter();
        Character c2 = p2.getCharacter();

        for (int i = 0; i < world.getSize(); ++i) {
            if (i == c1.getPosition()) {
                System.out.print(ansi().fg(GREEN).a("@").reset());
            } else if (i == c2.getPosition()) {
                System.out.print(ansi().fg(RED).a("@").reset());
            } else {
                if (world.getAtPosition(i) == null) {
                    System.out.print(" ");
                } else {
                    System.out.print("@");
                }
            }
        }
        System.out.println();
        for (int i = 0; i < world.getSize(); ++i)
        {
            System.out.print("-");
        }
        System.out.println();
        setNextState(new PlayerTurn(game));
    }
}
