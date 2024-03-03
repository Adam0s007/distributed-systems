package org.example.client;
import java.io.*;
import java.net.*;
import static java.lang.System.exit;
import static org.example.Config.SERVER_PORT;
import static org.example.Config.SERVER_ADDRESS;
public class WriteWorker implements Runnable {
    private PrintWriter writer;
    private BufferedReader consoleReader;
    private Socket socket;
    private DatagramSocket udpSocket;
    private InetAddress multicastGroup;
    private int multicastPort;


    public WriteWorker(Socket socket,
                       DatagramSocket udpSocket,
                       InetAddress multicastGroup, int multicastPort) throws IOException {
        this.socket = socket;
        this.udpSocket = udpSocket;
        this.multicastGroup = multicastGroup;
        this.multicastPort = multicastPort;
        writer = new PrintWriter(socket.getOutputStream(), true);
        consoleReader = new BufferedReader(new InputStreamReader(System.in));

    }
    @Override
    public void run() {
        try {
            sendUdpMessage("INIT");
            String text;
            while (!(text = consoleReader.readLine()).equalsIgnoreCase("quit")) {
                int port = socket.getLocalPort();
                if (text.toUpperCase().startsWith("U ")) {
                    sendUdpMessage("\n["+port + "]: " + text.substring(2));
                } else if (text.toUpperCase().startsWith("M ")) {
                    sendMulticastMessage("\n["+port + "]: " + text.substring(2));
                } else {
                    System.out.println("Sending standard TCP message.");
                    writer.println(text);
                }
            }
            closeConnection();
        } catch (IOException e) {
            System.out.println("Error writing to server: " + e.getMessage());
        }
    }
    private void sendUdpMessage(String message) throws IOException {
        String prefixedMessage = "U: " + message;
        if(message =="INIT")
            prefixedMessage = "INIT";
        byte[] buffer = prefixedMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        udpSocket.send(packet);
        System.out.println("UDP message sent: " + message);
    }

    private void sendMulticastMessage(String message) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastGroup, multicastPort);
        udpSocket.send(packet);
        System.out.println("Multicast message sent.");
    }
    private void closeConnection() {
        try {
            if (!socket.isClosed()) {
                writer.println("quit");
                socket.close();
                System.out.println("Connection closed by client.");
                exit(0);
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        }
    }
}
