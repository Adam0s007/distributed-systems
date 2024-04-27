const {SmartHome} = require("../generated/smarthome");
const prompt = require("prompt-sync")();
const drinksMachineCommands = async (command,stub) =>{
    switch (command) {
        case 'addWater':
            const waterAmount = parseInt(prompt('Amount of water (ml): '));
            try {
                await stub.addWater(waterAmount);
                console.log('Water added.');
            } catch (error) {
                if (error instanceof SmartHome.WaterCapacityException) {
                    console.log('Error: Exceeded water capacity.');
                } else {
                    console.log('Unhandled error:', error);
                }
            }
            return true;

        case 'addSugar':
            const sugarAmount = parseInt(prompt('Amount of sugar (grams): '));
            try {
                await stub.addSugar(sugarAmount);
                console.log('Sugar added.');
            } catch (error) {
                if (error instanceof SmartHome.SugarCapacityException) {
                    console.log('Error: Exceeded sugar capacity.');
                } else {
                    console.log('Unhandled error:', error);
                }
            }
            return true;

        case 'makeHotWater':
            const hotWaterAmount = parseInt(prompt('Amount of hot water (ml): '));
            try {
                console.log(await stub.makeHotWater(hotWaterAmount) ? 'Hot water prepared.' : 'Failed to make hot water.');
            } catch (error) {
                if (error instanceof SmartHome.WaterCapacityException) {
                    console.log('Error: Exceeded water capacity for hot water.');
                } else {
                    console.log('Unhandled error:', error);
                }
            }
            return true;

        case 'makeColdWater':
            const coldWaterAmount = parseInt(prompt('Amount of cold water (ml): '));
            try {
                console.log(await stub.makeColdWater(coldWaterAmount) ? 'Cold water prepared.' : 'Failed to make cold water.');
            } catch (error) {
                if (error instanceof SmartHome.WaterCapacityException) {
                    console.log('Error: Exceeded water capacity for cold water.');
                } else {
                    console.log('Unhandled error:', error);
                }
            }
            return true;
    }
    return false;

}

module.exports = drinksMachineCommands;