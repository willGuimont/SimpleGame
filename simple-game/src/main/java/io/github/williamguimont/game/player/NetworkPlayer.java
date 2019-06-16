package io.github.williamguimont.game.player;

import io.github.williamguimont.game.Game;
import io.github.williamguimont.game.characters.Character;
import io.github.williamguimont.network.NetworkStream;
import io.github.williamguimont.network.NetworkStream.NetworkException;
import io.github.williamguimont.utils.Serializator;
import io.github.williamguimont.utils.Serializator.SerializationException;

public class NetworkPlayer extends Player {

    public class NetworkError extends RuntimeException {
    }

    NetworkStream stream;
    RealPlayer player;

    public NetworkPlayer(Character c, NetworkStream stream) {
        super(c);
        this.stream = stream;
        player = new RealPlayer(c);
    }

    @Override
    public void takeTurn(Game game) {
        if (game.isFirstTurn()) {
            Turn turn = receiveTurn();
            doTurn(turn);
        } else {
            // Send lastTurn
            Player otherPlayer = game.getOtherPlayer();
            Turn otherLastTurn = otherPlayer.getLastPlayedTurn();
            sendTurn(otherLastTurn);
            // Do received turn
            Turn newTurn = receiveTurn();
            if (newTurn != null)
                doTurn(newTurn);
            else
                doTurn(Turn.Nothing);
        }
    }

    private void sendTurn(Turn t) {
        try {
            stream.sendData(Serializator.serializeToString(t));
        } catch (NetworkException | SerializationException | NullPointerException e) {
            throw new NetworkError();
        }
    }

    private Turn receiveTurn() {
        String data;
        try {
            data = stream.getData();
            Turn t = (Turn)Serializator.loadFromString(data);
            return t;
        } catch (NetworkException | SerializationException | NullPointerException e) {
            throw new NetworkError();
        }

    }
}