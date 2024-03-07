package org.example.client;

import org.example.client.connection.ConnectionManager;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        ConnectionManager manager = new ConnectionManager();
       try {
           manager.connect();
       } catch (IOException e) {
         System.out.println("Connection failed: " + e.getMessage());
       }
    }
}

