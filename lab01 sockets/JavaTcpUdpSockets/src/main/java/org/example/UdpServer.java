package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UdpServer {
    private DatagramSocket socket;
    private final Map<String, ClientInfo> clientAddresses = new ConcurrentHashMap<>();
    private final int port;

    public UdpServer(int port) {
       this.port = port;
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.println("Socket could not be opened, or socket could not bind to the specified local port.");
        }
    }

    public void start() throws IOException {
        System.out.println("<UDP> Server listening on port " + port);
        new Thread(() -> {
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try {
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("<UDP> Received message: " + received);

                    if (received.startsWith("INIT")) {
                        System.out.println("<UDP> New client connected: " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
                        addClientAddress(packet.getAddress().getHostAddress(), packet.getPort());
                    } else if (received.startsWith("U:")) {
                        distributeMessage(received.substring(2), packet.getAddress().getHostAddress(), packet.getPort());
                    } else if (received.startsWith("DISCONNECT")) {
                        String key = packet.getAddress().getHostAddress() + ":" + packet.getPort();
                        clientAddresses.remove(key);
                        System.out.println("<UDP> Client " + key + " disconnected");
                    } else {
                        System.out.println("<UDP> Unknown message: " + received);
                    }
                } catch (IOException e) {
                    System.err.println("I/O exception occurred while receiving the packet.");
                }
            }
        }).start();
    }

    private void distributeMessage(String message, String senderAddress, int senderPort) {
        byte[] buffer = message.getBytes();
        clientAddresses.forEach((key, clientInfo) -> {
            if (!key.equals(senderAddress + ":" + senderPort)) { // Nie wysyłaj wiadomości z powrotem do nadawcy
                try {
                    DatagramPacket packetToSend = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(clientInfo.address), clientInfo.udpPort);
                    socket.send(packetToSend);
                } catch (IOException e) {
                    System.err.println("Nie udało się wysłać wiadomości ");
                    e.printStackTrace();
                }
            }
        });
    }


    private void addClientAddress(String address, int udpPort) {
        String key = address + ":" + udpPort;
        if (!clientAddresses.containsKey(key)) {
            clientAddresses.put(key, new ClientInfo(address, udpPort));
            System.out.println("<UDP> Added new client for UDP distribution: " + address + ":" + udpPort);
        }
    }

    public void shutdown() {
        System.out.println("<UDP> Shutting down UDP server...");
        clientAddresses.clear();
        socket.close();
        System.out.println("<UDP> Server closed.");
    }

    private static class ClientInfo {
        String address;
        int udpPort;

        public ClientInfo(String address, int udpPort) {
            this.address = address;
            this.udpPort = udpPort;
        }
    }
}
