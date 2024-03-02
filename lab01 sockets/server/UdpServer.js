const dgram = require("dgram");
const { PORT, MULTICAST_ADDRESS } = require("../shared/Constants");

class UdpServer {
  constructor() {
    this.server = dgram.createSocket("udp4");
    this.clientAddresses = []; 
  }

  start() {
    this.server.bind(PORT, () => {
      console.log(`UDP Server listening on port ${PORT}`);
      this.server.setMulticastTTL(128);
      this.server.addMembership(MULTICAST_ADDRESS);
    });

    this.server.on('message', (msg, rinfo) => {
        const messageContent = msg.toString();
        console.log(`<UDP> Received message from ${rinfo.address}:${rinfo.port}: ${messageContent}`);
    
        if (messageContent.startsWith('INIT')) {
            const [_, address, udpPort] = messageContent.split(':');
            this.addClientAddress(rinfo.address, parseInt(udpPort, 10)); 
        } else if (messageContent.startsWith('M:')) {
            console.log(`Received multicast message: ${messageContent}`);
        } else if (messageContent.startsWith('U:')) {
            const modifiedMessage = messageContent.slice(2);
            this.distributeMessage(Buffer.from(modifiedMessage), rinfo);
        } else {
            console.log(`Unknown message: ${messageContent}`);
        }
    });
    
  }

  distributeMessage(message, senderInfo) {
    this.clientAddresses.forEach((client) => {
      if (
        client.address !== senderInfo.address ||
        client.udpPort !== senderInfo.port
      ) {
        console.log(`Sending message to ${client.address}:${client.udpPort}`);
        this.server.send(
          message,
          0,
          message.length,
          client.udpPort,
          client.address,
          (err) => {
            if (err)
              console.log(
                `Error sending message to ${client.address}:${client.udpPort}`,
                err
              );
          }
        );
      }
    });
  }

  addClientAddress(address, udpPort) {
    if (
      !this.clientAddresses.some(
        (client) => client.address === address && client.udpPort === udpPort
      )
    ) {
      this.clientAddresses.push({ address, udpPort });
      console.log(
        `Added new client for UDP distribution: ${address}:${udpPort}`
      );
    }
  }

  shutdown() {
    console.log("Shutting down UDP server...");
    this.server.close(() => console.log("UDP Server closed."));
  }
}

module.exports = UdpServer;
