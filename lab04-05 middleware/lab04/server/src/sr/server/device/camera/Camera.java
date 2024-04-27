package server.device.camera;

import SmartHome.*;
import com.zeroc.Ice.Current;
import server.device.Device;


public class Camera extends Device implements ICamera {
    private CameraMode cameraMode;

    public Camera(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.cameraMode = CameraMode.Day;
    }

    @Override
    public CameraMode getCameraMode(Current current) {
        System.out.println("Method Camera.getCameraMode with no args called by: " + current.id.name + ", category: " + current.id.category);
        return cameraMode;
    }

    @Override
    public boolean setCameraMode(CameraMode mode, Current current) throws CameraOperationException {
        System.out.println("Method Camera.setCameraMode with args " + mode + " called by: " + current.id.name + ", category: " + current.id.category);
        if (mode == null) {
            throw new CameraOperationException("Invalid camera mode", "setCameraMode");
        }
        this.cameraMode = mode;
        return true;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return super.getDetails(current) + "mode: " + cameraMode + "\n";
    }

}

