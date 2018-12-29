package io.github.williamguimont.network;

import java.io.IOException;

public class NetworkException extends IOException {

    private static final long serialVersionUID = 1863871124574850912L;

    public NetworkException(String msg) {
        super(msg);
    }

}