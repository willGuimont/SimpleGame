package io.github.williamguimont.network;

public interface NetworkStream {
    void sendData(String data) throws NetworkException;

    String getData() throws NetworkException;

    void close();
}