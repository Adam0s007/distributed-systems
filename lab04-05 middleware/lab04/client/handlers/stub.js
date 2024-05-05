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

const clearStub = (name) => {
    if (stubs[name]) {
        delete stubs[name];
    }
}



// Ta funkcja przyjmuje stub (proxy object) i wyświetla wszystkie jego metody
function listAvailableMethods(stub) {
    if (!stub) {
        console.log("No stub provided.");
        return;
    }
    const availableMethods = Object.keys(Object.getPrototypeOf(stub))
                                   .filter(key => typeof stub[key] === 'function');
    const methodsList = availableMethods.join(', ');
    console.log("Commands:\n", methodsList);
}

exports.stubHandler = stubHandler;
exports.clearStub = clearStub;
exports.listAvailableMethods = listAvailableMethods;
