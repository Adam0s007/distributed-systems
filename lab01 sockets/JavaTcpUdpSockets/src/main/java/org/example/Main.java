package org.example;

import static org.example.Config.SERVER_PORT;

public class Main {
    public static void main(String[] args) {
        int port = SERVER_PORT;

        Thread ChatServer = new Thread(() -> {
            try {
                ChatServer tcpServer = new ChatServer(port);
                tcpServer.start();
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

