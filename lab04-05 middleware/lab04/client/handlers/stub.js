const { SmartHome } = require('../generated/smarthome');
const { devices, stubs } = require('../config');
const servers = require("../config").servers;

async function getDeviceProxy(type, proxy) {
    const deviceTypePrxMap = {
        'Camera': SmartHome.ICameraPrx,
        'PTZCamera': SmartHome.IPTZCameraPrx,
        'MotionDetectionCamera': SmartHome.IMotionDetectionCameraPrx,
        'Television': SmartHome.ITelevisionPrx,
        'HomeCinemaTV': SmartHome.IHomeCinemaTVPrx,
        'OutdoorTelevision': SmartHome.IOutdoorTelevisionPrx,
        'DrinksMachine': SmartHome.IDrinksMachinePrx,
        'CoffeeMachine': SmartHome.ICoffeeMachinePrx,
        'TeaMachine': SmartHome.ITeaMachinePrx
    };

    if (deviceTypePrxMap[type]) {
        return await deviceTypePrxMap[type].checkedCast(proxy);
    } else {
        throw new Error(`Unsupported device type: ${type}`);
    }
}

const stubHandler = async (name, communicator) => {
    if (stubs[name]) {
        return stubs[name];
    }

    const { type, server } = devices[name];
    const proxy = communicator.stringToProxy(`${type}/${name} : ${servers[server]}`);

    try {
        const device = await getDeviceProxy(type, proxy);
        if (device) {
            stubs[name] = device;
            return device;
        } else {
            throw new Error('Failed to retrieve the device proxy.');
        }
    } catch (error) {
        console.error(`Error accessing the device ${name}:`, error.toString());
        return null;
    }
}

module.exports = stubHandler;
