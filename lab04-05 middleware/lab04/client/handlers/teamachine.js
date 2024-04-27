
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleCommonDeviceCommands = require('../commands/devicecommands');
const drinksMachineCommands = require('../commands/drinksmachinecommands');
const teaMachineCommands = require('../commands/teamachinecommands');
const prompt = require('prompt-sync')();

const teaMachineHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, addWater, addSugar, makeHotWater, makeColdWater, makeTea, addTeaLeavesOfType, getTeaList:');

    const deviceStatus  = await handleCommonDeviceCommands(command, stub);

    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    if(await drinksMachineCommands(command, stub)) return;
    await teaMachineCommands(command, stub);

}

module.exports = teaMachineHandler;