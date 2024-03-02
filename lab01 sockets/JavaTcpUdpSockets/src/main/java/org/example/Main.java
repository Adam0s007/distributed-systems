package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int port = 12345; // Używamy tego samego portu dla TCP i UDP dla uproszczenia
        try {
            TcpServer tcpServer = new TcpServer(); // Zakładając, że TcpServer został odpowiednio zmodyfikowany
            try {
                tcpServer.start();
            } catch (Exception e) {
                System.out.println("Błąd podczas uruchamiania serwera TCP: " + e.getMessage());
                e.printStackTrace();
            }

            UdpServer udpServer = new UdpServer(port);
            udpServer.start();

            System.out.println("Serwery TCP i UDP działają na porcie " + port);
        } catch (IOException e) {
            System.out.println("Błąd podczas uruchamiania serwerów: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
