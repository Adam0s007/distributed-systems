package org.example.client;

import java.io.PrintWriter;
import java.net.*;

@FunctionalInterface
public interface CloseConnectionAction {
    void execute(DatagramSocket udpSocket, MulticastSocket multicastSocket, Socket socket, PrintWriter writer, InetAddress multicastGroup);
}

