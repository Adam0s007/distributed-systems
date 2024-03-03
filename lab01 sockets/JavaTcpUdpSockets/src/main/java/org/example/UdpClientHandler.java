package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;

public class UdpClientHandler implements Runnable {
    private DatagramSocket socket;
    private final Map<String, ClientInfo> clientAddresses;

    public UdpClientHandler(DatagramSocket udpSocket, Map<String,ClientInfo>  udpClients) {
        this.socket = udpSocket;
        this.clientAddresses = udpClients;

    }
    @Override
    public void run()  {
            while (true) {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try {
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("<UDP> Received message: " + received);

                    if (received.startsWith("INIT")) {
                        System.out.println("<UDP> New client connected: " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
                        connectUser(packet.getAddress().getHostAddress(), packet.getPort());
                    } else if (received.startsWith("U:")) {
                        broadcastMessage(received.substring(2), packet.getAddress().getHostAddress(), packet.getPort());
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
    }

    private void broadcastMessage(String message, String senderAddress, int senderPort) {
        byte[] buffer = message.getBytes();
        clientAddresses.forEach((key, clientInfo) -> {
            if (!key.equals(senderAddress + ":" + senderPort)) { // Nie wysyłaj wiadomości z powrotem do nadawcy
                try {
                    DatagramPacket packetToSend = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(clientInfo.address), clientInfo.udpPort);
                    socket.send(packetToSend);
                } catch (IOException e) {
                    System.err.println("I/O exception occurred while sending the packet.");
                    e.printStackTrace();
                }
            }
        });
    }


    private void connectUser(String address, int udpPort) {
        String key = address + ":" + udpPort;
        if (!clientAddresses.containsKey(key)) {
            clientAddresses.put(key, new ClientInfo(address, udpPort));
            System.out.println("<UDP> Added new client for UDP distribution: " + address + ":" + udpPort);
        }
    }

    public void cleanUp() {
        System.out.println("<UDP> Shutting down UDP server...");
        clientAddresses.clear();
        socket.close();
        System.out.println("<UDP> Server closed.");
    }


}
