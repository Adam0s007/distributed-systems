const { SmartHome } = require("../generated/smarthome");
const prompt = require("prompt-sync")();

const outdoorTelevisionCommands = async (command, stub) => {
    switch (command) {
        case 'setBrightness':
            const level = parseInt(prompt('Enter brightness level (0-100): '));
            try {
                await stub.setBrightness(level);
                console.log('Brightness set successfully.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'waterproofMode':
            const enable = prompt('Enable waterproof mode? (yes/no): ').toLowerCase() === 'yes';
            try {
                const isEnabled = await stub.waterproofMode(enable);
                console.log(isEnabled);
                if (isEnabled) console.log('Waterproof mode enabled.');
                else console.log('Waterproof mode disabled.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

    }
    return false;

}

module.exports = outdoorTelevisionCommands;
