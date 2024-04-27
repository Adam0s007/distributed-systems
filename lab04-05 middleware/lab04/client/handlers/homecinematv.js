
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const handleHomeCinemaCommands = require('../commands/homecinematvcommands');
const prompt = require('prompt-sync')();

const homeCinemaTVHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);
    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, setChannel, getCurrentChannel, getChannelList, setEffect, getCurrentEffect, getEffects,disableSound:');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    if(await handleTelevisionCommands(command, stub)) return;
    await handleHomeCinemaCommands(command, stub);

}
module.exports = homeCinemaTVHandler;