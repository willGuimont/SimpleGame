package io.github.williamguimont.game.gamestate.configuration;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.World;
import io.github.williamguimont.game.gamestate.BaseGameState;
import io.github.williamguimont.game.player.NetworkPlayer;
import io.github.williamguimont.network.NetworkStream.NetworkException;
import io.github.williamguimont.network.Server;

public class Host extends BaseGameState {

    private final Server server;

    public Host(Game game, Server server) {
        super(game);
        this.server = server;
    }

    @Override
    public void execute() {
        try {
            Game game = getGame();
            // World size
            World world = game.getWorld();
            Integer worldSize = world.getSize();
            server.sendData(worldSize.toString());
            server.sendData(world.getType().toString());

            setNextState(new ExitConfig(game));
        } catch (NetworkException | NetworkPlayer.NetworkError e) {
            setNextState(new MainMenu(getGame()));
        }
    }
}
