package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public UdpServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {
        running = true;

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);

                // Przetwarzanie otrzymanej wiadomości i rozsyłanie do wszystkich klientów
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("UDP Server received: " + received);

                // W tym miejscu można dodać logikę rozsyłania do klientów
                // W przypadku prostego przykładu, pominęliśmy tę część.

            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
        }
        socket.close();
    }
}
