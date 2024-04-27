const { SmartHome, CameraMode } = require("../generated/smarthome");
const prompt = require("prompt-sync")();

const cameraCommands = async (command, stub) => {
    switch (command) {
        case 'getCameraMode':
            try {
                const mode = await stub.getCameraMode();
                console.log('Camera Mode:', mode);
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;
        case 'setCameraMode':
            const cameraMode = SmartHome.CameraMode[prompt('Enter camera mode (Night, Day, Auto): ')];
            if (!SmartHome.CameraMode[cameraMode]) {
                console.log('Unknown camera mode');
                return true;
            }
            try {
                const result = await stub.setCameraMode(cameraMode);
                console.log(result ? 'Camera mode set.' : 'Failed to set camera mode.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

    }
    return false;

}

module.exports = cameraCommands;
