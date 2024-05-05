const {SmartHome}  = require("../gen/smarthome");
const prompt = require("prompt-sync")();
const drinksMachineCommands = async (command,stub) =>{
    switch (command) {
        case 'addWater':
            const waterAmount = parseInt(prompt('Amount of water (ml): '));
            try {
                await stub.addWater(waterAmount);
                console.log('Water added.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'addSugar':
            const sugarAmount = parseInt(prompt('Amount of sugar (grams): '));
            try {
                await stub.addSugar(sugarAmount);
                console.log('Sugar added.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'makeHotWater':
            const hotWaterAmount = parseInt(prompt('Amount of hot water (ml): '));
            try {
                await stub.makeHotWater(hotWaterAmount);
                console.log('Hot water prepared.');
            } catch (error) {
                console.log(error.message);
            }
            return true;

        case 'makeColdWater':
            const coldWaterAmount = parseInt(prompt('Amount of cold water (ml): '));
            try {
                await stub.makeColdWater(coldWaterAmount);
                console.log('Cold water prepared.');
            } catch (error) {
                console.log(error.message);
            }
            return true;
    }
    return false;

}

module.exports = drinksMachineCommands;