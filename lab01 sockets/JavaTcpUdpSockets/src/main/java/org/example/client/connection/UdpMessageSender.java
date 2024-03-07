package org.example.client.connection;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UdpMessageSender {
    private InetAddress multicastGroup;
    private int multicastPort;

    public UdpMessageSender(InetAddress multicastGroup, int multicastPort) {
        this.multicastGroup = multicastGroup;
        this.multicastPort = multicastPort;
    }

    public void sendUdpMessage(DatagramSocket udpSocket, String message, String serverAddress, int serverPort) throws IOException {
        String prefixedMessage = "U: " + message;
        if(message =="INIT")
            prefixedMessage = "INIT";
        if(message =="DISCONNECT")
            prefixedMessage = "DISCONNECT";
        byte[] buffer = prefixedMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(serverAddress), serverPort);
        udpSocket.send(packet);
        System.out.println("UDP message sent.");
    }

    public void sendMulticastMessage(String message) throws IOException {
        try (MulticastSocket multicastSocket = new MulticastSocket()) {
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastGroup, multicastPort);
            multicastSocket.send(packet);
            System.out.println("Multicast message sent.");
        }
    }
}
