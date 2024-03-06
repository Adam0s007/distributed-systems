package org.example.client;

import java.io.*;
import java.net.*;

import static org.example.Config.SERVER_ADDRESS;
import static org.example.Config.SERVER_PORT;

public class WriteWorker implements Runnable {
    private PrintWriter writer;
    private BufferedReader consoleReader;
    private Socket socket;
    private UdpMessageSender udpMessageSender;
    private DatagramSocket udpSocket;
    private CloseConnectionAction closeConnectionAction;
    private InetAddress multicastGroup;
    private MulticastSocket multicastSocket;

    public WriteWorker(Socket socket,DatagramSocket udpSocket,PrintWriter writer ,UdpMessageSender udpMessageSender,InetAddress multicastGroup,MulticastSocket multicastSocket,CloseConnectionAction closeConnectionAction
                        ) throws IOException {
        this.socket = socket;
        this.udpSocket = udpSocket;
        this.udpMessageSender = udpMessageSender;
        this.writer = writer;

        this.multicastGroup = multicastGroup;
        this.multicastSocket = multicastSocket;
        this.closeConnectionAction = closeConnectionAction;
        this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            udpMessageSender.sendUdpMessage(udpSocket, "INIT", SERVER_ADDRESS, SERVER_PORT);
            String text;

            while (!(text = consoleReader.readLine()).equalsIgnoreCase("quit")) {
                String command = text.toUpperCase().substring(0, 1);
                if (text.length() < 2 && ( command.equals("U") || command.equals("M"))) {
                    System.out.println("Invalid message format");
                    continue;
                }
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
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        if(closeConnectionAction != null) {
            closeConnectionAction.execute(udpSocket, multicastSocket, socket, writer, multicastGroup);
        }
    }

}


