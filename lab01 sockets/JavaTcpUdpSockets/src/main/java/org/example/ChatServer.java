package org.example;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

import static java.lang.System.exit;

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
            exit(0);

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        } finally {
            if (!tcpServerSocket.isClosed())
                tcpServerSocket.close();
            if (!udpServerSocket.isClosed())
                udpServerSocket.close();
        }
    }

    private void handleServerCommands() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            if (scanner.nextLine().trim().equalsIgnoreCase("quit")) {
                closeServer();
                exit(0);
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

            if (udpServerSocket != null && !udpServerSocket.isClosed()) {
                udpServerSocket.close();
                System.out.println("<ChatSServer> UDP connection closed.");
            }
            if (tcpServerSocket != null && !tcpServerSocket.isClosed()) {
                tcpServerSocket.close();
                System.out.println("<ChatServer> TCP connection closed.");
            }
            System.out.println("Server closed.");
        } catch (IOException e) {
            System.out.println("Error closing the server: " + e.getMessage());
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
        }

    }


}
