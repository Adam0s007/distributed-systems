const drinksMachineHandler = require('./drinksmachine');
const cafeMachineHandler = require('./cafemachine');
const teaMachineHandler = require('./teamachine');
const cameraHandler = require('./camera');
const ptzCameraHandler = require('./ptzcamera');
const motionDetectionCameraHandler = require('./motiondetectioncamera');
const televisionHandler = require('./television');
const homeCinemaTVHandler = require('./homecinematv');
const outdoorTelevisionHandler = require('./outdoortelevision');

const deviceHandlers = {
    'DrinksMachine': async (deviceName, communicator) => drinksMachineHandler(deviceName, communicator),
    'CoffeeMachine': async (deviceName, communicator) => cafeMachineHandler(deviceName, communicator),
    'TeaMachine': async (deviceName, communicator) => teaMachineHandler(deviceName, communicator),
    'Camera': async (deviceName, communicator) => cameraHandler(deviceName, communicator),
    'PTZCamera': async (deviceName, communicator) => ptzCameraHandler(deviceName, communicator),
    'MotionDetectionCamera': async (deviceName, communicator) => motionDetectionCameraHandler(deviceName, communicator),
    'Television': async (deviceName, communicator) => televisionHandler(deviceName, communicator),
    'HomeCinemaTV': async (deviceName, communicator) => homeCinemaTVHandler(deviceName, communicator),
    'OutdoorTelevision': async (deviceName, communicator) => outdoorTelevisionHandler(deviceName, communicator)
};

const handleDeviceCommand = async (deviceName, deviceType, communicator) => {
    const handler = deviceHandlers[deviceType];
    if (!handler) {
        console.log(`Invalid ${deviceType}`);
        return;
    }
    try {
        await handler(deviceName, communicator);
    } catch (e) {
        console.log(`Error handling device operation: ${e.toString()}`);
    }
};

module.exports = handleDeviceCommand;