const { SmartHome } = require("../generated/smarthome");
const prompt = require("prompt-sync")();

const outdoorTelevisionCommands = async (command, stub) => {
    switch (command) {
        case 'setBrightness':
            const level = parseInt(prompt('Enter brightness level (0-100): '));
            if (isNaN(level) || level < 0 || level > 100) {
                console.log('Invalid input for brightness level.');
                return true;
            }
            try {
                const result = await stub.setBrightness(level);
                console.log(result ? 'Brightness set successfully.' : 'Failed to set brightness.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'waterproofMode':
            const enable = prompt('Enable waterproof mode? (yes/no): ').toLowerCase() === 'yes';
            try {
                const result = await stub.waterproofMode(enable);
                console.log(result ? 'Waterproof mode enabled.' : 'Failed to enable waterproof mode.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

    }
    return false;

}

module.exports = outdoorTelevisionCommands;
