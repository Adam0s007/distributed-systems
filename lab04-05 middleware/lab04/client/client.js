const Ice = require('ice').Ice
const SmartHome = require('./generated/smarthome').SmartHome
const prompt = require('prompt-sync')()
const devices = require('./commons').devices
const servers = require('./commons').config

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
    let smartHome, deviceList;

    try {
        const proxy = communicator.stringToProxy('SmartHome/Adam : ' + servers[1]);
        smartHome = await SmartHome.ISmartHomePrx.checkedCast(proxy);
        deviceList = await smartHome.getDevices();
        console.log('Connected to server: ', servers[1]);
    } catch (error) {
        console.log(`Failed to connect to server ${servers[1]} or retrieve devices:`, error.toString());
        console.log('Trying to connect to server: ', servers[2], '...')
        try {
            const proxy2 = communicator.stringToProxy('SmartHome/Adam : ' + servers[2]);
            smartHome = await SmartHome.ISmartHomePrx.checkedCast(proxy2);
            deviceList = await smartHome.getDevices();
            console.log('Connected to server: ', servers[2]);
        } catch (error2) {
            console.log(`Failed to connect to server ${servers[2]} or retrieve devices:`, error2.toString());
            return false;
        }
    }
    getDeviceList(deviceList);
    return true;
}
const getDeviceList = (deviceList) => {
    console.log('<<<----- Devices ----->>>');
    deviceList.forEach((device) => {
        devices[device.name.toString()] = {type: device.type, server: device.server};
        console.log(`Device: ${device.name} Type: ${device.type} Server: ${device.server}`);
    });
}


const main = async () => {
    const communicator = Ice.initialize()
    console.log(communicator)
    if(!await getDevices(communicator)){
        console.log('Failed to retrieve devices. Exiting...')
        communicator.destroy()
        return
    }

    while (true) {

        console.log('Commands: [device-name] , list, exit')
        deviceName = prompt('> ')
        if (deviceName === 'exit') {
            break
        }

        if (deviceName === 'list') {
            console.log(devices)
            continue
        }

        if (!devices[deviceName]) {
            console.log(`${deviceName} is unreachable`)
            continue
        }
        try {
            console.log(`Device: ${deviceName} Type: ${devices[deviceName].type}`)
            let stringType = String(devices[deviceName].type)
            switch (stringType) {
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
            console.log(e)
        }
    }

    communicator.destroy()
}

main()
