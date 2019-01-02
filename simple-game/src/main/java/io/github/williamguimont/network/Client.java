package io.github.williamguimont.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements NetworkStream {
    private Socket clientSocket;
    private BufferedReader in;
    private OutputStreamWriter out;

    public Client(String ip, int port) throws NetworkException {
        try {
            clientSocket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new OutputStreamWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new NetworkException("Server can't send to client");
        }
    }

    public String getData() throws NetworkException {

        try {
            return in.readLine();
        } catch (IOException e) {
            throw new NetworkException("Client can't read from server");
        }
    }

    public void sendData(String data) throws NetworkException {
        try {
            out.write(data + '\n');
            out.flush();
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