package io.github.williamguimont.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements NetworkStream {
    private Socket clientSocket;
    private BufferedReader inFromServer;
    private DataOutputStream outToServer;

    public Client(String ip, int port) throws NetworkException {
        try {
            clientSocket = new Socket(ip, port);
            inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new NetworkException("Server can't send to client");
        }
    }

    public String getData() throws NetworkException {

        try {
            return inFromServer.readLine();
        } catch (IOException e) {
            throw new NetworkException("Client can't read from server");
        }
    }

    public void sendData(String data) throws NetworkException {
        try {
            outToServer.writeBytes(data + 'n');
        } catch (IOException e) {
            throw new NetworkException("Client can't send to server");
        }
    }

    public void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
        }
    }
}