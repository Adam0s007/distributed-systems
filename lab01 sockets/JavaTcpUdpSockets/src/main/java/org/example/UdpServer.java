package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ConcurrentHashMap;

public class UdpServer {
    private final int port;
    private final String multicastAddress;
    private final ConcurrentHashMap<String, ClientInfo> clientAddresses;

    public UdpServer(int port, String multicastAddress) {
        this.port = port;
        this.multicastAddress = multicastAddress;
        this.clientAddresses = new ConcurrentHashMap<>();
    }

    public void start() {
        try (DatagramSocket serverSocket = new DatagramSocket(port);
             MulticastSocket multicastSocket = new MulticastSocket()) {
            System.out.println("<UDP> Server listening on port " + port);
            multicastSocket.joinGroup(InetAddress.getByName(multicastAddress));

            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            while (true) {
                serverSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("<UDP> Received: " + received + " from " + packet.getAddress() + ":" + packet.getPort());

                if (received.startsWith("INIT")) {
                    addClientAddress(packet.getAddress().getHostAddress(), packet.getPort());
                } else if (received.startsWith("DISCONNECT")) {
                    clientAddresses.remove(packet.getAddress().getHostAddress() + ":" + packet.getPort());
                    System.out.println("<UDP> Client disconnected: " + packet.getAddress() + ":" + packet.getPort());
                } else {

                }


                String message = "Echo: " + received;
                buf = message.getBytes();
                packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(multicastAddress), port);
                multicastSocket.send(packet);
            }
        } catch (IOException e) {
            System.out.println("<UDP> Server exception: " + e.getMessage());
        }
    }

    private void addClientAddress(String address, int port) {
        clientAddresses.put(address + ":" + port, new ClientInfo(address, port));
        System.out.println("<UDP> New client added: " + address + ":" + port);
    }

    private static class ClientInfo {
        String address;
        int port;

        public ClientInfo(String address, int port) {
            this.address = address;
            this.port = port;
        }
    }

    public static void main(String[] args) {
        int port = 8124; // Example port number
        String multicastAddress = "224.0.0.114"; // Example multicast address
        UdpServer server = new UdpServer(port, multicastAddress);
        server.start();
    }
}

