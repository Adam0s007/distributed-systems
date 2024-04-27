package server.device.camera;

import SmartHome.*;
import com.zeroc.Ice.Current;

public class PTZCamera extends Camera implements IPTZCamera {

    private int pan_attr;
    private int tilt_attr;
    private int zoom_attr;

    public PTZCamera(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.pan_attr = 0;
        this.tilt_attr = 0;
        this.zoom_attr = 50;
    }

    @Override
    public boolean setPtz(PtzPosition ptzPosition, Current current) throws PTZOperationException {
        System.out.println("Method PTZCamera.setPtz called with pan: " + ptzPosition.pan + ", tilt: " + ptzPosition.tilt + ", zoom: " + ptzPosition.zoom + " by: " + current.id.name + ", category: " + current.id.category);

        if (ptzPosition.pan < -180 || ptzPosition.pan > 180) {
            throw new PTZOperationException("Invalid pan degrees", "pan", "degrees");
        }
        if (ptzPosition.tilt < -180 || ptzPosition.tilt > 180) {
            throw new PTZOperationException("Invalid tilt degrees", "tilt", "degrees");
        }
        if (ptzPosition.zoom < 0 || ptzPosition.zoom > 100) {
            throw new PTZOperationException("Invalid zoom level", "zoom", "level");
        }

        try {
            Thread.sleep(800);  // Simulating the time delay for PTZ operation
            this.pan_attr = ptzPosition.pan;
            this.tilt_attr = ptzPosition.tilt;
            this.zoom_attr = ptzPosition.zoom;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PTZCamera: " + super.toString() + ", pan: " + pan_attr + ", tilt: " + tilt_attr + ", zoom: " + zoom_attr;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {
        return super.getDetails(current) + "pan: " + pan_attr + ", tilt: " + tilt_attr + ", zoom: " + zoom_attr + "\n";
    }

}
