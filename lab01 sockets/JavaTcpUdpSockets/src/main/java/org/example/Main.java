package org.example;

public class Main {
    public static void main(String[] args) {
        int port = 12345;

        Thread ChatServer = new Thread(() -> {
            try {
                ChatServer tcpServer = new ChatServer(port);
                tcpServer.start();
            } catch (Exception e) {
                System.out.println("Błąd podczas uruchamiania serwera TCP: " + e.getMessage());
                e.printStackTrace();
            }
        });

        ChatServer.start();
        try{
            ChatServer.join();
        } catch (InterruptedException e) {
            System.out.println("Błąd podczas uruchamiania wątków: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

