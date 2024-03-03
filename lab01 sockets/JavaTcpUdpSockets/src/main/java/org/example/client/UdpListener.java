package org.example.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpListener implements Runnable {
    private final DatagramSocket udpSocket;
    private volatile boolean running = true;
    public UdpListener(DatagramSocket udpSocket) {
        this.udpSocket = udpSocket;
    }


    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        while (running) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                udpSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("UDP message received: " + received);
            } catch (IOException e) {
                System.out.println("UDP socket closed.");
                break;
            }
        }
    }
}

