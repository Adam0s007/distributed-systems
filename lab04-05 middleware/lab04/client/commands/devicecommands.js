
const deviceCommands = async (command, stub) => {
    try {
        switch (command) {
            case 'getState':
                try{
                    const state = String(await stub.getStatus());
                    console.log(`Device state: ${state}`);
                } catch(e) {
                    console.log(e.message);
                }
                return true;
            case 'turnOn':
                try{
                    const onStatus = String(await stub.turnOn());
                    console.log(`Turned on: ${onStatus}`);
                    
                } catch (e) {
                    console.log(e.message);
                }
                return true;;
            case 'turnOff':
                try {
                    const offStatus = String(await stub.turnOff());
                    console.log(`Turned off: ${offStatus}`);
                } catch(e) {
                    console.log(e.message);
                }
                return true;;
            case 'getDetails':
                try{
                const details = String(await stub.getDetails());
                console.log(`${details}`);
                } catch(e) {
                    console.log(e.message);
                }
                return true;;
        }
    } catch (error) {
        console.log('Error handling device command:', error.message);
    }
    return false;
}

module.exports = deviceCommands;

