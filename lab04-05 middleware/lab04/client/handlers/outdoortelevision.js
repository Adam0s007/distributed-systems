
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const handleOutdoorTelevision = require('../commands/outdoortelevisioncommands');
const prompt = require('prompt-sync')();

const outdoorTelevisionHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);
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