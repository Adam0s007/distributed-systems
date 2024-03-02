package org.example;

import java.io.*;
import java.net.*;

import static java.lang.System.exit;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static final String MULTICAST_ADDRESS = "230.0.0.0"; // Przykładowy adres grupowy
    private static final int MULTICAST_PORT = 4446; // Przykładowy port dla multicast


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress multicastGroup = InetAddress.getByName(MULTICAST_ADDRESS);
        MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT);
        multicastSocket.joinGroup(multicastGroup);

        System.out.println("Connected to chat server");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeConnection(socket, udpSocket, multicastSocket)));

        new Thread(new UdpListener(udpSocket)).start(); // Nasłuchiwanie na wiadomości UDP
        new Thread(new ReadThread(socket, udpSocket)).start();
        new Thread(new WriteThread(socket, udpSocket, multicastGroup, MULTICAST_PORT)).start();
        new Thread(new MulticastListener(multicastSocket)).start(); // Dodane nasłuchiwanie na wiadomości Multicast
    }

    private static void closeConnection(Socket socket, DatagramSocket udpSocket, MulticastSocket multicastSocket) {
        try {
            if (!socket.isClosed()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("quit");
                socket.close();
                udpSocket.close();
                multicastSocket.leaveGroup(InetAddress.getByName(MULTICAST_ADDRESS));
                multicastSocket.close();
                System.out.println("Connection closed by client.");
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        }
    }

    private static class ReadThread implements Runnable {
        private BufferedReader reader;
        private Socket socket; // Dodajemy referencję do gniazda
        private DatagramSocket udpSocket;
        public ReadThread(Socket socket,DatagramSocket udpSocket) throws IOException {
            this.udpSocket = udpSocket;
            this.socket = socket; // Przypisujemy gniazdo przekazane z konstruktora
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        public void run() {
            while (true) {
                try {
                    String response = reader.readLine();
                    if (response == null) break; // Zerwanie połączenia przez serwer
                    System.out.println(response);
                    if (response.startsWith("NAMEACCEPTED")) {
                        sendUdpMessage("INIT"); // Wysłanie wiadomości INIT przez UDP
                    }
                    if (response.equals("Server is closing. Goodbye!")) {
                        closeConnection(); // Zamknięcie połączenia po stronie klienta
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("Error reading from server: " + e.getMessage());
                    break;
                }
            }
        }

        private void closeConnection() { // Metoda do bezpiecznego zamykania połączenia
            try {
                if (!socket.isClosed()) {
                    socket.close();
                    System.out.println("Connection closed by server.");
                    exit(0); // Zamknięcie aplikacji
                }
            } catch (IOException e) {
                System.out.println("Error closing the client: " + e.getMessage());
            }
        }
        private void sendUdpMessage(String message) throws IOException {
            byte[] buffer = message.getBytes();
            InetAddress address = InetAddress.getByName(SERVER_ADDRESS);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, SERVER_PORT);
            udpSocket.send(packet);
            System.out.println("UDP message sent: " + message);
        }
    }

    private static class WriteThread implements Runnable {
        private PrintWriter writer;
        private BufferedReader consoleReader;
        private Socket socket;
        private DatagramSocket udpSocket;
        private InetAddress multicastGroup;
        private int multicastPort;

        public WriteThread(Socket socket, DatagramSocket udpSocket, InetAddress multicastGroup, int multicastPort) throws IOException {
            this.socket = socket;
            this.udpSocket = udpSocket;
            this.multicastGroup = multicastGroup;
            this.multicastPort = multicastPort;
            writer = new PrintWriter(socket.getOutputStream(), true);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
        }

        public void run() {
            try {
                System.out.println("Enter your name: ");
                String userName = consoleReader.readLine().trim();
                writer.println(userName);
                String text;
                while (!(text = consoleReader.readLine()).equalsIgnoreCase("quit")) {
                    if (text.toUpperCase().startsWith("U ")) { // Obsługa UDP
                        sendUdpMessage("\n["+userName + "]: " + text.substring(2)); // Usunięcie prefiksu i wysłanie reszty jako wiadomość
                    } else if (text.toUpperCase().startsWith("M ")) { // Obsługa Multicast
                        sendMulticastMessage("\n["+userName + "]: " + text.substring(2)); // Usunięcie prefiksu i wysłanie reszty jako wiadomość
                    } else {
                        System.out.println("Sending standard TCP message.");
                        writer.println(text); // Wysyłanie standardowej wiadomości TCP
                    }
                }
                closeConnection();
            } catch (IOException e) {
                System.out.println("Error writing to server: " + e.getMessage());
            }
        }

        private void sendUdpMessage(String message) throws IOException {
            String prefixedMessage = "U: " + message; // Dodaj prefiks do wiadomości
            byte[] buffer = prefixedMessage.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
            udpSocket.send(packet);
            System.out.println("UDP message sent: " + message);
        }


        private void sendMulticastMessage(String message) throws IOException {
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, multicastGroup, multicastPort);
            udpSocket.send(packet); // Wysyłanie wiadomości jako UDP, ale do grupy multicast
            System.out.println("Multicast message sent.");
        }

        private void closeConnection() { // Metoda do bezpiecznego zamykania połączenia
            try {
                if (!socket.isClosed()) {
                    writer.println("quit"); // Inform server about disconnection
                    socket.close();
                    System.out.println("Connection closed by client.");
                    exit(0); // Zamknięcie aplikacji
                }
            } catch (IOException e) {
                System.out.println("Error closing the client: " + e.getMessage());
            }
        }
    }
    private static class UdpListener implements Runnable {
        private DatagramSocket udpSocket;

        public UdpListener(DatagramSocket udpSocket) {
            this.udpSocket = udpSocket;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    udpSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("UDP message received: " + received);
                } catch (IOException e) {
                    System.out.println("UDP socket closed.");
                    break;
                }
            }
        }
    }

    private static class MulticastListener implements Runnable {
        private MulticastSocket multicastSocket;
        public MulticastListener(MulticastSocket multicastSocket) {
            this.multicastSocket = multicastSocket;
        }
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    multicastSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Multicast message received: " + received);
                } catch (IOException e) {
                    System.out.println("Multicast socket closed.");
                    break;
                }
            }
        }
    }

}



