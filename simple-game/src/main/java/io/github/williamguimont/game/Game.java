package io.github.williamguimont.game;

import org.fusesource.jansi.AnsiConsole;

import io.github.williamguimont.game.characters.Character.FacingDirection;
import io.github.williamguimont.game.gamestate.ShowGame;
import io.github.williamguimont.game.gamestate.configuration.MainMenu;
import io.github.williamguimont.game.player.Player;
import io.github.williamguimont.utils.StateMachine;

public class Game {
    private StateMachine stateMachine;
    private World world;
    private Player player1;
    private Player player2;
    private boolean isSetup;
    private boolean isPlayer1Turn;
    private int nbTurns;

    public Game() {
        world = new World(10);
        isSetup = false;
        isPlayer1Turn = true;
        nbTurns = 1;
    }

    public void run() {
        AnsiConsole.systemInstall();
        config();
        if (isSetup)
            play();
    }

    private void config() {
        stateMachine = new StateMachine(new MainMenu(this));
        while (stateMachine.hasState()) {
            stateMachine.execute();
            stateMachine.transition();
        }
    }

    private void play() {
        stateMachine = new StateMachine(new ShowGame(this));
        while (stateMachine.hasState()) {
            stateMachine.execute();
            stateMachine.transition();
        }
    }

    public boolean isGameFinished() {
        return player1.hasLost() || player2.hasLost();
    }

    public Player getWinningPlayer() {
        if (player1.hasLost()) {
            return player2;
        } else if (player2.hasLost()) {
            return player1;
        } else {
            return null;
        }
    }

    public void changeTurn() {
        isPlayer1Turn = !isPlayer1Turn;
        nbTurns += 1;
    }

    public int getNbTurns() {
        return nbTurns;
    }

    public boolean isFirstTurn() {
        return nbTurns == 1;
    }

    public Player getCurrentPlayer() {
        return isPlayer1Turn ? player1 : player2;
    }

    public Player getOtherPlayer() {
        return isPlayer1Turn ? player2 : player1;
    }

    public void setWorld(World world) {
        this.world = world;
        world.addCharacter(player1.getCharacter(), 0, FacingDirection.Right);
        world.addCharacter(player2.getCharacter(), world.getSize() - 1, FacingDirection.Left);
    }

    public World getWorld() {
        return world;
    }

    public void setPlayer1(Player player) {
        player1 = player;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer2(Player player) {
        player2 = player;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void finishSetup() {
        isSetup = true;
    }

}