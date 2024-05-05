
const { SmartHome } = require('../generated/smarthome');
const stubHandler = require('./stub').stubHandler;
const listAvailableMethods = require('./stub').listAvailableMethods;
const handleDeviceCommands = require('../commands/devicecommands');
const handleTelevisionCommands = require('../commands/televisioncommands');
const handleHomeCinemaCommands = require('../commands/homecinematvcommands');
const prompt = require('prompt-sync')();

const homeCinemaTVHandler = async (name, communicator) => {
    const stub = await stubHandler(name, communicator);
    if(!stub)return;

    listAvailableMethods(stub);
    const command = prompt('>');
    if(await handleDeviceCommands(command, stub)) return;
    if(await handleTelevisionCommands(command, stub)) return;
    if(await handleHomeCinemaCommands(command, stub)) return;
    console.log("Unknown command")
    

}
module.exports = homeCinemaTVHandler;