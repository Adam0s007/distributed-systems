
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const handlePTZCameraCommands = require('../commands/ptzcameracommands');
const prompt = require('prompt-sync')();

const ptzCameraHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, getCameraMode, setCameraMode, setPtz: ');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    if(await handleCameraCommands(command, stub)) return;
    await handlePTZCameraCommands(command, stub);
}
module.exports = ptzCameraHandler;