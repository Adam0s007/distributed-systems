package server.device.television;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OutdoorTelevision extends Television implements IOutdoorTelevision {
    int brightnessLevel = 50;
    boolean waterproofMode = false;
    public OutdoorTelevision(DeviceInfo deviceInfo) {
        super(deviceInfo);
    }

    @Override
    public boolean setBrightness(int level, Current current) throws BrightnessAdjustmentException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method OutdoorTelevision.setBrightness with args " + level + ", current.id.name " + current.id.name + ", current.id.category: " + current.id.category);

        if(level < 0 || level > 100) {
            throw new BrightnessAdjustmentException("Brightness level must be between 0 and 100","setBrightness");
        }
        this.brightnessLevel = level;
        return true;
    }

    @Override
    public boolean waterproofMode(boolean enable, Current current) throws TelevisionOperationException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method OutdoorTelevision.waterproofMode with args " + enable + ", current.id.name " + current.id.name + ", current.id.category: " + current.id.category);
        if (enable == waterproofMode) {
            throw new TelevisionOperationException("Waterproof mode is already " + (enable ? "enabled" : "disabled"), "waterproofMode");
        }
        this.waterproofMode = enable;
        return enable;
    }

    @Override
    public String getDetails(Current current) throws NotEnabledException {
        return super.getDetails(current) + "brightness: " + brightnessLevel + ", waterproof mode: " + waterproofMode +" \n";
    }

    @Override
    public void isTurnedOn(Current current) throws NotEnabledException {
        super.isTurnedOn(current);
    }
}
