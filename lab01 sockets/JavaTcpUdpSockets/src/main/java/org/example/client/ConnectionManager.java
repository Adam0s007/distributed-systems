package org.example.client;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.example.Config.*;

public class ConnectionManager {
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    UdpListener udpListener;
    MulticastListener multicastListener;
    ReadWorker readWorker;
    WriteWorker writeWorker;
    public void connect() throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

        int port = socket.getLocalPort();

        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
        MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
        multicastSocket.joinGroup(multicastGroup);

        System.out.println("Connected to chat server");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeConnection(socket, udpSocket, multicastSocket)));

        UdpMessageSender udpMessageSender = new UdpMessageSender(multicastGroup, MULTICAST_PORT);

        this.udpListener = new UdpListener(udpSocket);
        this.multicastListener = new MulticastListener(multicastSocket, port);
        this.readWorker = new ReadWorker(socket,udpSocket,udpMessageSender);
        this.writeWorker = new WriteWorker(socket,udpSocket,udpMessageSender,multicastSocket,multicastGroup);

        List<Future<?>> futures = List.of(
                executorService.submit(udpListener),
                executorService.submit(multicastListener),
                executorService.submit(readWorker),
                executorService.submit(writeWorker)
        );
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
        executorService.shutdownNow();
    }
    private void closeConnection(Socket socket, DatagramSocket udpSocket, MulticastSocket multicastSocket) {
        try {
            if (socket != null && !socket.isClosed()) {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    out.println("quit"); // Inform server about quitting
                } finally {
                    socket.close();
                }
            }
            if (udpSocket != null && !udpSocket.isClosed()) {
                udpSocket.close();
            }
            if (multicastSocket != null && !multicastSocket.isClosed()) {
                multicastSocket.leaveGroup(InetAddress.getByName(MULTICAST_ADDRESS));
                multicastSocket.close();
            }
            System.out.println("Connection closed by client.");
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        } finally {
            executorService.shutdownNow();
        }
    }

}
