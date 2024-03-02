const net = require("net");
const { PORT } = require("../shared/Constants");
class TcpServer {
  constructor() {
    this.clients = new Map();
    this.server = net.createServer(this.handleConnection.bind(this));
    this.setupErrorHandling();
  }

  handleConnection(socket) {
    
    const clientId = socket.remotePort

    console.log(`${clientId} connected`);
    this.clients.set(clientId, socket);
    this.sendIdToClient(clientId, socket);

    socket.on("data", (data) => this.broadcastMessage(clientId, data));
    socket.on("close", () => this.handleDisconnect(clientId));
    socket.on("error", (error) =>
      console.error(`${clientId} encountered an error: ${error.message}`)
    );
  }


  broadcastMessage(clientId, data) {
    const message = `${clientId}: ${data}`;
    console.log(`<Broadcasting message> ${message}`);
    this.clients.forEach((client, id) => {
      if (id !== clientId) {
        client.write(message);
      }
    });
  }

  sendIdToClient(clientId, socket) {
    socket.write(`Your ID is: ${clientId}`);
  }

  handleDisconnect(clientId) {
    console.log(`${clientId} disconnected`);
    this.clients.delete(clientId);
  }

  start() {
    this.server.listen(PORT, () =>
      console.log(`TCP Server listening on port ${PORT}`)
    );
  }

  async shutdown() {
    console.log("Shutting down TCP server...");
    this.clients.forEach((client) => {
      client.write("Server is shutting down.");
    });
    await Promise.all(
        Array.from(this.clients.values()).map(socket => 
            new Promise((resolve) => {
                socket.end(resolve);
            })
        )
    );

    this.server.close(() => {
      console.log("TCP Server closed.");
    });
  }

  setupErrorHandling() {
    this.server.on("error", (error) => {
      console.error(`TCP Server encountered an error: ${error.message}`);
      this.shutdown();
    });
  }
}

module.exports = TcpServer;
