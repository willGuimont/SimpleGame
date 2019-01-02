package io.github.williamguimont.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements NetworkStream {
    private ServerSocket serverSocket;
    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;

    public Server(int port) throws NetworkException {

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            out = new OutputStreamWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new NetworkException("Server can't send to client");
        }
    }

    public void sendData(String data) throws NetworkException {
        try {
            out.write(data + '\n');
            out.flush();
        } catch (IOException e) {
            throw new NetworkException("Server can't send to client");
        }
    }

    public String getData() throws NetworkException {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new NetworkException("Server can't read from client");
        }
    }

    public void close() {
        try {
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
        }
    }
}