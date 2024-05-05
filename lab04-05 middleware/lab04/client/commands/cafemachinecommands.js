const { SmartHome, CoffeeStrength, CoffeeType } = require("../generated/smarthome");
const prompt = require("prompt-sync")();

const cafeMachineCommands = async (command, stub) => {
    switch (command) {
        case 'makeCoffee':

           const type = SmartHome.CoffeeType[prompt('Enter coffee type (Espresso, Americano, Latte, Cappuccino, Macchiato): ')]
            if (!SmartHome.CoffeeType[type]) {
                console.log('Unknown coffee type')
                return true;
            }
            const coffeeStrength = SmartHome.CoffeeStrength[prompt('Enter coffee strength (Light, Medium, Strong): ')];
            if (!SmartHome.CoffeeStrength[coffeeStrength]) {
                console.log('Unknown coffee strength');
                return true;
            }

            const milkAmount = parseInt(prompt('Enter milk amount (ml): '));
            if (isNaN(milkAmount)) {
                console.log('Error: Invalid milk amount.');
                return true;
            }
            try {
                await stub.makeCoffee(new SmartHome.Coffee(type, coffeeStrength, milkAmount));
                console.log('Coffee prepared.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'addMilk':
            const milkVolume = parseInt(prompt('Amount of milk (ml): '));
            try {
                await stub.addMilk(milkVolume);
                console.log('Milk added.');
            } catch (error) {
                console.log('Error:',error.message);
            }
            return true;

        case 'addCoffeeBeans':
            const beansAmount = parseInt(prompt('Amount of coffee beans (grams): '));
            try {
                await stub.addCoffeeBeans(beansAmount);
                console.log('Coffee beans added.');
            } catch (error) {
                console.log('Error:',error.message);
            }
            return true;

        case 'getCoffeeList':
            try {
                const coffeeList = await stub.getCoffeeList();
                console.log('Available Coffees:', coffeeList);
            } catch (error) {
                console.log('Error:',error.message);
            }
            return true;
    }
    return false;
}

module.exports = cafeMachineCommands;