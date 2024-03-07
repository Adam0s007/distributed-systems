package org.example.client.connection;

import org.example.client.connection.workers.MulticastListener;
import org.example.client.connection.workers.ReadWorker;
import org.example.client.connection.workers.UdpListener;
import org.example.client.connection.workers.WriteWorker;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.example.util.Config.*;

public class ConnectionManager {
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    UdpListener udpListener;
    MulticastListener multicastListener;
    ReadWorker readWorker;
    WriteWorker writeWorker;

    UdpMessageSender udpMessageSender;
    public void connect() throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        int port = socket.getLocalPort();

        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
        MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);

        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(udpSocket.getLocalAddress());
        multicastSocket.joinGroup(new InetSocketAddress(multicastGroup, MULTICAST_PORT), networkInterface);


        System.out.println("Connected to chat server");
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeConnection(udpSocket, multicastSocket, socket,writer,multicastGroup)));


        this.udpMessageSender = new UdpMessageSender(multicastGroup, MULTICAST_PORT);

        this.udpListener = new UdpListener(udpSocket);
        this.multicastListener = new MulticastListener(multicastSocket, port, multicastGroup,udpSocket);
        this.readWorker = new ReadWorker(socket,udpSocket,writer,udpMessageSender,multicastGroup,multicastSocket,this::closeConnection);
        this.writeWorker = new WriteWorker(socket,udpSocket,writer,udpMessageSender,multicastGroup,multicastSocket,this::closeConnection);

        List<Future<?>> futures = List.of(
                executorService.submit(udpListener),
                executorService.submit(multicastListener),
                executorService.submit(readWorker),
                executorService.submit(writeWorker)
        );
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("[Futures - ConnectionManager] Error: " + e.getMessage());
            }
        });
        executorService.shutdownNow();
    }

    private void closeConnection(DatagramSocket udpSocket,
                                 MulticastSocket multicastSocket,
                                 Socket socket,
                                 PrintWriter writer,
                                 InetAddress multicastGroup) {
        try {
            udpMessageSender.sendMulticastMessage("[" + socket.getLocalPort() + "] " + "DISCONNECT");
            writer.println("quit");
            udpMessageSender.sendUdpMessage(udpSocket, "DISCONNECT", SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connection closed.");
        } catch(NullPointerException e){
            System.out.println("NullPointerException");
        } catch (IOException e ) {
            System.out.println("Connection closed.");
        } finally {
            try {
                if (multicastSocket != null && !multicastSocket.isClosed()) {
                    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(udpSocket.getLocalAddress());
                    multicastSocket.leaveGroup(new InetSocketAddress(multicastGroup, 0), networkInterface);
                    multicastSocket.close();
                    System.out.println("<CLIENT> Multicast UDP connection closed.");
                }
                if (!socket.isClosed()) {
                    socket.close();
                    System.out.println("<Client> TCP connection closed.");
                }
                if (udpSocket != null && !udpSocket.isClosed()) {
                    udpSocket.close();
                    System.out.println("<Client> UDP connection closed.");
                }
            } catch (IOException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }




}
