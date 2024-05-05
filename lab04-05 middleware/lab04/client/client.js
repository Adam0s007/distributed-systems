const Ice = require('ice').Ice
const SmartHome = require('./gen/smarthome').SmartHome

const readline = require('readline');

const devices = require('./config').devices
const servers = require('./config').servers
const clearStub = require('./handlers/stub').clearStub;
const { exit } = require('process');

const handleDeviceCommand = require('./handlers/mainhandler');

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
    console.log('----------------------------------------------------------------------');
    console.log('| Device Name   | Device Type              | Server   | Connection   |');
    console.log('----------------------------------------------------------------------');
    Object.entries(devices).forEach(([name, info]) => {
        const deviceType = info.type._name;
        const server = info.server;
        const connection = info.connection;
        console.log(`| ${name.padEnd(13)} | ${deviceType.padEnd(24)} | ${server.toString().padEnd(8)} | ${connection.padEnd(12)} |`);
    });
    console.log('----------------------------------------------------------------------');
}



const updateConnections = async (communicator) => {
    //console.log('Checking device connections every 30 seconds...');
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
            console.log(`${deviceName} not found. Type 'list' to see available devices.`);
            rl.prompt();
            return;
        }

        await handleDeviceCommand(deviceName, device.type, communicator);
        rl.prompt();
    });
};

main();
