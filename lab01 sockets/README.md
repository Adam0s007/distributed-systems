# Homework Assignment - Chat Application

## Overview
This assignment requires the creation of a chat application. The key features and requirements are outlined below.

### Features
- **TCP Protocol:** Clients connect to the server using the TCP protocol.
- **Message Broadcasting:** The server receives messages from each client and broadcasts them to the others, including the sender's ID or nickname.
- **Multithreading:** The server operates on a multithreaded architecture, assigning a separate thread for each client connection. Proper thread management is crucial.

### Additional UDP Channel
- **UDP Channel:** Besides the TCP connection, the server and clients open an additional UDP channel on the same port as TCP.
- **UDP Command ('U'):** When a client enters the 'U' command, a message is sent through UDP to the server, which then broadcasts it to the other clients. This message can simulate multimedia data, such as ASCII Art.

### Multicast Implementation
- **Multicast Option:** Implement the previous point using multicast as an alternative option, activated with the 'M' command. Multicast sends directly to all clients via a group address, and the server may, but is not required to, receive these messages.

## Instructions
- **Programming Language:** The assignment can be submitted in any programming language.
- **No Frameworks:** Only socket programming is allowed. The use of network communication frameworks or Akka is prohibited.

### Submission Requirements
- **Demonstration:** Demonstrate the application's operation with the server and at least two clients.
- **Code Discussion:** Discuss the source code during submission.
- **Considerations:** Focus on the solution's efficiency (e.g., thread pool usage) and correctness (e.g., avoiding message echo to the sender, thread management).

### Running the Application

#### For JavaTcpUdpSockets Folder
1. **Start the Server:** Initialize the `Main` class to launch the server.
2. **Launch Clients:** Execute the `ClientApp` class for each client wishing to connect. Multiple clients can connect simultaneously.

#### For NodejsTcpUdpSockets Folder
1. **Installation:** Before running the server or clients, execute `npm install` to install necessary dependencies.
2. **Start the Server:** Run the server using the command `node server/index.js`.
3. **Launch Clients:** For each client that wishes to connect, use the command `node client/index.js`.


### Additional Notes
- Ensure proper handling of threads to maintain application performance and stability.
- Pay attention to efficiently managing client connections and broadcasting messages.
