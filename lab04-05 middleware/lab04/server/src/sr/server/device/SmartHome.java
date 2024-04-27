package server.device;

import SmartHome.ISmartHome;
import com.zeroc.Ice.Current;
import SmartHome.DeviceType;
import SmartHome.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

public class SmartHome implements ISmartHome {

    private final List<DeviceInfo> devices = new ArrayList<>();

    public SmartHome() {

        devices.add(new DeviceInfo("Sophia", DeviceType.MotionDetectionCamera, 1));
        devices.add(new DeviceInfo("Emily", DeviceType.CoffeeMachine, 1));
        devices.add(new DeviceInfo("Emma", DeviceType.OutdoorTelevision, 1));
        devices.add(new DeviceInfo("Olivia", DeviceType.DrinksMachine, 1));
        devices.add(new DeviceInfo("Michael", DeviceType.Camera, 1));

        devices.add(new DeviceInfo("James", DeviceType.TeaMachine, 2));
        devices.add(new DeviceInfo("James2", DeviceType.TeaMachine, 2));
        devices.add(new DeviceInfo("John", DeviceType.PTZCamera, 2));
        devices.add(new DeviceInfo("Isabella", DeviceType.Television, 2));
        devices.add(new DeviceInfo("David", DeviceType.HomeCinemaTV, 2));


    }
    public List<DeviceInfo> getDevices(Current current) {
        System.out.println("Method SmartHome.getDevices with no args called by: "+ current.id.name + ", category: "+ current.id.category);
        return this.devices;
    }

    @Override
    public DeviceInfo getDeviceByName(String name, Current current) {
        System.out.println("Method SmartHome.getDeviceByName with args " + name + " called by: "+ current.id.name + ", category: "+ current.id.category);
        return devices.stream().filter(device -> device.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<DeviceInfo> getDevicesByServer(int server, Current current) {
        System.out.println("Method SmartHome.getDevicesByServer with args " + server + " called by: "+ current.id.name + ", category: "+ current.id.category);
        return devices.stream().filter(device -> device.server == server).toList();
    }

    @Override
    public DeviceInfo getDeviceByType(DeviceType type, Current current) {
        System.out.println("Method SmartHome.getDeviceByType with args " + type + " called by: "+ current.id.name + ", category: "+ current.id.category);
        return devices.stream().filter(device -> device.type == type).findFirst().orElse(null);
    }



}
