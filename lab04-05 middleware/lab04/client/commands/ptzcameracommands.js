const { SmartHome }  = require("../gen/smarthome");
const prompt = require("prompt-sync")();

const ptzCameraCommands = async (command, stub) => {
    if (command === 'setPtz') {
        const degreesPan = parseInt(prompt('Enter degrees to pan (-180 to 180): '));
        const degreesTilt = parseInt(prompt('Enter degrees to tilt (-180 to 180): '));
        const levelZoom = parseInt(prompt('Enter zoom level (0 to 100): '));
        try {
            const ptzPosition = new SmartHome.PtzPosition(degreesPan, degreesTilt, levelZoom);
            await stub.setPtz(ptzPosition);
            console.log('PTZ position set successfully.');
        } catch (error) {
            console.log(error.message);
        }
        return true;
    }else if(command === 'getPtz'){
        try {
            const ptzPosition = await stub.getPtz();
            console.log(ptzPosition);
        } catch (error) {
            console.log(error.message);
        }
        return true;
    }
    return false;
}

module.exports = ptzCameraCommands;
