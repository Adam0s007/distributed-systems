
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const listAvailableMethods = require('./stub').listAvailableMethods;
const handleDeviceCommands = require('../commands/devicecommands');
const drinksMachineCommands = require('../commands/drinksmachinecommands');
const coffeeMachineCommands = require('../commands/cafemachinecommands');

const prompt = require('prompt-sync')();

const coffeeMachineHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;
    
    listAvailableMethods(stub);
    const command = prompt('method >');

    if(await handleDeviceCommands(command, stub)) return;
    if(await drinksMachineCommands(command, stub)) return;
    if(await coffeeMachineCommands(command, stub)) return;
    console.log('Unknown command');
}

module.exports = coffeeMachineHandler;