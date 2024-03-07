package org.example;

import org.example.server.ChatServer;

import static org.example.util.Config.SERVER_PORT;

public class Main {
    public static void main(String[] args) {
        int port = SERVER_PORT;

        Thread ChatServer = new Thread(() -> {
            try {
               ChatServer server = new ChatServer(port);
               server.start();
            } catch (Exception e) {
                System.out.println("Server exception: " + e.getMessage());
                e.printStackTrace();
            }
        });

        ChatServer.start();
        try{
            ChatServer.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

