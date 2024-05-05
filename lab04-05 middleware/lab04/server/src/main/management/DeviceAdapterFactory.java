package main.management;

import SmartHome.DeviceDetails;
import SmartHome.DeviceType;
import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import main.device.camera.*;
import main.device.drink.*;
import main.device.television.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceAdapterFactory {
    private Map<String, DeviceDetails> deviceData;

    public DeviceAdapterFactory() {
        this.deviceData = new java.util.HashMap<>();

        addDevice(new DeviceDetails("Sophiax10", DeviceType.MotionDetectionCamera, 1));
        addDevice(new DeviceDetails("Emibrew", DeviceType.CoffeeMachine, 1));
        addDevice(new DeviceDetails("Ultraview900", DeviceType.OutdoorTelevision, 1));
        addDevice(new DeviceDetails("Olidispense", DeviceType.DrinksMachine, 1));
        addDevice(new DeviceDetails("Microncam", DeviceType.Camera, 1));

        addDevice(new DeviceDetails("Jteamaster", DeviceType.TeaMachine, 2));
        addDevice(new DeviceDetails("Jteapro", DeviceType.TeaMachine, 2));
        addDevice(new DeviceDetails("Panozoomx", DeviceType.PTZCamera, 2));
        addDevice(new DeviceDetails("Bellavision", DeviceType.Television, 2));
        addDevice(new DeviceDetails("Cinehomex2", DeviceType.HomeCinemaTV, 2));

        addDevice(new DeviceDetails("Teaelite3", DeviceType.TeaMachine, 3));
        addDevice(new DeviceDetails("Viewfinder", DeviceType.Camera, 3));
        addDevice(new DeviceDetails("Smartscreen", DeviceType.Television, 3));

    }

    public void addDevice(DeviceDetails device) {
        this.deviceData.put(device.name, device);
    }

    public List<DeviceDetails> getDevicesByServer(int server) {
        return deviceData.values().stream().filter(device -> device.server == server).toList();
    }
    public ObjectAdapter createAdapter(int type, Communicator communicator) {
        ObjectAdapter adapter = communicator.createObjectAdapter("Adapter" + type);
        List<DeviceDetails> devices = getDevicesByServer(type);
        devices.forEach(deviceDetails -> servantCreator(adapter, deviceDetails));
        adapter.add(new SmartHome(new ArrayList<>(deviceData.values())), new Identity("Adam", "SmartHome"));
        return adapter;
    }


    private void servantCreator(ObjectAdapter adapter, DeviceDetails deviceInfo) {
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
