
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;

const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const prompt = require('prompt-sync')();

const televisionHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, setChannel, getCurrentChannel, getChannelList: ');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    await handleTelevisionCommands(command, stub);
}
module.exports = televisionHandler;