package org.example.server.handlers;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class TcpClientHandler implements Runnable {
    private final Socket socket;
    private String clientId;
    private PrintWriter out;
    private BufferedReader in;
    private final Map<String, PrintWriter> tcpClients;

    public TcpClientHandler(Socket socket, Map<String, PrintWriter> tcpClients) {
        this.socket = socket;
        this.tcpClients = tcpClients;

    }
    @Override
    public void run() {
        try {
            connectUser();
            announceNewUser();
            handleMessages();
        } catch (IOException e) {
            System.out.println("Client handler exception: " + e.getMessage());
        } finally {
            cleanUp();
        }
    }

    private void connectUser() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        clientId = Integer.toString(socket.getPort());
        tcpClients.put(clientId, out);
        out.println("INIT");
    }

    private void announceNewUser() {
        System.out.println("<TCP> New client connected: " + clientId);
        broadcastMessage("NEW USER: " + clientId + " has joined", clientId);
    }

    private void handleMessages() throws IOException {
        String input;
        while ((input = in.readLine()) != null && !input.equalsIgnoreCase("quit")) {
            broadcastMessage(input, clientId);
        }
    }

    private void cleanUp() {
        try {
            if (clientId != null && tcpClients.containsKey(clientId)) {
                System.out.println("<TCP> Client " + clientId + " disconnected");
                tcpClients.get(clientId).println("DISCONNECT");
                tcpClients.remove(clientId);

                }
            broadcastMessage(clientId + " has left", clientId);
        } catch (Exception e) {
            System.out.println("[SERVER - TcpClientHandler] Error closing client socket: " + e.getMessage());
        }
    }

    private void broadcastMessage(String message, String excludeUser) {
        String messageToSend = "[" + excludeUser + "] "+message;
        System.out.println("<TCP> Received message: " + messageToSend);
        tcpClients.forEach((client, writer) -> {
            if (!client.equals(excludeUser)) {
                writer.println(messageToSend);
            }
        });
    }
}

