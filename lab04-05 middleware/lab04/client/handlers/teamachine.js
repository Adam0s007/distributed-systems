
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const handleDeviceCommands = require('../commands/devicecommands');
const drinksMachineCommands = require('../commands/drinksmachinecommands');
const teaMachineCommands = require('../commands/teamachinecommands');
const prompt = require('prompt-sync')();

const teaMachineHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, addWater, addSugar, makeHotWater, makeColdWater, makeTea, addTeaLeavesOfType, getTeaList:');

    if(await handleDeviceCommands(command, stub))return;
    if(await drinksMachineCommands(command, stub)) return;
    if(await teaMachineCommands(command, stub))return;
    console.log('Unknown command');

}

module.exports = teaMachineHandler;