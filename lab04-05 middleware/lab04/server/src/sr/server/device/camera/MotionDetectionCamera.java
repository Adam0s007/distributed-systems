package server.device.camera;

import SmartHome.CameraMotionDetectionException;
import SmartHome.DeviceInfo;
import SmartHome.IMotionDetectionCamera;
import SmartHome.SmarthomeException;
import com.zeroc.Ice.Current;

public class MotionDetectionCamera extends Camera implements IMotionDetectionCamera {
    private boolean motionDetectionEnabled;
    public MotionDetectionCamera(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.motionDetectionEnabled = false;
    }

    @Override
    public boolean enableMotionDetection(Current current) throws CameraMotionDetectionException {
        this.motionDetectionEnabled = true;
        return true;
    }

    @Override
    public boolean disableMotionDetection(Current current) throws CameraMotionDetectionException {
        this.motionDetectionEnabled = false;
        return true;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return super.getDetails(current) + "motion detection: " + (motionDetectionEnabled ? "enabled" : "disabled") + "\n";
    }
}
