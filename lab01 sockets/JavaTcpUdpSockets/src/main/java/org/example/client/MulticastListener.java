package org.example.client;


import java.io.IOException;
import java.net.*;

public class MulticastListener implements Runnable{
    private final MulticastSocket multicastSocket;
    private volatile boolean running = true;
    private DatagramSocket udpSocket;
    private InetAddress multicastGroup;
    public MulticastListener(MulticastSocket multicastSocket, int clientPort, InetAddress multicastGroup, DatagramSocket udpSocket) {
        this.multicastSocket = multicastSocket;
        this.clientPort = clientPort;
        this.multicastGroup = multicastGroup;
        this.udpSocket = udpSocket;
    }
    public int clientPort;
    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        try {
        while (running) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    multicastSocket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    if (!messageIsFromSelf(received, clientPort)) {
                        if(!received.contains("DISCONNECT"))
                            System.out.println("Multicast message received: " + received);
                        if(received.contains("SERVERSHUTDOWN")){
                            this.running = false;
                        }
                    }else{
                        if(received.contains("DISCONNECT")){
                            this.running = false;
                        }
                    }
            }
        }catch (IOException e) {
                System.out.println("<MulticastListener> Multicast socket closed.");
        }
//        finally {
//            if(multicastSocket != null && !multicastSocket.isClosed()){
//                try {
//                    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(udpSocket.getLocalAddress());
//                    multicastSocket.leaveGroup(new InetSocketAddress(multicastGroup, 0), networkInterface);
//                } catch (IOException e) {
//                     e.printStackTrace();
//                } catch (NullPointerException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    multicastSocket.close();
//                    System.out.println("<CLIENT> Multicast listener disconnected.");
//                }
//
//            }
//
//        }

    }

    private boolean messageIsFromSelf(String message, int clientPort) {
        int endOfPortIndex = message.indexOf("] ");
        if (endOfPortIndex != -1) {
            try {
                int portInMessage = Integer.parseInt(message.substring(1, endOfPortIndex));
                return portInMessage == clientPort;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

}

