const net = require('net');
const dgram = require('dgram');
const readline = require('readline');
const { SERVER_HOST, PORT, MULTICAST_ADDRESS } = require('../shared/Constants');

class ChatClient {
    constructor(serverHost, port) {
        this.serverHost = serverHost;
        this.port = port;
        this.id = null;
        this.tcpClient = new net.Socket();
        this.udpClient = dgram.createSocket('udp4');
        this.setupErrorHandling();
        this.rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        
    }

    connect() {
        this.tcpClient.connect(this.port, this.serverHost, () => {
            console.log('Connected to TCP server');
            this.listenToServer();
            this.handleUserInput();
        });

        this.tcpClient.on('close', () => {
            console.log('Disconnected from TCP server');
            process.exit(0);
        });
    }

    listenToServer() {
        this.tcpClient.on('data', (data) => {
            console.log(`<Server mess> ${data}`);
            if (data.toString().includes('Server is shutting down.')) {
                this.disconnect();
            }else if (data.toString().includes('Your ID is: ')) {
                this.id = data.toString().slice(11);
                console.log(`My ID is: ${this.id}`);
            }

        });
    }

    handleUserInput() {
        this.rl.on('line', (input) => {
            if (input.startsWith('U ')) {
                this.sendUdpMessage(input.slice(2));
            } else if (input.startsWith('M ')) {
                this.sendMulticastMessage(input.slice(2));
            } else {
                this.tcpClient.write(input);
            }
        });
    }

    sendUdpMessage(message) {
        const messageBuffer = Buffer.from(message);
        this.udpClient.send(messageBuffer, 0, messageBuffer.length, this.port, this.serverHost, err => {
            if (err) console.error(err);
            console.log('UDP message sent');
        });
    }

    sendMulticastMessage(message) {
        const messageBuffer = Buffer.from(message);
        this.udpClient.send(messageBuffer, 0, messageBuffer.length, this.port, MULTICAST_ADDRESS, err => {
            if (err) console.error(err);
            console.log('Multicast UDP message sent');
        });
    }

    setupErrorHandling() {
        this.tcpClient.on('error', (error) => {
            if (error.code === 'ECONNREFUSED') {
                console.error(`Connection refused. Could not connect to ${this.serverHost}:${this.port}`);
            } else {
                console.error(`An error occurred: ${error.message}`);
            }
            process.exit(1); 
        });
    }

    disconnect() {
        console.log('Disconnecting from the server...');
        this.tcpClient.end(() => {
            console.log('TCP connection closed');
            this.rl.close();
            process.exit();
        });
    }
}

const client = new ChatClient(SERVER_HOST, PORT);
client.connect();

process.on('SIGINT', () => client.disconnect());
