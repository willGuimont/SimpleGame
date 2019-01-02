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

        int c1pos = c1.getPosition();
        int c2pos = c2.getPosition();

        showCharacter(c1);
        showCharacter(c2);

        for (int i = 0; i < world.getSize(); ++i) {
            if (i == c1pos) {
                System.out.print(ansi().fg(GREEN).a("@").reset());
            } else if (i == c2pos) {
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
        for (int i = 0; i < world.getSize(); ++i) {
            System.out.print("-");
        }
        System.out.println();

        setNextState(new PlayerTurn(game));
    }

    public void showCharacter(Character c) {
        System.out.println("-----");
        System.out.println(c.getName() + ": ");
        System.out.println("Health: " + c.getHealth() + " / " + c.getMaxHealth());
        System.out.println("Strenght: " + c.getStrenght());
    }
}
