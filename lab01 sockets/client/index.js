const net = require("net");
const dgram = require("dgram");
const readline = require("readline");
const {
  SERVER_HOST,
  PORT,
  MULTICAST_ADDRESS,
  MULTICAST_PORT,
} = require("../shared/Constants");

class ChatClient {
  constructor(serverHost, port) {
    this.serverHost = serverHost;
    this.port = port;
    this.id = null;
    this.tcpClient = new net.Socket();
    this.udpClient = dgram.createSocket("udp4");
    this.setupErrorHandling();
    this.rl = readline.createInterface({
      input: process.stdin,
      output: process.stdout,
    });
    this.startUdpListening();
    this.startUdpMulticastListening();
  }

  connect() {
    this.tcpClient.connect(this.port, this.serverHost, () => {
      console.log("Connected to TCP server");
      this.listenTcpServer();
      this.handleUserInput();
    });

    this.tcpClient.on("close", () => {
      console.log("Disconnected from TCP server");
      this.disconnect();
    });
  }

  listenTcpServer() {
    this.tcpClient.on("data", (data) => {
      console.log(`${data}`);
      if (data.toString().includes("Server is shutting down.")) {
        this.disconnect();
      } else if (data.toString().includes("Your ID is: ")) {
        this.id = data.toString().slice(11);
        console.log(`My ID is: ${this.id}`);
      }
    });
  }

  startUdpListening() {
    this.udpClient.bind({
      port: 0,
      address: "0.0.0.0",
    });

    this.udpClient.on("listening", () => {
      const address = this.udpClient.address();
      console.log(
        `UDP Client ready to receive messages on port ${address.port}.`
      );
      this.sendUdpMessageAsync(`INIT`);
    });

    this.udpClient.on("error", (err) => {
      console.error(`Failed to bind UDP client: ${err.message}`);
      this.udpClient.close();
    });

    this.udpClient.on("message", (msg, rinfo) => {
      const message = msg.toString();
      console.log(
        `Received UDP message: ${message} from ${rinfo.address}:${rinfo.port}`
      );
    });
  }

  startUdpMulticastListening() {
    const udpMulticastClient = dgram.createSocket({ type: "udp4", reuseAddr: true });

    udpMulticastClient.bind(MULTICAST_PORT, () => {
      udpMulticastClient.setBroadcast(true);
      udpMulticastClient.setMulticastTTL(128);
      udpMulticastClient.addMembership(MULTICAST_ADDRESS);
      console.log('Multicast UDP Client ready to receive messages.');
    });

    udpMulticastClient.on("message", (msg, rinfo) => {
      console.log(`Received multicast message: ${msg.toString()} from ${rinfo.address}:${rinfo.port}`);
    });

    udpMulticastClient.on("error", (err) => {
      console.error(`Failed to bind Multicast UDP client: ${err.message}`);
      udpMulticastClient.close();
    });
    this.udpMulticastClient = udpMulticastClient;
  }

  handleUserInput() {
    this.rl.on("line", (input) => {
      if (input.startsWith("U ")) {
        this.sendUdpMessageAsync(`U:${input.slice(2)}`);
      } else if (input.startsWith("M ")) {
        this.sendMulticastMessage(`M:${input.slice(2)}`);
      } else {
        this.tcpClient.write(input);
      }
    });
  }

  async sendUdpMessageAsync(message) {
    return new Promise((resolve, reject) => {
      const messageBuffer = Buffer.from(message);
      this.udpClient.send(
        messageBuffer,
        0,
        messageBuffer.length,
        this.port,
        this.serverHost,
        (err) => {
          if (err) {
            console.error(err);
            reject(err);
          } else {
            console.log("UDP message sent");
            resolve();
          }
        }
      );
    });
  }

  sendMulticastMessage(message) {
    const messageBuffer = Buffer.from(message);
    this.udpMulticastClient.send(
      messageBuffer,
      0,
      messageBuffer.length,
      MULTICAST_PORT,
      MULTICAST_ADDRESS,
      (err) => {
        if (err) console.error("Error sending multicast message:", err);
        else console.log("Multicast UDP message sent");
      }
    );
  }

  setupErrorHandling() {
    this.tcpClient.on("error", (error) => {
      if (error.code === "ECONNREFUSED") {
        console.error(
          `Connection refused. Could not connect to ${this.serverHost}:${this.port}`
        );
      } else {
        console.error(`An error occurred: ${error.message}`);
      }
      this.disconnect(1);
    });
  }

  async disconnect(status = 0) {
    console.log("Disconnecting from the server...");
    try {
      await this.sendUdpMessageAsync("DISCONNECT");
    } catch (error) {
      console.error("Failed to send UDP disconnect message:", error);
    }
    this.tcpClient.end(() => {
      console.log("TCP connection closed");
      this.rl.close();
      process.exit(status);
    });
  }
}

const client = new ChatClient(SERVER_HOST, PORT);
client.connect();

process.on("SIGINT", () => client.disconnect());
