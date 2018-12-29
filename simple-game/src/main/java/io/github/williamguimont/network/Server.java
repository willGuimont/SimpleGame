package io.github.williamguimont.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Server implements NetworkStream {
    private ServerSocket serverSocket;
    private Socket socket;
    DataOutputStream outToClient;
    private BufferedReader inFromClient;

    public Server(int port) throws NetworkException {

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            outToClient = new DataOutputStream(socket.getOutputStream());
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new NetworkException("Server can't send to client");
        }
    }

    public void sendData(String data) throws NetworkException {
        try {
            outToClient.writeBytes(data);
        } catch (IOException e) {
            throw new NetworkException("Server can't send to client");
        }
    }

    public String getData() throws NetworkException {
        try {
            return inFromClient.readLine();
        } catch (IOException e) {
            throw new NetworkException("Server can't read from client");
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
        }
    }
}