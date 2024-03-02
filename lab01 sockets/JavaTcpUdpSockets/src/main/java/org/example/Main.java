package org.example;

import java.io.IOException;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        int port = 12345; // Używamy tego samego portu dla TCP i UDP dla uproszczenia

        // Tworzenie i uruchamianie wątku dla serwera TCP
        Thread tcpServerThread = new Thread(() -> {
            try {
                TcpServer tcpServer = new TcpServer(port); // Zakładając, że TcpServer został odpowiednio zmodyfikowany
                tcpServer.start();
            } catch (Exception e) {
                System.out.println("Błąd podczas uruchamiania serwera TCP: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Tworzenie i uruchamianie wątku dla serwera UDP
        Thread udpServerThread = new Thread(() -> {
            try {
                UdpServer udpServer = new UdpServer(port); // Zakładając, że UdpServer został odpowiednio zmodyfikowany
                udpServer.start();
            } catch (Exception e) {
                System.out.println("Błąd podczas uruchamiania serwera UDP: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Uruchamianie wątków
        tcpServerThread.start();
        udpServerThread.start();
        //System.out.println("Serwery TCP i UDP działają na porcie " + port);
        try{
            tcpServerThread.join();
            udpServerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Błąd podczas uruchamiania wątków: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

