const TcpServer = require('./TcpServer');
const UdpServer = require('./UdpServer');
const net = require('net');

const { PORT } = require('../shared/Constants');



function checkPortAvailability(port) {
    return new Promise((resolve, reject) => {
        const tester = net.createServer()
            .once('error', err => (err.code === 'EADDRINUSE' ? resolve(false) : reject(err)))
            .once('listening', () => tester.once('close', () => resolve(true)).close())
            .listen(port);
    });
}

async function startServer() {
    const isPortAvailable = await checkPortAvailability(PORT);

    if (!isPortAvailable) {
        console.error(`Port ${PORT} is already in use. Exiting...`);
        process.exit(1);
    }
    const tcpServer = new TcpServer();
    const udpServer = new UdpServer(tcpServer);

    tcpServer.start();
    udpServer.start();

    process.on('SIGINT', async () => {
        await tcpServer.shutdown();
        udpServer.shutdown();
        process.exit(0);
    });
}
startServer().catch(err => console.error('Failed to start the server:', err));



