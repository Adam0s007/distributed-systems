const Ice = require('ice').Ice
const SmartHome = require('./generated/smarthome').SmartHome
const prompt = require('prompt-sync')()
const devices = require('./config').devices
const servers = require('./config').servers

const drinksMachineHandler = require('./handlers/drinksmachine');
const cafeMachineHandler = require('./handlers/cafemachine');
const teaMachineHandler = require('./handlers/teamachine');
const cameraHandler = require('./handlers/camera');
const ptzCameraHandler = require('./handlers/ptzcamera');
const motionDetectionCameraHandler = require('./handlers/motiondetectioncamera');
const televisionHandler = require('./handlers/television');
const homeCinemaTVHandler = require('./handlers/homecinematv');
const outdoorTelevisionHandler = require('./handlers/outdoortelevision');

const getDevices = async (communicator) => {
    let smartHome, deviceList = null;
    let allServersFailed = true;

    for (let [serverKey, serverAddress] of Object.entries(servers)) {
        try {
            const proxy = communicator.stringToProxy(`SmartHome/Adam : ${serverAddress}`);
            smartHome = await SmartHome.ISmartHomePrx.checkedCast(proxy);

            if (!deviceList) {
                smartHome = await SmartHome.ISmartHomePrx.checkedCast(proxy);
                deviceList = await smartHome.getDevices();
                retrieveDeviceList(deviceList);
            }
            updateDeviceStatus("Online", serverKey);
            // console.log('Connected to server:', serverAddress);
            allServersFailed = false;
        } catch (error) {
            console.log(`Failed to connect to server ${serverAddress} or retrieve devices:`, error.toString());
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

const main = async () => {
    const communicator = Ice.initialize()
    console.log(communicator)
    if(!await getDevices(communicator)){
        console.log('Exiting...');
        communicator.destroy();
        return;
    };
    displayDevices(devices);
    let deviceName;
    while ((deviceName = prompt('Commands: [device-name] , list, exit >')) !== 'exit') {

        if (deviceName === 'list') {
            displayDevices(devices)
            continue
        }
        if (!devices[deviceName]) {
            console.log(`${deviceName} is unreachable`)
            continue
        }
        try {
            console.log(`Device: ${deviceName} Type: ${devices[deviceName].type}`)
            switch (String(devices[deviceName].type)) {
                case 'DrinksMachine':
                    await drinksMachineHandler(deviceName, communicator)
                    break
                case 'CoffeeMachine':
                    await cafeMachineHandler(deviceName, communicator)
                    break
                case 'TeaMachine':
                    await teaMachineHandler(deviceName, communicator)
                    break
                case 'Camera':
                    await cameraHandler(deviceName, communicator)
                    break
                case 'PTZCamera':
                    await ptzCameraHandler(deviceName, communicator)
                    break
                case 'MotionDetectionCamera':
                    await motionDetectionCameraHandler(deviceName, communicator)
                    break
                case 'Television':
                    await televisionHandler(deviceName, communicator)
                    break
                case 'HomeCinemaTV':
                    await homeCinemaTVHandler(deviceName, communicator)
                    break
                case 'OutdoorTelevision':
                    await outdoorTelevisionHandler(deviceName, communicator)
                    break
            }
        } catch (e) {
            console.log(e.toString())
            console.log(e.message)
        }
    }
    communicator.destroy()
}

main()
