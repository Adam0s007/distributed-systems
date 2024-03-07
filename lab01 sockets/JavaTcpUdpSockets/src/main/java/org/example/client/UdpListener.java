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
        try {
            while (running) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    udpSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    if(received.contains("DISCONNECT") || received.contains("SERVERSHUTDOWN")){
                        this.running = false;
                    }else{
                        System.out.println("UDP message received: " + received);
                    }
                }
            }catch (IOException e) {
                System.out.println("<UdpListener> UDP socket closed.");

            }
        }

    }


