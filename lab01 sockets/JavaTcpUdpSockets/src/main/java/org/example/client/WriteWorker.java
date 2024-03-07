package org.example.client;

import java.io.*;
import java.net.*;

import static org.example.Config.*;

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
                String command = text.length() > 1 ? text.toUpperCase().substring(0, 2) : text.toUpperCase();
                int port = socket.getLocalPort();
                String messageContent = text.length() > 2 ? text.substring(2) : "";
                String user = "[" + port + "] ";
                String udpMessage = user + messageContent;

                if (command.equals("U ") || command.equals("M ")) {
                    if (command.equals("U ")) {
                        this.sendUdpMessage(udpMessage);
                    } else if (command.equals("M ")) {
                        this.sendMulticastMessage(udpMessage);
                    }
                } else if (text.toUpperCase().equals("T")) {
                    this.sendAsciiArt(FILE_PATH,user);
                } else if (command.equals("T ")) {
                    System.out.println("Invalid message format for ASCII Art command.");
                } else {
                    this.sendTcpMessage(text);
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void sendAsciiArt(String filePath,String user) throws IOException {
        File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.sendUdpMessage(user + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }


    }

    private void closeConnection() {
        if(closeConnectionAction != null) {
            closeConnectionAction.execute(udpSocket, multicastSocket, socket, writer, multicastGroup);
        }
    }

    private void sendUdpMessage(
            String message
    )  throws IOException {
        udpMessageSender.sendUdpMessage(udpSocket, message, SERVER_ADDRESS, SERVER_PORT);
    }

    private void sendMulticastMessage(
            String message
    )  throws IOException {
        udpMessageSender.sendMulticastMessage(message);
    }

    private void sendTcpMessage(
            String message
    ) {
        writer.println(message);
        System.out.println("TCP message sent.");
    }

}


