
const motionDetectionCameraCommands = async (command, stub) => {
    switch (command) {
        case 'enableMotionDetection':
            try {
                await stub.enableMotionDetection();
                console.log('Motion detection enabled.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'disableMotionDetection':
            try {
                await stub.disableMotionDetection();
                console.log('Motion detection disabled.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

    }
    return false;

}

module.exports = motionDetectionCameraCommands;
