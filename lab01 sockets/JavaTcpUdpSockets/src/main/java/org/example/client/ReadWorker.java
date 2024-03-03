package org.example.client;

import java.io.*;
import java.net.*;
import static java.lang.System.exit;
import static org.example.Config.SERVER_PORT;
import static org.example.Config.SERVER_ADDRESS;

public class ReadWorker implements Runnable {
    private BufferedReader reader;
    private Socket socket;
    private DatagramSocket udpSocket;
    private UdpMessageSender udpMessageSender;
    private volatile boolean running = true;
    public ReadWorker(Socket socket,DatagramSocket udpSocket,UdpMessageSender udpMessageSender) throws IOException {
        this.udpSocket = udpSocket;
        this.socket = socket;
        this.udpMessageSender = udpMessageSender;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void run() {
        while (running) {
            try {
                String response = reader.readLine();
                if (response == null) break;
                System.out.println(response);
                if (response.contains("DISCONNECT")) {
                    closeConnection();
                    break;
                }
            } catch (IOException e) {
                System.out.println("TCP socket closed.");
                break;
            }
        }
        //System.out.println("<<<<<<<TCP reader stopped.>>>>>");
    }

    private void closeConnection() {
        try {
            if(socket != null && !socket.isClosed()){
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        }
    }
}