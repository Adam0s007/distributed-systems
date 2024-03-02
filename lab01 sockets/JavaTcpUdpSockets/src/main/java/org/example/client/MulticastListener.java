package org.example.client;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MulticastListener implements Runnable {
    private final MulticastSocket multicastSocket;

    public MulticastListener(MulticastSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                multicastSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Multicast message received: " + received);
            } catch (IOException e) {
                System.out.println("Multicast socket closed.");
                break;
            }
        }
    }
}

