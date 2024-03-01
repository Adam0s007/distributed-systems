const net = require('net');
const dgram = require('dgram');
const udpServer = dgram.createSocket('udp4');
const tcpClients = [];
const multicastAddress = '224.0.0.114';
const port = 8124;

// Obsługa TCP
const tcpServer = net.createServer(socket => {
  const clientId = `${socket.remoteAddress}:${socket.remotePort}`;
  console.log(`${clientId} connected`);
  tcpClients.push(socket);

  socket.on('data', data => {
    const message = `${clientId}: ${data}`;
    console.log(`TCP: ${message}`);
    tcpClients.forEach(client => {
      if (client !== socket) {
        client.write(message);
      }
    });
  });

  socket.on('close', () => {
    console.log(`${clientId} disconnected`);
    tcpClients.splice(tcpClients.indexOf(socket), 1);
  });

  socket.on('error', error => {
    console.log(`${clientId} has encountered an error: ${error.message}`);
    tcpClients.splice(tcpClients.indexOf(socket), 1);
  });
}).listen(port, () => console.log(`TCP Server listening on port ${port}`));


process.on('SIGINT', () => {
    console.log('Shutting down server...');

    const disconnectPromises = tcpClients.map(socket => {
        return new Promise(resolve => socket.end('Server is shutting down.', resolve));
    });

    Promise.all(disconnectPromises).then(() => {
        console.log('All clients disconnected.');
      
        tcpServer.close(() => {
            console.log('TCP Server closed.');
            udpServer.close(() => {
                console.log('UDP Server closed.');
                process.exit();
            });
        });

    }).catch(error => {
        console.error('Error shutting down:', error);
    });
});

udpServer.on('message', (msg, rinfo) => {
  const message = `${rinfo.address}:${rinfo.port}: ${msg}`;
  console.log(`UDP: ${message}`);
  tcpClients.forEach(client => {
    client.write(`UDP: ${message}`);
  });
});

udpServer.bind(port, () => {
  console.log(`UDP Server listening on port ${port}`);
  udpServer.setBroadcast(true);
  udpServer.setMulticastTTL(128);
  udpServer.addMembership(multicastAddress);
});

console.log('Server is running...');
