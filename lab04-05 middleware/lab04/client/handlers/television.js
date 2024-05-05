
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const listAvailableMethods = require('./stub').listAvailableMethods;
const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const prompt = require('prompt-sync')();

const televisionHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;
    
    listAvailableMethods(stub);
    const command = prompt('method >');
    if(await handleDeviceCommands(command, stub))return;
    if(await handleTelevisionCommands(command, stub))return;
    console.log("Unknown command")
}
module.exports = televisionHandler;