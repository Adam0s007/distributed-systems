package org.example.util;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

public class UserInfoPrinter {


    public UserInfoPrinter(Socket tcpSocket, DatagramSocket udpSocket, MulticastSocket multicastSocket, InetAddress multicastGroup) {
        String tcpPort = ""+tcpSocket.getLocalPort();
        String udpPort = ""+udpSocket.getLocalPort();
        String multicastInfo = multicastGroup.getHostAddress() + ":" + multicastSocket.getLocalPort();
        printWelcomeMessage(tcpPort,udpPort,multicastInfo);
    }
    public void printWelcomeMessage(String tcpInfo, String udpInfo, String multicastInfo) {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        String green = "\u001B[32m";
        String cyan = "\u001B[36m";
        String yellow = "\u001B[33m";
        String purple = "\u001B[35m";
        String blue = "\u001B[34m";

        System.out.println(green + "Welcome to the chat application!" + reset);
        System.out.println(cyan + "Your TCP port is: " + tcpInfo + reset);
        System.out.println(cyan + "Your UDP port is: " + udpInfo + reset);
        System.out.println(cyan + "Your Multicast address is: " + multicastInfo + reset);
        System.out.println(cyan + "Your ID is: " + tcpInfo + reset);
        System.out.println(yellow + "====================================================" + reset);
        System.out.println("Type '" + purple + "<message>" + reset + "' to send a message indirectly to all other clients via the TCP channel.");
        System.out.println("Type '" + blue + "U <message>" + reset + "' to send a message indirectly to all other clients via the UDP channel.");
        System.out.println("Type '" + green + "M <message>" + reset + "' to send a message directly to all other clients using Multicast.");
        System.out.println("Type '" + yellow + "T" + reset + "' to send an ASCII art message from the file to the server using UDP.");
        System.out.println("Type '" + red + "quit" + reset + "' to exit the application.");
        System.out.println(yellow + "====================================================" + reset);
    }

}
