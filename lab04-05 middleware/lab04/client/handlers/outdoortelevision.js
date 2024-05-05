
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const handleOutdoorTelevision = require('../commands/outdoortelevisioncommands');
const prompt = require('prompt-sync')();

const outdoorTelevisionHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, setChannel, getCurrentChannel, getChannelList, setBrightness, waterproofMode:');
    const deviceStatus  = await handleDeviceCommands(command, stub);
    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    if(await handleTelevisionCommands(command, stub)) return;
    await handleOutdoorTelevision(command, stub);

}
module.exports = outdoorTelevisionHandler;