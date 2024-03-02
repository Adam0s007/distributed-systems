package org.example.client;



import java.io.*;
import java.net.*;
import static org.example.Config.*;
public class ConnectionManager {
    public  void connect() throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
        MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
        multicastSocket.joinGroup(multicastGroup);

        System.out.println("Connected to chat server");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeConnection(socket, udpSocket, multicastSocket)));

        new Thread(new UdpListener(udpSocket)).start();
        new Thread(new ReadWorker(socket, udpSocket)).start();
        new Thread(new WriteWorker(socket, udpSocket, multicastGroup, MULTICAST_PORT)).start();
        new Thread(new MulticastListener(multicastSocket)).start();
    }

    private void closeConnection(Socket socket, DatagramSocket udpSocket, MulticastSocket multicastSocket) {
        try {
            if (!socket.isClosed()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("quit");
                socket.close();
                udpSocket.close();
                multicastSocket.leaveGroup(InetAddress.getByName(MULTICAST_ADDRESS));
                multicastSocket.close();
                System.out.println("Connection closed by client.");
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        }
    }
}
