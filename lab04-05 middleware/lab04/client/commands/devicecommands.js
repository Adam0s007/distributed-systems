
const deviceCommands = async (command, stub) => {
    try {
        let status = String(await stub.getStatus());
        switch (command) {
            case 'getState':
                console.log(`Device status: ${status}`);
                break;
            case 'turnOn':
                if (status === 'Disabled') {
                    const onStatus = String(await stub.turnOn());
                    console.log(`Turned on: ${onStatus}`);
                    status = onStatus;
                } else {
                    console.log('Device is not in a state that allows it to be turned on (must be Disabled).');
                }
                break;
            case 'turnOff':
                if (status === 'Enabled') {
                    const offStatus = String(await stub.turnOff());
                    console.log(`Turned off: ${offStatus}`);
                    status = offStatus;
                } else {
                    console.log('Device is not in a state that allows it to be turned off (must be Enabled).');
                }
                break;
            case 'getDetails':
                const details = String(await stub.getDetails());
                console.log(`${details}`);
                break;
        }
        return status;
    } catch (error) {
        console.log('Error handling device command:', error.toString());
    }
    return null;
}

module.exports = deviceCommands;

