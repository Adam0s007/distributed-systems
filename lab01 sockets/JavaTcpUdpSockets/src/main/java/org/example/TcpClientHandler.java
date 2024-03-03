package org.example;

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
        //SEND INIT MESSAGE TO THIS USER
        out.println("INIT");
    }

    private void announceNewUser() {
        System.out.println("<TCP> New client connected: " + clientId);
        broadcastMessage("NEW USER: " + clientId + " has joined", clientId);
    }

    private void handleMessages() throws IOException {
        String input;
        while ((input = in.readLine()) != null && !input.equalsIgnoreCase("quit")) {
            System.out.println("<TCP> Received message: " + input);
            broadcastMessage(input, clientId);
        }
    }

    private void cleanUp() {
        if (clientId != null && tcpClients.containsKey(clientId)) {
            tcpClients.remove(clientId);
            broadcastMessage(clientId + " has left", clientId);
        }
        try {
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing client socket: " + e.getMessage());
        }
    }

    public void broadcastMessage(String message, String excludeUser) {
        String messageToSend = "[" + excludeUser + "]: "+message;
        System.out.println("<TCP> " + messageToSend);
        tcpClients.forEach((client, writer) -> {
            if (!client.equals(excludeUser)) {
                writer.println(messageToSend);
            }
        });
    }
}

