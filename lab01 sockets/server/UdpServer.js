const dgram = require('dgram');
const { PORT, MULTICAST_ADDRESS } = require('../shared/Constants');

class UdpServer {
    constructor(tcpServer) {
        this.tcpServer = tcpServer;
        this.server = dgram.createSocket('udp4');
        this.setupErrorHandling();
    }

    start() {
        this.server.on('message', (msg, rinfo) => this.handleMessage(msg, rinfo));
        this.server.bind(PORT, () => {
            console.log(`UDP Server listening on port ${PORT}`);
            this.server.setBroadcast(true);
            this.server.setMulticastTTL(128);
            this.server.addMembership(MULTICAST_ADDRESS);
        });
    }

    handleMessage(msg, rinfo) {
        const message = `${rinfo.address}:${rinfo.port}: ${msg}`;
        console.log("<UDP>", message)
        
        this.tcpServer.clients.forEach(client => {
            client.write(`<UDP> ${message}`);
        });
    }

    broadcastMessage(message) {
        const messageBuffer = Buffer.from(message);
        this.server.send(messageBuffer, 0, messageBuffer.length, PORT, MULTICAST_ADDRESS, (error) => {
            if (error) {
                console.error(`Error broadcasting message: ${error.message}`);
            }
        });
    }

    shutdown() {
        console.log('Shutting down UDP server...');
        this.server.close(() => console.log('UDP Server closed.'));
    }

    setupErrorHandling() {
        this.server.on('error', (error) => {
            console.error(`UDP Server encountered an error: ${error.message}`);
            this.shutdown();
        });
    }
}

module.exports = UdpServer;
