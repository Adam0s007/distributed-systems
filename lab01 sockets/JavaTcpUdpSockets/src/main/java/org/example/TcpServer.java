package org.example;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TcpServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 12345;
    private static volatile boolean isRunning = true;
    private static Map<String, PrintWriter> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println("JAVA TCP CHAT SERVER");
        serverSocket = new ServerSocket(PORT);
        Runtime.getRuntime().addShutdownHook(new Thread(TcpServer::closeServer));

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (isRunning) {
                if (scanner.nextLine().trim().equalsIgnoreCase("quit")) {
                    closeServer();
                    System.exit(0);
                }
            }
        }).start();

        try {
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        } finally {
            serverSocket.close();
        }
    }

    private static void closeServer() {
        isRunning = false;
        try {
            for (PrintWriter writer : clients.values()) {
                writer.println("Server is closing. Goodbye!");
                writer.close();
            }
            if (!serverSocket.isClosed()) serverSocket.close();
            System.out.println("Server closed.");
        } catch (IOException e) {
            System.out.println("Error closing the server: " + e.getMessage());
        }
    }

    public static void broadcastMessage(String message, String excludeUser) {
        for (String client : clients.keySet()) {
            if (!client.equals(excludeUser)) {
                clients.get(client).println(message);
            }
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private String clientId;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    clientId = in.readLine();
                    if (clientId == null || clientId.equalsIgnoreCase("quit")) {
                        return;
                    }
                    synchronized (clients) {
                        if (!clients.containsKey(clientId) && !clientId.equalsIgnoreCase("quit")) {
                            clients.put(clientId, out);
                            break;
                        }
                    }
                }

                out.println("NAMEACCEPTED " + clientId);
                broadcastMessage("NEW USER: " + clientId + " has joined", clientId);

                String input;
                while ((input = in.readLine()) != null && !input.equalsIgnoreCase("quit")) {
                    broadcastMessage("[" + clientId + "]: " + input, clientId);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                if (clientId != null) {
                    clients.remove(clientId);
                    broadcastMessage(clientId + " has left", clientId);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
