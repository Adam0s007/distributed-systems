
const deviceCommands = async (command, stub) => {
    try {
        let status = String(await stub.getStatus());
        switch (command) {
            case 'getState':
                console.log(`Device status: ${status}`);
                break;
            case 'turnOn':
                try{
                    const onStatus = String(await stub.turnOn());
                    console.log(`Turned on: ${onStatus}`);
                    status = onStatus;
                } catch (e) {
                    console.log(e.message);
                }
                break;
            case 'turnOff':
                try {
                    const offStatus = String(await stub.turnOff());
                    console.log(`Turned off: ${offStatus}`);
                    status = offStatus;
                } catch(e) {
                    console.log(e.message);
                }
                break;
            case 'getDetails':
                const details = String(await stub.getDetails());
                console.log(`${details}`);
                break;
        }
        return status;
    } catch (error) {
        console.log('Error handling device command:', error.message);
    }
    return null;
}

module.exports = deviceCommands;

