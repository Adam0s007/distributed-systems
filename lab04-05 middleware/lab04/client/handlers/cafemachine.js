
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const handleDeviceCommands = require('../commands/devicecommands');
const drinksMachineCommands = require('../commands/drinksmachinecommands');
const coffeeMachineCommands = require('../commands/cafemachinecommands');
const prompt = require('prompt-sync')();

const coffeeMachineHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, addWater, addSugar, makeHotWater, makeColdWater, makeCoffee, addMilk, addCoffeeBeans, getCoffeeList:');

    if(await handleDeviceCommands(command, stub)) return;
    if(await drinksMachineCommands(command, stub)) return;
    if(await coffeeMachineCommands(command, stub)) return;
    console.log('Unknown command');
}

module.exports = coffeeMachineHandler;