
const stubHandler = require('./stub').stubHandler;
const handleDeviceCommands = require('../commands/devicecommands');
const handleCameraCommands = require('../commands/cameracommands');
const listAvailableMethods = require('./stub').listAvailableMethods;
const prompt = require('prompt-sync')();

const cameraHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    listAvailableMethods(stub);
    const command = prompt('method >');
    if(await handleDeviceCommands(command, stub))return;
    if(await handleCameraCommands(command, stub))return;
    console.log('Unknown command');
}
module.exports = cameraHandler;