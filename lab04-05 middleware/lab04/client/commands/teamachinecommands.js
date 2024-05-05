const { SmartHome, TeaType }  = require("../gen/smarthome");
const prompt = require("prompt-sync")();

const teaMachineCommands = async (command, stub) => {
    switch (command) {
        case 'makeTea':
            const teaTypeInput = prompt('Enter tea type (Black, Green, Herbal, Oolong): ');
            const teaType = SmartHome.TeaType[teaTypeInput];
            if (!SmartHome.TeaType[teaTypeInput]) {  // Checking directly with the user's input
                console.log('Unknown tea type');
                return true;
            }

            const leavesAmount = parseInt(prompt('Amount of leaves (grams): '));
            if (isNaN(leavesAmount)) {
                console.log('Error: Invalid amount of leaves.');
                return true;
            }

            try {
                await stub.makeTea(new SmartHome.Tea(teaType, leavesAmount));
                console.log('Tea prepared.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'addTeaLeavesOfType':
            const typeInput = prompt('Enter tea type for leaves addition (Black, Green, Herbal, Oolong): ');
            const type = SmartHome.TeaType[typeInput];
            if (!SmartHome.TeaType[typeInput]) {
                console.log('Unknown tea type');
                return true;
            }

            const amountToAdd = parseInt(prompt('Amount of leaves to add (grams): '));
            if (isNaN(amountToAdd)) {
                console.log('Error: Invalid amount of leaves.');
                return true;
            }

            try {
                await stub.addTeaLeavesOfType(type, amountToAdd);
                console.log('Tea leaves added.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'getTeaList':
            try {
                const teaList = await stub.getTeaList();
                console.log('Available Teas:', teaList);
            } catch (error) {
                console.log(error.message);
            }
            return true;

    }
    return false;

}

module.exports = teaMachineCommands;
