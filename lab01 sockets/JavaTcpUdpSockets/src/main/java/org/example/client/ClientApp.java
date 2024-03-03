package org.example.client;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        ConnectionManager manager = new ConnectionManager();
        manager.connect();
    }
}

