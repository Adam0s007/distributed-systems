package org.example.client.connection.workers;

import org.example.client.connection.CloseConnectionAction;
import org.example.client.connection.UdpMessageSender;

import java.io.*;
import java.net.*;

import static org.example.util.Config.*;
public class WriteWorker implements Runnable {
    private PrintWriter writer;
    private BufferedReader consoleReader;
    private Socket socket;
    private UdpMessageSender udpMessageSender;
    private DatagramSocket udpSocket;
    private CloseConnectionAction closeConnectionAction;
    private InetAddress multicastGroup;
    private MulticastSocket multicastSocket;

    public WriteWorker(Socket socket, DatagramSocket udpSocket, PrintWriter writer, UdpMessageSender udpMessageSender, InetAddress multicastGroup, MulticastSocket multicastSocket, CloseConnectionAction closeConnectionAction) throws IOException {
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
                    handleCommand(text);
            }
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

    private void handleCommand(String text) throws IOException {
        String command = text.length() > 1 ? text.toUpperCase().substring(0, 2) : text.toUpperCase();
        int port = socket.getLocalPort();
        String messageContent = text.length() > 2 ? text.substring(2) : "";
        String user = "[" + port + "] ";
        String udpMessage = user + messageContent;

        switch (command) {
            case "U":
                sendMultilineUdpMessage();
                break;
            case "U ":
                sendUdpMessage(udpMessage);
                break;
            case "M ":
                sendMulticastMessage(udpMessage);
                break;
            case "T":
                sendAsciiArt(user);
                break;
            case "T ":
                System.out.println("Invalid message format for ASCII Art command.");
                break;
            default:
                sendTcpMessage(text);
                break;
        }
    }

    private void sendMultilineUdpMessage() throws IOException {
        StringBuilder multilineMessage = new StringBuilder();
        System.out.println("Enter multiline message (empty line to send):");
        String line;
        multilineMessage.append("\n");
        while (!(line = consoleReader.readLine()).isEmpty()) {
            multilineMessage.append(line).append("\n");
        }
        String message = "[" + socket.getLocalPort() + "] " + multilineMessage.toString();
        sendUdpMessage(message);
    }
    private void sendAsciiArt(String user) throws IOException {
        System.out.println("Enter the filename: ");
        String filePath = consoleReader.readLine();
        if (!filePath.endsWith(".txt")) {
            System.out.println("Invalid file format. Please enter a .txt file.");
            return;
        }
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder('\n');
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            this.sendUdpMessage(user + '\n' + stringBuilder);
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

