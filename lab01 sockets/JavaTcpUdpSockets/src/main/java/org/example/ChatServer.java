package org.example;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private ServerSocket tcpServerSocket;
    private DatagramSocket udpServerSocket;
    private final int PORT;
    private volatile boolean isRunning = true;
    private final Map<String, PrintWriter> tcpClients = new ConcurrentHashMap<>();
    private final Map<String, ClientInfo> udpClients = new ConcurrentHashMap<>();
    private ExecutorService clientExecutor = Executors.newCachedThreadPool();

    public ChatServer(int port) {
        this.PORT = port;
    }

    public void start() throws IOException {


        tcpServerSocket = new ServerSocket(PORT);
        this.udpServerSocket = new DatagramSocket(PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(this::closeServer));
        new Thread(this::handleServerCommands).start();

        System.out.println("<ChatServer> Server listening on port " + PORT);
        try {
            while (isRunning) {


                // Accept new tcp client connections
                Socket clientSocket = tcpServerSocket.accept();
                TcpClientHandler clientHandler = new TcpClientHandler(clientSocket, tcpClients);
                clientExecutor.submit(clientHandler);
                // Accept new udp client connections
                UdpClientHandler udpClientHandler = new UdpClientHandler(udpServerSocket, udpClients);
                clientExecutor.submit(udpClientHandler);

            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        } finally {
            if (!tcpServerSocket.isClosed()) tcpServerSocket.close();
        }
    }

    private void handleServerCommands() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            if (scanner.nextLine().trim().equalsIgnoreCase("quit")) {
                closeServer();
                System.exit(0);
            }
        }
    }

    private void closeServer() {
        isRunning = false;
        try {
            tcpClients.values().forEach(writer -> {
                writer.println("Server is closing. Goodbye!");
                writer.close();
            });
            clientExecutor.shutdownNow();
            if (tcpServerSocket != null && !tcpServerSocket.isClosed()) tcpServerSocket.close();
            System.out.println("Server closed.");
        } catch (IOException e) {
            System.out.println("Error closing the server: " + e.getMessage());
        }
    }

}
