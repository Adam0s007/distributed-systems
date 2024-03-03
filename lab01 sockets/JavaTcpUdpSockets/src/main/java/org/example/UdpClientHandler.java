package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;

public class UdpClientHandler implements Runnable {
    private DatagramSocket socket;
    private final Map<String, ClientInfo> clientAddresses;

    public UdpClientHandler(DatagramSocket udpSocket, Map<String, ClientInfo> udpClients) {
        this.socket = udpSocket;
        this.clientAddresses = udpClients;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                processIncomingPacket();
            } catch (IOException e) {
                System.err.println("I/O exception occurred while receiving the packet: " + e.getMessage());
            }
        }
    }

    private void processIncomingPacket() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        String received = new String(packet.getData(), 0, packet.getLength()).trim();
        //System.out.println("<UDP> Received message: " + received);
        handlePacketContent(received, packet);
    }

    private void handlePacketContent(String message, DatagramPacket packet) {
        String senderKey = getKeyForPacket(packet);
        switch (message.split(":", 2)[0]) {
            case "INIT":
                System.out.println("<UDP> New client connected: " + senderKey);
                connectUser(senderKey);
                break;
            case "U":
                String actualMessage = message.substring(2).trim();
                broadcastMessage(actualMessage, senderKey);
                break;
            case "DISCONNECT":
                clientAddresses.remove(senderKey);
                System.out.println("<UDP> Client " + senderKey + " disconnected");
                break;
            default:
                System.out.println("<UDP> Unknown message: " + message);
        }
    }

    private void broadcastMessage(String message, String senderKey) {
        byte[] buffer = message.getBytes();
        clientAddresses.forEach((key, clientInfo) -> {
            if (!key.equals(senderKey)) {
                try {
                    DatagramPacket packetToSend = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(clientInfo.address), clientInfo.udpPort);
                    socket.send(packetToSend);
                } catch (IOException e) {
                    System.err.println("Failed to send message: " + e.getMessage());
                }
            }
        });
    }

    private void connectUser(String key) {
        String[] parts = key.split(":");
        String address = parts[0];
        int udpPort = Integer.parseInt(parts[1]);
        clientAddresses.putIfAbsent(key, new ClientInfo(address, udpPort));
        System.out.println("<UDP> Added new client for UDP distribution: " + key);
    }

    private String getKeyForPacket(DatagramPacket packet) {
        return packet.getAddress().getHostAddress() + ":" + packet.getPort();
    }
}
