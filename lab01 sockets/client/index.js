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
        this.startUdpListening(); 
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

    startUdpListening() {
   
        this.udpClient.bind({
            port: 0, 
            address: '0.0.0.0',
            reuseAddr: true
        });
        
        this.udpClient.on('listening', () => {
            const address = this.udpClient.address();
            console.log(`UDP Client ready to receive messages on port ${address.port}.`);
            this.udpClient.addMembership(MULTICAST_ADDRESS);
            this.sendUdpMessage(`INIT:${this.udpClient.address().address}:${this.udpClient.address().port}`);
        });

        this.udpClient.on('error', (err) => {
            console.error(`Failed to bind UDP client: ${err.message}`);
            this.udpClient.close();
        });

        this.udpClient.on('message', (msg, rinfo) => {
            const message = msg.toString();
            console.log(`Received UDP message: ${message} from ${rinfo.address}:${rinfo.port}`);
        });
        
     

    }


    handleUserInput() {
        this.rl.on('line', (input) => {
            if (input.startsWith('U ')) {
                this.sendUdpMessage(`U:${input.slice(2)}`);
            } else if (input.startsWith('M ')) {
                this.sendMulticastMessage(`M:${input.slice(2)}`);
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
