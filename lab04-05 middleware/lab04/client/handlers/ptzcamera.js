
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const handlePTZCameraCommands = require('../commands/ptzcameracommands');
const prompt = require('prompt-sync')();

const ptzCameraHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, getCameraMode, setCameraMode, setPtz: ');
    if(await handleDeviceCommands(command, stub))return;
    if(await handleCameraCommands(command, stub)) return;
    if(await handlePTZCameraCommands(command, stub)) return;
    console.log("Unknown command")
}
module.exports = ptzCameraHandler;