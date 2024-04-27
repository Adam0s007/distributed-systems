const { SmartHome } = require("../generated/smarthome");
const prompt = require("prompt-sync")();

const ptzCameraCommands = async (command, stub) => {
    if (command === 'setPtz') {
        const degreesPan = parseInt(prompt('Enter degrees to pan (-180 to 180): '));
        if (isNaN(degreesPan) || degreesPan < -180 || degreesPan > 180) {
            console.log('Invalid input for pan degrees. Please enter a number between -180 and 180.');
            return true;
        }

        const degreesTilt = parseInt(prompt('Enter degrees to tilt (-180 to 180): '));
        if (isNaN(degreesTilt) || degreesTilt < -180 || degreesTilt > 180) {
            console.log('Invalid input for tilt degrees. Please enter a number between -180 and 180.');
            return true;
        }

        const levelZoom = parseInt(prompt('Enter zoom level (0 to 100): '));
        if (isNaN(levelZoom) || levelZoom < 0 || levelZoom > 100) {
            console.log('Invalid input for zoom level. Please enter a number between 0 and 100.');
            return true;
        }

        try {
            const ptzPosition = new SmartHome.PtzPosition(degreesPan, degreesTilt, levelZoom);
            const result = await stub.setPtz(ptzPosition);
            console.log(result ? 'PTZ position set successfully.' : 'Failed to set PTZ position.');
        } catch (error) {
            console.log('Error:', error.message);
        }
        return true;
    }
    return false;
}

module.exports = ptzCameraCommands;
