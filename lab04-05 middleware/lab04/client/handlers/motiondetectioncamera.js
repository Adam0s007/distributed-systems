
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const handleMotionDetectionCameraCommands = require('../commands/motiondetectioncameracommand');
const prompt = require('prompt-sync')();

const motionDetectionCameraHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);
    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, getCameraMode, setCameraMode, enableMotionDetection, disableMotionDetection: ');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    if(await handleCameraCommands(command, stub)) return;
    await handleMotionDetectionCameraCommands(command, stub);
}
module.exports = motionDetectionCameraHandler;