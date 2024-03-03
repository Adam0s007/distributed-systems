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

    private volatile boolean running = true;
    public ReadWorker(Socket socket,DatagramSocket udpSocket) throws IOException {
        this.udpSocket = udpSocket;
        this.socket = socket;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void run() {
        while (running) {
            try {
                String response = reader.readLine();
                if (response == null) break;
                System.out.println(response);
                if (response.equals("Server is closing. Goodbye!")) {
                    closeConnection();
                    break;
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                break;
            }
        }
    }

    private void closeConnection() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                System.out.println("Connection closed by server.");
                exit(0);
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        }
    }
}