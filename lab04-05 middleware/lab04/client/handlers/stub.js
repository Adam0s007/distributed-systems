const { SmartHome } = require('../generated/smarthome');
const { devices, stubs } = require('../commons');
const servers = require("../commons").config;

const getStub = async (name, communicator) => {
    if (stubs[name]) {
        return stubs[name]
    }
    const {type, server} = devices[name]
    const proxy = communicator.stringToProxy(`${type}/${name} : ${servers[server]}`)
    let device
    let stringType = String(type)
    switch (stringType) {
        case 'Camera':
            device = await SmartHome.ICameraPrx.checkedCast(proxy)
            break
        case 'PTZCamera':
            device = await SmartHome.IPTZCameraPrx.checkedCast(proxy)
            break
        case 'MotionDetectionCamera':
            device = await SmartHome.IMotionDetectionCameraPrx.checkedCast(proxy)
            break
        case 'Television':
            device = await SmartHome.ITelevisionPrx.checkedCast(proxy)
            break
        case 'HomeCinemaTV':
            device = await SmartHome.IHomeCinemaTVPrx.checkedCast(proxy)
            break
        case 'OutdoorTelevision':
            device = await SmartHome.IOutdoorTelevisionPrx.checkedCast(proxy)
            break
        case 'DrinksMachine':
            device = await SmartHome.IDrinksMachinePrx.checkedCast(proxy)
            break
        case 'CoffeeMachine':
            device = await SmartHome.ICoffeeMachinePrx.checkedCast(proxy)
            break
        case 'TeaMachine':
            device = await SmartHome.ITeaMachinePrx.checkedCast(proxy)
            break
        default:
            throw new Error(`Unsupported type!: ${type}`)

    }
    stubs[name] = device
    return device
}

module.exports = getStub;