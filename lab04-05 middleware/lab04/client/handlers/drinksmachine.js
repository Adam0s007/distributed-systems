
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleCommonDeviceCommands = require('../commands/devicecommands');
const drinksMachineCommands = require('../commands/drinksmachinecommands');
const prompt = require('prompt-sync')();

const drinksMachineHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, addWater, addSugar, makeHotWater, makeColdWater: ');

    const deviceStatus  = await handleCommonDeviceCommands(command, stub);

    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    await drinksMachineCommands(command, stub);

}

module.exports = drinksMachineHandler;