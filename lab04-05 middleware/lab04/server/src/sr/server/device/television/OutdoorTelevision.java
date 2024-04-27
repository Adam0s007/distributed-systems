package server.device.television;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OutdoorTelevision extends Television implements IOutdoorTelevision {
    int brightnessLevel = 50;
    boolean waterproofMode = false;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public OutdoorTelevision(DeviceInfo deviceInfo) {
        super(deviceInfo);
    }

    @Override
    public boolean setBrightness(int level, Current current) throws BrightnessAdjustmentException {
        System.out.println("Method OutdoorTelevision.setBrightness with args " + level + " called by " + current.id.name + ", category: " + current.id.category);

        if(level < 0 || level > 100) {
            throw new BrightnessAdjustmentException("Brightness level must be between 0 and 100","setBrightness");
        }
        //simulate brightness adjustment

        try {
            scheduler.scheduleAtFixedRate(() -> {
                if (this.brightnessLevel != level) {
                    this.brightnessLevel += (brightnessLevel < level) ? 1 : -1;
                    System.out.println("Adjusted brightness to: " + brightnessLevel);
                } else {
                    scheduler.shutdown();
                    System.out.println("Target brightness level reached: " + brightnessLevel);
                }
            }, 0, 200, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new BrightnessAdjustmentException("Error while adjusting brightness level", "setBrightness");
        }
        return true;
    }

    @Override
    public boolean waterproofMode(boolean enable, Current current) throws TelevisionOperationException {
        System.out.println("Method OutdoorTelevision.waterproofMode with args " + enable + " called by " + current.id.name + ", category: " + current.id.category);
        if (enable == waterproofMode) {
            throw new TelevisionOperationException("Waterproof mode is already " + (enable ? "enabled" : "disabled"), "waterproofMode");
        }
        this.waterproofMode = enable;
        return true;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return super.getDetails(current) + "brightness: " + brightnessLevel + ", waterproof mode: " + waterproofMode +" \n";
    }
}
