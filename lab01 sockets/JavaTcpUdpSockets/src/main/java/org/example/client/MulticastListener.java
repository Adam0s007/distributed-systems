package org.example.client;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

public class MulticastListener implements Runnable{
    private final MulticastSocket multicastSocket;
    private volatile boolean running = true;
    public MulticastListener(MulticastSocket multicastSocket, int clientPort) {
        this.multicastSocket = multicastSocket;
        this.clientPort = clientPort;
    }

    public int clientPort;

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        while (running) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                multicastSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (!messageIsFromSelf(received, clientPort)) {
                    System.out.println("Multicast message received: " + received);
                }
            } catch (IOException e) {
                System.out.println("Multicast socket closed.");
                break;
            }
        }
    }

    private boolean messageIsFromSelf(String message, int clientPort) {
        int endOfPortIndex = message.indexOf("]:");
        if (endOfPortIndex != -1) {
            try {
                int portInMessage = Integer.parseInt(message.substring(2, endOfPortIndex));
                return portInMessage == clientPort;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

}

