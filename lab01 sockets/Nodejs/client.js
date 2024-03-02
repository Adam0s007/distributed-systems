const net = require('net');
const dgram = require('dgram');
const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});
const udpClient = dgram.createSocket('udp4');
const tcpClient = new net.Socket();
const serverHost = 'localhost';
const port = 8124;

tcpClient.connect(port, serverHost, () => {
  console.log('Connected to TCP server');
});

tcpClient.on('data', (data) => {
  console.log(`Server says: ${data}`);
  if (data.toString().includes('Server is shutting down.')) {
    tcpClient.end(() => {
        console.log('TCP connection closed');
        process.exit();
      });
}
});

tcpClient.on('close', () => {
  console.log('Disconnected from TCP server');
});

rl.on('line', (input) => {
  if (input.startsWith('U ')) {
    const message = Buffer.from(input.slice(2));
    udpClient.send(message, 0, message.length, port, serverHost, err => {
      if (err) console.error(err);
      console.log('UDP message sent');
    });
  } else if (input.startsWith('M ')) {
    const message = Buffer.from(input.slice(2));
    udpClient.send(message, 0, message.length, port, multicastAddress, err => {
      if (err) console.error(err);
      console.log('Multicast UDP message sent');
    });
  } else {
    tcpClient.write(input);
  }
});

process.on('SIGINT', () => {
    console.log('Disconnecting from the server...');
    tcpClient.end(() => {
      console.log('TCP connection closed');
      process.exit();
    });
  });
  
