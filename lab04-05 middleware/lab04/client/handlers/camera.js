
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const prompt = require('prompt-sync')();

const cameraHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, getCameraMode, setCameraMode:');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    await handleCameraCommands(command, stub);
}
module.exports = cameraHandler;