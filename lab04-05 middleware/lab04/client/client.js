const Ice = require('ice').Ice
const SmartHome = require('./generated/smarthome').SmartHome

const prompt = require('prompt-sync')()
const readline = require('readline');

const devices = require('./config').devices
const servers = require('./config').servers
const clearStub = require('./handlers/stub').clearStub;


const drinksMachineHandler = require('./handlers/drinksmachine');
const cafeMachineHandler = require('./handlers/cafemachine');
const teaMachineHandler = require('./handlers/teamachine');
const cameraHandler = require('./handlers/camera');
const ptzCameraHandler = require('./handlers/ptzcamera');
const motionDetectionCameraHandler = require('./handlers/motiondetectioncamera');
const televisionHandler = require('./handlers/television');
const homeCinemaTVHandler = require('./handlers/homecinematv');
const outdoorTelevisionHandler = require('./handlers/outdoortelevision');

const { exit } = require('process');

const getDevices = async (communicator) => {
    let smartHome, deviceList = null;
    let allServersFailed = true;

    for (let [serverKey, serverAddress] of Object.entries(servers)) {
        try {
            const proxy = communicator.stringToProxy(`SmartHome/Adam : ${serverAddress}`);
            smartHome = await SmartHome.ISmartHomePrx.checkedCast(proxy);
            if (!deviceList) {
                deviceList = await smartHome.getDevices();
                retrieveDeviceList(deviceList);
            }
            updateDeviceStatus("Online", serverKey);
            // console.log('Connected to server:', serverAddress);
            allServersFailed = false;
        } catch (error) {
            console.log(`Failed to connect to server ${serverAddress}`);
        }
    }

    if (allServersFailed) {
        console.log('All server connections failed.');
        return false;
    }
    return true;
}

const retrieveDeviceList = (deviceList) => {
    deviceList.forEach((device) => {
        const deviceName = String(device.name);
        devices[deviceName] = { type: device.type, server: device.server, connection: "Offline" };
    });
}

const updateDeviceStatus = (status, serverAddress) => {
    Object.keys(devices).forEach((deviceName) => {
        //console.log("<<<<",devices[deviceName].server, serverAddress)
        if (String(devices[deviceName].server) === String(serverAddress)) {
            devices[deviceName].connection = status;
            if(status == "Offline"){
                clearStub(deviceName)
            }

        }
    });
}

function displayDevices(devices) {
    console.log('\nList of Devices:');
    console.log('--------------------------------------------------------------------');
    console.log('| Device Name | Device Type            | Server | Connection |');
    console.log('--------------------------------------------------------------------');
    Object.entries(devices).forEach(([name, info]) => {
        const deviceType = info.type._name;
        const server = info.server;
        const connection = info.connection;
        console.log(`| ${name.padEnd(11)} | ${deviceType.padEnd(22)} | ${server.toString().padEnd(6)} | ${connection.padEnd(10)} |`);
    });
    console.log('--------------------------------------------------------------------');
}



const updateConnections = async (communicator) => {
    console.log('Checking device connections every 30 seconds...');
    const intervalId = setInterval(async () => {
        //console.log('Checking device connections...');
        for (let [serverKey, serverAddress] of Object.entries(servers)) {
            try {
                const proxy = communicator.stringToProxy(`SmartHome/Adam : ${serverAddress}`);
                const smtHome = await SmartHome.ISmartHomePrx.checkedCast(proxy);
                if (smtHome) {
                    updateDeviceStatus("Online", serverKey);
                } else {
                    updateDeviceStatus("Offline", serverKey);
                }
            } catch (error) {
                //console.log(`Failed to connect to server ${serverAddress}:`, error.toString());
                updateDeviceStatus("Offline", serverKey);
            }
        }
    }, 30000);
    return intervalId; 
}


const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const main = async () => {
    const communicator = Ice.initialize();
    const initializationSuccess = await getDevices(communicator);
    if (!initializationSuccess) {
        console.log('Initialization failed, exiting...');
        communicator.destroy();
        exit(1);
        
    }

    displayDevices(devices);
    const intervalId = updateConnections(communicator);
    
    console.log('Commands: [device-name], list, exit >');
    rl.prompt();
    rl.on('line', async (input) => {
        
        const deviceName = input.trim();
        console.log(`Received command: ${deviceName}`);

        if (deviceName === 'exit') {
            console.log('Exiting...');
            clearInterval(intervalId);
            try {
                await communicator.destroy();
                console.log('Communicator destroyed.');
            } catch (error) {
                console.log(`Error destroying communicator: ${error}`);
            }
            rl.close();
            console.log('Interface closed.');
            exit(1);
        }

        if (deviceName === 'list') {
            displayDevices(devices);
            rl.prompt();
            return;
        }

        const device = devices[deviceName];
        if (!device) {
            console.log(`${deviceName} is unreachable`);
            rl.prompt();
            return;
        }

        try {
            console.log(`Device: ${deviceName}, Type: ${device.type}`);
            switch (String(device.type)) {
                case 'DrinksMachine':
                    await drinksMachineHandler(deviceName, communicator);
                    break;
                case 'CoffeeMachine':
                    await cafeMachineHandler(deviceName, communicator);
                    break;
                case 'TeaMachine':
                    await teaMachineHandler(deviceName, communicator);
                    break;
                case 'Camera':
                    await cameraHandler(deviceName, communicator);
                    break;
                case 'PTZCamera':
                    await ptzCameraHandler(deviceName, communicator);
                    break;
                case 'MotionDetectionCamera':
                    await motionDetectionCameraHandler(deviceName, communicator);
                    break;
                case 'Television':
                    await televisionHandler(deviceName, communicator);
                    break;
                case 'HomeCinemaTV':
                    await homeCinemaTVHandler(deviceName, communicator);
                    break;
                case 'OutdoorTelevision':
                    await outdoorTelevisionHandler(deviceName, communicator);
                    break;
                default:
                    console.log(`No handler available for type ${device.type}`);
                    break;
            }
        } catch (e) {
            console.log(`Error handling device operation: ${e.toString()}`);
        }
        rl.prompt();
    });
};

main();
