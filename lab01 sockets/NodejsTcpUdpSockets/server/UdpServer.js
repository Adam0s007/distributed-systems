const dgram = require("dgram");
const { PORT, MULTICAST_ADDRESS } = require("../shared/Constants");

class UdpServer {
  constructor() {
    this.server = dgram.createSocket("udp4");
    this.clientAddresses = new Map(); // { 'address:port': { address, udpPort } ,... }
  }

  start() {
    this.server.bind(PORT, () => {
      console.log(`<UDP> Server listening on port ${PORT}`);
      this.server.setMulticastTTL(128);
      this.server.addMembership(MULTICAST_ADDRESS);
    });

    this.server.on('message', (msg, rinfo) => {
        const messageContent = msg.toString();
        console.log(`<UDP> Received message from ${rinfo.address}:${rinfo.port}: ${messageContent}`);
    
        if (messageContent.startsWith('INIT')) {
            
            this.addClientAddress(rinfo.address, rinfo.port); 
        } else if (messageContent.startsWith('M:')) {
            console.log(`<UDP> Received multicast message: ${messageContent}`);
        } else if (messageContent.startsWith('U:')) {
            const modifiedMessage = messageContent.slice(2);
            const key = `${rinfo.address}:${rinfo.port}`;
            this.distributeMessage(Buffer.from(modifiedMessage), key);
        }else if(messageContent.startsWith('DISCONNECT')){
            console.log(`<UDP> Client ${rinfo.address}:${rinfo.port} disconnected`);
            // console.log("key to remove", `${rinfo.address}:${rinfo.port}`)
            this.clientAddresses.delete(`${rinfo.address}:${rinfo.port}`);
        }
        
        else {
            console.log(`<UDP> Unknown message: ${messageContent}`);
        }
    });
  }

  distributeMessage(message, key) {
    this.clientAddresses.forEach((rinfoObj, keyObj) => {
      if (key !== keyObj) {
        console.log(`<UDP> Sending message to ${rinfoObj.address}:${rinfoObj.udpPort}`);
        this.server.send(message, 0, message.length, rinfoObj.udpPort, rinfoObj.address, (err) => {
            if (err) console.log(`<UDP> Error sending message to ${rinfoObj.address}:${rinfoObj.udpPort}`, err);
        });
      }
    });
  }

  addClientAddress(address, udpPort) {
    const key = `${address}:${udpPort}`;
    // console.log("key to add", key)
    if (!this.clientAddresses.has(key)) {
      this.clientAddresses.set(key, { address, udpPort });
      console.log(`<UDP> Added new client for UDP distribution: ${address}:${udpPort}`);
    }
  }

  shutdown() {
    console.log("<UDP> Shutting down UDP server...");
    this.clientAddresses.clear(); 
    this.server.close(() => console.log("<UDP> Server closed."));
  }
}

module.exports = UdpServer;
