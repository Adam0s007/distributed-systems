
const { SmartHome } = require('../generated/smarthome');
const getStub = require('./stub');
const handleCommonDeviceCommands = require('../commands/devicecommands');
const drinksMachineCommands = require('../commands/drinksmachinecommands');
const coffeeMachineCommands = require('../commands/cafemachinecommands');
const prompt = require('prompt-sync')();

const coffeeMachineHandler = async (name, communicator) => {
    const stub = await getStub(name, communicator);

    const command = prompt('Commands: getState, getDetails, turnOn, turnOff, addWater, addSugar, makeHotWater, makeColdWater, makeCoffee, addMilk, addCoffeeBeans, getCoffeeList:');

    const deviceStatus  = await handleCommonDeviceCommands(command, stub);

    if (deviceStatus !== 'Enabled') {
        console.log('Device is not enabled.');
        return;
    }
    if(await drinksMachineCommands(command, stub)) return;
    await coffeeMachineCommands(command, stub);

}

module.exports = coffeeMachineHandler;