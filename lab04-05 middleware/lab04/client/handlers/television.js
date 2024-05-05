
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;

const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const prompt = require('prompt-sync')();

const televisionHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;
    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, setChannel, getCurrentChannel, getChannelList: ');
    if(await handleDeviceCommands(command, stub))return;
    if(await handleTelevisionCommands(command, stub))return;
    console.log("Unknown command")
}
module.exports = televisionHandler;