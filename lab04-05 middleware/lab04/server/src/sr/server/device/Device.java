package server.device;

import SmartHome.DeviceOperationException;
import SmartHome.DeviceStatus;
import SmartHome.IDevice;
import SmartHome.SmarthomeException;
import SmartHome.DeviceInfo;
import com.zeroc.Ice.Current;

public class Device implements IDevice {
    private DeviceInfo deviceInfo;

    private DeviceStatus status;

    public Device(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        this.status = DeviceStatus.Disabled;
    }

    @Override
    public DeviceStatus getStatus(Current current) throws SmarthomeException {
        return this.status;
    }

    @Override
    public DeviceStatus turnOn(Current current) throws DeviceOperationException {
        if (this.status == DeviceStatus.Enabled || this.status == DeviceStatus.Starting) {
            throw new DeviceOperationException("Device is already on or is starting.", "turnOn");
        }

            try {
                System.out.println("Device " + this.deviceInfo.name + " is starting...");
                this.status = DeviceStatus.Starting;
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Device " + this.deviceInfo.name + " is on.");
        this.status = DeviceStatus.Enabled;
        return this.status;
    }

    @Override
    public DeviceStatus turnOff(Current current) throws DeviceOperationException {
        if (this.status == DeviceStatus.Disabled || this.status == DeviceStatus.ShuttingDown) {
            throw new DeviceOperationException("Device is already off or is shutting down.", "turnOff");
        }
        try {
            System.out.println("Device " + this.deviceInfo.name + " is shutting down...");
            this.status = DeviceStatus.ShuttingDown;
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.status = DeviceStatus.Disabled;
        return this.status;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return "\nDevice Details: \n" +
                "Name: " + this.deviceInfo.name + "\n" +
                "Category: " + this.deviceInfo.type + "\n" +
                "Status: " + this.status + "\n";
    }


}
