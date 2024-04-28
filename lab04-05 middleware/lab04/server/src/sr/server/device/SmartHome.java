package server.device;

import SmartHome.ISmartHome;
import com.zeroc.Ice.Current;
import SmartHome.DeviceType;
import SmartHome.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

public class SmartHome implements ISmartHome {

    private final List<DeviceInfo> devices = new ArrayList<>();

    public SmartHome(List<DeviceInfo> devices) {

        this.devices.addAll(devices);

    }
    public List<DeviceInfo> getDevices(Current current) {
        System.out.println("Method SmartHome.getDevices with no args, current.id.name: "+ current.id.name + ", category: "+ current.id.category);
        return this.devices;
    }

    @Override
    public DeviceInfo getDeviceByName(String name, Current current) {
        System.out.println("Method SmartHome.getDeviceByName with args, " + name + " current.id.name: "+ current.id.name + ", current.id.category: "+ current.id.category);
        return devices.stream().filter(device -> device.name.equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<DeviceInfo> getDevicesByServer(int server, Current current) {
        System.out.println("Method SmartHome.getDevicesByServer with args, " + server + " current.id.name: "+ current.id.name + ", current.id.category: "+ current.id.category);
        return devices.stream().filter(device -> device.server == server).toList();
    }

    @Override
    public DeviceInfo getDeviceByType(DeviceType type, Current current) {
        System.out.println("Method SmartHome.getDeviceByType with args, " + type + " current.id.name: "+ current.id.name + ", current.id.category: "+ current.id.category);
        return devices.stream().filter(device -> device.type == type).findFirst().orElse(null);
    }



}
