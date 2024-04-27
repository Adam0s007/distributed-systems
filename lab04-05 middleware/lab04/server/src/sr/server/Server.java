package server;

import SmartHome.DeviceInfo;
import SmartHome.DeviceType;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import server.device.SmartHome;
import server.device.camera.Camera;
import server.device.camera.MotionDetectionCamera;
import server.device.camera.PTZCamera;
import server.device.drink.CoffeeMachine;
import server.device.drink.DrinksMachine;
import server.device.drink.TeaMachine;
import server.device.television.HomeCinemaTV;
import server.device.television.OutdoorTelevision;
import server.device.television.Television;


import java.util.*;


public class Server {
    private ObjectAdapter newAdapter1(Communicator communicator) {
        ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

        MotionDetectionCamera motionDetectionCamera = new MotionDetectionCamera(new DeviceInfo("Sophia", DeviceType.MotionDetectionCamera, 1));
        CoffeeMachine coffeeMachine = new CoffeeMachine(new DeviceInfo("Emily", DeviceType.CoffeeMachine, 1));
        OutdoorTelevision outdoorTelevision = new OutdoorTelevision(new DeviceInfo("Emma", DeviceType.OutdoorTelevision, 1));
        DrinksMachine drinksMachine = new DrinksMachine(new DeviceInfo("Olivia", DeviceType.DrinksMachine, 1));
        Camera camera = new Camera(new DeviceInfo("Michael", DeviceType.Camera, 1));

        adapter.add(motionDetectionCamera, new Identity("Sophia", "MotionDetectionCamera"));
        adapter.add(coffeeMachine, new Identity("Emily", "CoffeeMachine"));
        adapter.add(outdoorTelevision, new Identity("Emma", "OutdoorTelevision"));
        adapter.add(drinksMachine, new Identity("Olivia", "DrinksMachine"));
        adapter.add(camera, new Identity("Michael", "Camera"));

        return adapter;
    }

    private ObjectAdapter newAdapter2(Communicator communicator) {
        ObjectAdapter adapter = communicator.createObjectAdapter("Adapter2");

        TeaMachine teaMachine = new TeaMachine(new DeviceInfo("James", DeviceType.TeaMachine, 2));
        TeaMachine teaMachine2 = new TeaMachine(new DeviceInfo("James2", DeviceType.TeaMachine, 2));
        PTZCamera ptzCamera = new PTZCamera(new DeviceInfo("John", DeviceType.PTZCamera, 2));
        Television television = new Television(new DeviceInfo("Isabella", DeviceType.Television, 2));
        HomeCinemaTV homeCinemaTV = new HomeCinemaTV(new DeviceInfo("David", DeviceType.HomeCinemaTV, 2));

        adapter.add(teaMachine, new Identity("James", "TeaMachine"));
        adapter.add(teaMachine2, new Identity("James2", "TeaMachine"));
        adapter.add(ptzCamera, new Identity("John", "PTZCamera"));
        adapter.add(television, new Identity("Isabella", "Television"));
        adapter.add(homeCinemaTV, new Identity("David", "HomeCinemaTV"));

        return adapter;
    }
    public void run(String[] args) {
        System.out.println("Server started");
        Arrays.stream(args).forEach(System.out::println);

        int type = Integer.parseInt(args[1].split("=")[1]);
        Communicator communicator = null;
        int status = 0;


        try {
            communicator = Util.initialize(new String[]{args[0]});

            ObjectAdapter adapter;
            switch (type) {
                case 1:
                    adapter = newAdapter1(communicator);
                    break;
                case 2:
                    adapter = newAdapter2(communicator);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type");
            }
            adapter.add(new SmartHome(), new Identity("Adam", "SmartHome"));
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


