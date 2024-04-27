
const motionDetectionCameraCommands = async (command, stub) => {
    switch (command) {
        case 'enableMotionDetection':
            try {
                const result = await stub.enableMotionDetection();
                console.log(result ? 'Motion detection enabled.' : 'Failed to enable motion detection.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

        case 'disableMotionDetection':
            try {
                const result = await stub.disableMotionDetection();
                console.log(result ? 'Motion detection disabled.' : 'Failed to disable motion detection.');
            } catch (error) {
                console.log('Error:', error.message);
            }
            return true;

    }
    return false;

}

module.exports = motionDetectionCameraCommands;
