package org.example.client;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.example.Config.*;

public class ConnectionManager {
    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void connect() throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

        int port = socket.getLocalPort();

        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
        MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
        multicastSocket.joinGroup(multicastGroup);

        System.out.println("Connected to chat server");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeConnection(socket, udpSocket, multicastSocket)));

        executorService.submit(new UdpListener(udpSocket));
        executorService.submit(new ReadWorker(socket, udpSocket));
        executorService.submit(new WriteWorker(socket, udpSocket, multicastGroup, MULTICAST_PORT));
        executorService.submit(new MulticastListener(multicastSocket,port));
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

                executorService.shutdownNow();
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        } finally {
            executorService.shutdownNow(); // Elegancko zamykamy wszystkie uruchomione wątki
        }
    }
}
