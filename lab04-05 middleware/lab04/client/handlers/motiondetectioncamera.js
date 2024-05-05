
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const handleMotionDetectionCameraCommands = require('../commands/motiondetectioncameracommand');
const prompt = require('prompt-sync')();

const motionDetectionCameraHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, getCameraMode, setCameraMode, enableMotionDetection, disableMotionDetection: ');
    if(await handleDeviceCommands(command, stub)) return;
    if(await handleCameraCommands(command, stub)) return;
    if(await handleMotionDetectionCameraCommands(command, stub))return;
    console.log("Unknown command")
}
module.exports = motionDetectionCameraHandler;