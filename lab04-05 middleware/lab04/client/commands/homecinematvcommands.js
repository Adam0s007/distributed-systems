const { SmartHome }  = require("../gen/smarthome");
const prompt = require("prompt-sync")();

const homeCinemaTVCommands = async (command, stub) => {
    switch (command) {
        case 'setEffect':
            const surroundSound = prompt('Enter surround sound setting: ');
            const pictureMode = prompt('Enter picture mode setting: ');
            const surroundEffect = new SmartHome.SurroundEffect(surroundSound, pictureMode);
            try {
                await stub.setEffect(surroundEffect);
                console.log('Surround effect set successfully.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'getCurrentEffect':
            try {
                const effect = await stub.getCurrentEffect();
                console.log(`Current Surround Effect: Sound - ${effect.surroundSound}, Picture - ${effect.pictureMode}`);
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'getEffects':
            try {
                const effects = await stub.getEffects();
                console.log('Available Surround Effects:');
                effects.forEach((e, index) => console.log(`${index + 1}: Sound - ${e.surroundSound}, Picture - ${e.pictureMode}`));
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'disableSound':
            try {
                await stub.disableSound();
                console.log('Surround sound disabled.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

    }
    return false;

}

module.exports = homeCinemaTVCommands;
