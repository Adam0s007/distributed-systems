
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const handlePTZCameraCommands = require('../commands/ptzcameracommands');
const prompt = require('prompt-sync')();

const ptzCameraHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);
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