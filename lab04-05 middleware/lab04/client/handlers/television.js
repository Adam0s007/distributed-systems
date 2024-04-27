
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const prompt = require('prompt-sync')();

const televisionHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);
    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, setChannel, getCurrentChannel, getChannelList: ');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    await handleTelevisionCommands(command, stub);
}
module.exports = televisionHandler;