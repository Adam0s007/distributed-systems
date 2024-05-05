package server.device.camera;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class MotionDetectionCamera extends Camera implements IMotionDetectionCamera {
    private boolean motionDetectionEnabled;
    public MotionDetectionCamera(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.motionDetectionEnabled = false;
    }

    @Override
    public boolean enableMotionDetection(Current current) throws CameraMotionDetectionException, NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method MotionDetectionCamera.enableMotionDetection with no args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        if(this.motionDetectionEnabled) {
            throw new CameraMotionDetectionException("Motion detection is already enabled", "enableMotionDetection");
        }
        this.motionDetectionEnabled = true;
        return true;
    }

    @Override
    public boolean disableMotionDetection(Current current) throws CameraMotionDetectionException, NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method MotionDetectionCamera.disableMotionDetection with no args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        if(!this.motionDetectionEnabled) {
            throw new CameraMotionDetectionException("Motion detection is already disabled", "disableMotionDetection");
        }
        this.motionDetectionEnabled = false;
        return true;
    }

    @Override
    public String getDetails(Current current) throws NotEnabledException {
        return super.getDetails(current) + "motion detection: " + (motionDetectionEnabled ? "enabled" : "disabled") + "\n";
    }

    @Override
    public void isTurnedOn(Current current) throws NotEnabledException {
        super.isTurnedOn(current);
    }
}
