package org.example.client;

import java.io.*;
import java.net.*;
import static java.lang.System.exit;
import static org.example.Config.SERVER_PORT;
import static org.example.Config.SERVER_ADDRESS;

public class ReadWorker implements Runnable {
    private BufferedReader reader;

    private PrintWriter writer;
    private Socket socket;
    private UdpMessageSender udpMessageSender;
    private DatagramSocket udpSocket;
    private CloseConnectionAction closeConnectionAction;
    private InetAddress multicastGroup;
    private MulticastSocket multicastSocket;


    private volatile boolean running = true;
    public ReadWorker(Socket socket,DatagramSocket udpSocket,PrintWriter writer ,UdpMessageSender udpMessageSender,InetAddress multicastGroup,MulticastSocket multicastSocket,CloseConnectionAction closeConnectionAction
    ) throws IOException {
        this.socket = socket;
        this.udpSocket = udpSocket;
        this.udpMessageSender = udpMessageSender;
        this.writer = writer;
        this.multicastGroup = multicastGroup;
        this.multicastSocket = multicastSocket;
        this.closeConnectionAction = closeConnectionAction;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public void run() {
            try {
                while (running) {
                    String response = reader.readLine();
                    if (response == null) break;
                    System.out.println(response);
                    if (response.contains("DISCONNECT")) {
                        running = false;
                        break;
                    }
                    else if(response.contains("SERVERSHUTDOWN")){
                        closeConnection();
                    }
                }
            } catch (IOException e) {
                System.out.println("TCP socket closed.");
            }
        }
    private void closeConnection() {
        if(closeConnectionAction != null) {
            closeConnectionAction.execute(udpSocket, multicastSocket, socket, writer, multicastGroup);
            exit(0);
        }
    }
}