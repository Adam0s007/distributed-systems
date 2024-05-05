const { SmartHome } = require("../generated/smarthome");
const prompt = require("prompt-sync")();

const televisionCommands = async (command, stub) => {
    switch (command) {
        case 'setChannel':
            const newChannel = parseInt(prompt('Enter new channel number: '));
            if (isNaN(newChannel)) {
                console.log('Invalid channel number.');
                return true;
            }
            try {
                await stub.setChannel(newChannel);
                console.log('Channel set to', newChannel);
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'getCurrentChannel':
            try {
                const channel = await stub.getCurrentChannel();
                console.log('Current Channel:', channel.name, '-', channel.description);
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'getChannelList':
            try {
                const channelList = await stub.getChannelList();
                console.log('Available Channels:');
                channelList.forEach((ch, index) => console.log(`${index + 1}: ${ch.name} - ${ch.description}`));
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

    }
    return false;

}

module.exports = televisionCommands;
