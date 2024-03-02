package org.example;

import java.io.*;
import java.net.*;

import static java.lang.System.exit;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        System.out.println("Connected to chat server");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeConnection(socket)));
        new Thread(new ReadThread(socket)).start();
        new Thread(new WriteThread(socket)).start();
    }
    private static void closeConnection(Socket socket) {
        try {
            if (!socket.isClosed()) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("quit"); // Inform server about disconnection
                socket.close();
                System.out.println("Connection closed by client.");
            }
        } catch (IOException e) {
            System.out.println("Error closing the client: " + e.getMessage());
        }
    }
    private static class ReadThread implements Runnable {
        private BufferedReader reader;
        private Socket socket; // Dodajemy referencję do gniazda

        public ReadThread(Socket socket) throws IOException {
            this.socket = socket; // Przypisujemy gniazdo przekazane z konstruktora
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public void run() {
            while (true) {
                try {
                    String response = reader.readLine();
                    if (response == null) break; // Zerwanie połączenia przez serwer
                    System.out.println(response);
                    // Sprawdzenie czy serwer wysłał wiadomość o zamykaniu
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
    }

    private static class WriteThread implements Runnable {
        private PrintWriter writer;
        private BufferedReader consoleReader;
        private Socket socket; // Dodajemy referencję do gniazda
        public WriteThread(Socket socket) throws IOException {
            this.socket = socket; // Przypisujemy gniazdo przekazane z konstruktora
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
                    writer.println(text);
                }
                closeConnection(); // Wywołujemy teraz bezpośrednio tutaj
            } catch (IOException e) {
                System.out.println("Error writing to server: " + e.getMessage());
            }
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

}
