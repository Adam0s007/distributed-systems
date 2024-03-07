package org.example.server;

import org.example.server.models.ClientInfo;
import org.example.server.handlers.TcpClientHandler;
import org.example.server.handlers.UdpClientHandler;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

public class ChatServer {
    private ServerSocket tcpServerSocket;
    private DatagramSocket udpServerSocket;
    private final int PORT;
    private volatile boolean isRunning = true;
    private final Map<String, PrintWriter> tcpClients = new ConcurrentHashMap<>();
    private final Map<String, ClientInfo> udpClients = new ConcurrentHashMap<>();
    private List<Future<?>> futures;
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
        this.futures = new ArrayList<>();
        try {
            while (isRunning) {
                Socket clientSocket = tcpServerSocket.accept();
                TcpClientHandler clientHandler = new TcpClientHandler(clientSocket, tcpClients);
                futures.add(clientExecutor.submit(clientHandler));

                UdpClientHandler udpClientHandler = new UdpClientHandler(udpServerSocket, udpClients);
                futures.add(clientExecutor.submit(udpClientHandler));

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleServerCommands() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            if (scanner.nextLine().trim().equalsIgnoreCase("quit")) {
                closeServer();
            }
        }
    }

    private void closeServer() {
        isRunning = false;
        try {
            Map<String, PrintWriter> tmpClients = new ConcurrentHashMap<>(tcpClients);
            tmpClients.values().forEach(writer -> writer.println("SERVERSHUTDOWN"));
            for (Future<?> future : futures) {
                future.get();
            }
            this.clientExecutor.shutdownNow();
            System.out.println("Server closed.");
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (udpServerSocket != null && !udpServerSocket.isClosed()) {
                    udpServerSocket.close();
                    System.out.println("<ChatServer> UDP connection closed.");
                }
                if (tcpServerSocket != null && !tcpServerSocket.isClosed()) {
                    tcpServerSocket.close();
                    System.out.println("<ChatServer> TCP connection closed.");
                }
            } catch (IOException e) {
                System.out.println("Error closing the server: " + e.getMessage());
            }
        }

    }


}
