package server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;


import java.util.*;


public class Server {

    public void run(String[] args) {
        System.out.println("Server started");
        Arrays.stream(args).forEach(System.out::println);

        int type = Integer.parseInt(args[1].split("=")[1]);
        Communicator communicator = null;
        int status = 0;

        try {
            communicator = Util.initialize(new String[]{args[0]});

            DeviceAdapterFactory factory = new DeviceAdapterFactory();
            ObjectAdapter adapter = factory.createAdapter(type, communicator);
            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }
        }
        System.exit(status);
    }

    public static void main(String[] args) {
        Server app = new Server();
        app.run(args);
    }
}


