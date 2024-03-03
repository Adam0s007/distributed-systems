package org.example.client;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

import static java.lang.System.exit;
import static org.example.Config.SERVER_ADDRESS;
import static org.example.Config.SERVER_PORT;

public class WriteWorker implements Runnable {
    private PrintWriter writer;
    private BufferedReader consoleReader;
    private Socket socket;
    private UdpMessageSender udpMessageSender;
    private DatagramSocket udpSocket;
    private final Semaphore semaphore = new Semaphore(1);
    private MulticastSocket multicastSocket;
    private InetAddress multicastGroup;

    public WriteWorker(Socket socket,DatagramSocket udpSocket, UdpMessageSender udpMessageSender,MulticastSocket multicastSocket,InetAddress multicastGroup) throws IOException {
        this.socket = socket;
        this.udpSocket = udpSocket;
        this.udpMessageSender = udpMessageSender;
        this.multicastGroup = multicastGroup;
        this.multicastSocket = multicastSocket;
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            udpMessageSender.sendUdpMessage(udpSocket, "INIT", SERVER_ADDRESS, SERVER_PORT);
            String text;

            while (!(text = consoleReader.readLine()).equalsIgnoreCase("quit")) {
                String command = text.toUpperCase().substring(0, 1);
                int port = socket.getLocalPort();
                String messageContent = text.substring(2);
                String udpMessage = "[" + port + "] " + messageContent;
                switch (command) {
                    case "U":
                        udpMessageSender.sendUdpMessage(udpSocket, udpMessage, SERVER_ADDRESS, SERVER_PORT);
                        break;
                    case "M":
                        udpMessageSender.sendMulticastMessage(udpMessage);
                        break;
                    default:
                        writer.println(text);
                        System.out.println("TCP message sent.");
                        break;
                }
            }

            closeConnection();
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        }
    }
    private void sendDisconnectMessage() {
        try {
            semaphore.acquire();
            udpMessageSender.sendUdpMessage(udpSocket, "DISCONNECT", SERVER_ADDRESS, SERVER_PORT);
            int port = socket.getLocalPort();
            String udpMessage = "[" + port + "] " + "DISCONNECT";
            udpMessageSender.sendMulticastMessage(udpMessage);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("Error sending DISCONNECT message: " + e.getMessage());
        } finally {
            semaphore.release();
        }
    }

    private void closeConnection() {
            try {
                udpMessageSender.sendUdpMessage(udpSocket, "DISCONNECT", SERVER_ADDRESS, SERVER_PORT);

                int port = socket.getLocalPort();
                String udpMessage = "[" + port + "] " + "DISCONNECT";
                udpMessageSender.sendMulticastMessage(udpMessage);
                writer.println("quit");

                if (udpSocket != null && !udpSocket.isClosed()) {
                    udpSocket.close();
                }
                if (multicastSocket != null && !multicastSocket.isClosed()) {
                    multicastSocket.leaveGroup(multicastGroup);
                    multicastSocket.close();
                }
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                System.out.println("Connection closed by client.");
            } catch (IOException e) {
                System.out.println("Error closing the client: " + e.getMessage());
            }
           // System.out.println("<<<<<<<TCP Writer stopped.>>>>>");
            //exit(0);
        }

    }


