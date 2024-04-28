package server;

import SmartHome.DeviceInfo;
import SmartHome.DeviceType;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import server.device.*;
import server.device.camera.*;
import server.device.drink.*;
import server.device.television.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DeviceAdapterFactory {
    // Non-static factory method to create adapters

    private Map<String,DeviceInfo> deviceData;

    public DeviceAdapterFactory() {
        this.deviceData = new java.util.HashMap<>();

        addDevice(new DeviceInfo("Sophia", DeviceType.MotionDetectionCamera, 1));
        addDevice(new DeviceInfo("Emily", DeviceType.CoffeeMachine, 1));
        addDevice(new DeviceInfo("Emma", DeviceType.OutdoorTelevision, 1));
        addDevice(new DeviceInfo("Olivia", DeviceType.DrinksMachine, 1));
        addDevice(new DeviceInfo("Michael", DeviceType.Camera, 1));

        addDevice(new DeviceInfo("James", DeviceType.TeaMachine, 2));
        addDevice(new DeviceInfo("James2", DeviceType.TeaMachine, 2));
        addDevice(new DeviceInfo("John", DeviceType.PTZCamera, 2));
        addDevice(new DeviceInfo("Isabella", DeviceType.Television, 2));
        addDevice(new DeviceInfo("David", DeviceType.HomeCinemaTV, 2));

        addDevice(new DeviceInfo("Allie", DeviceType.TeaMachine, 3));
        addDevice(new DeviceInfo("Marry", DeviceType.Camera, 3));
        addDevice(new DeviceInfo("Marry", DeviceType.Television, 3));
    }

    public void addDevice(DeviceInfo device) {
        this.deviceData.put(device.name, device);
    }

    public List<DeviceInfo> getDevicesByServer(int server) {
        return deviceData.values().stream().filter(device -> device.server == server).toList();
    }
    ObjectAdapter createAdapter(int type, Communicator communicator) {
        ObjectAdapter adapter = communicator.createObjectAdapter("Adapter" + type);
        List<DeviceInfo> devices = getDevicesByServer(type);
        devices.forEach(deviceInfo -> servantCreator(adapter, deviceInfo));
        adapter.add(new SmartHome(new ArrayList<>(deviceData.values())), new Identity("Adam", "SmartHome"));
        return adapter;
    }


    private void servantCreator(ObjectAdapter adapter, DeviceInfo deviceInfo) {
        switch (deviceInfo.type) {
            case MotionDetectionCamera:
                adapter.add(new MotionDetectionCamera(deviceInfo), new Identity(deviceInfo.name, "MotionDetectionCamera"));
                break;
            case CoffeeMachine:
                adapter.add(new CoffeeMachine(deviceInfo), new Identity(deviceInfo.name, "CoffeeMachine"));
                break;
            case OutdoorTelevision:
                adapter.add(new OutdoorTelevision(deviceInfo), new Identity(deviceInfo.name, "OutdoorTelevision"));
                break;
            case DrinksMachine:
                adapter.add(new DrinksMachine(deviceInfo), new Identity(deviceInfo.name, "DrinksMachine"));
                break;
            case Camera:
                adapter.add(new Camera(deviceInfo), new Identity(deviceInfo.name, "Camera"));
                break;
            case TeaMachine:
                adapter.add(new TeaMachine(deviceInfo), new Identity(deviceInfo.name, "TeaMachine"));
                break;
            case PTZCamera:
                adapter.add(new PTZCamera(deviceInfo), new Identity(deviceInfo.name, "PTZCamera"));
                break;
            case Television:
                adapter.add(new Television(deviceInfo), new Identity(deviceInfo.name, "Television"));
                break;
            case HomeCinemaTV:
                adapter.add(new HomeCinemaTV(deviceInfo), new Identity(deviceInfo.name, "HomeCinemaTV"));
                break;
            default:
                throw new IllegalArgumentException("Invalid device type");
        }
    }
}
