# Message Exchange Project

## Overview
This project implements a system where two players communicate with each other by exchanging messages. The implementation satisfies two distinct requirements:

1. **Requirement #5**: Both players run in the **same Java process**, using threads to handle communication.
2. **Requirement #7**: Both players run in **separate Java processes**, communicating via inter-process communication (IPC) using sockets.

The project demonstrates clean design principles and functional implementation using core Java and Maven.

---

## Project Structure
The project is organized as follows:
```
message-exchange/
│── src/main/java/com/example/
│   ├── MessageExchange.java      # Implementation for Requirement #5
│   ├── Player.java               # Player logic for Requirement #5
│   ├── PlayerServer.java         # Server logic for Requirement #7
│   ├── PlayerClient.java         # Client logic for Requirement #7
│── pom.xml                       # Maven build configuration
│── start.sh                      # Script to start the application
│── README.md                     # Project documentation
```

---

## Requirement #5: Single Java Process (Threads)
In this implementation, both players run as threads within the same Java process. Communication is achieved using shared memory constructs (e.g., a blocking queue). The workflow is as follows:

1. Two `Player` instances are created and run as separate threads.
2. The initiating player sends a message to the other player.
3. The receiving player replies with the received message concatenated with a counter.
4. The process continues until 10 messages are exchanged.

### Key Classes:
- **`MessageExchange.java`**: Entry point that creates and starts the player threads.
- **`Player.java`**: Encapsulates the player logic for sending and receiving messages.

### How to Run:
Run the `start.sh` script and select the option for the single-process implementation.

---

## Requirement #7: Separate Java Processes (Sockets)
In this implementation, each player runs in a separate Java process, and communication occurs via TCP sockets. One player acts as a server, and the other as a client. The workflow is as follows:

1. The server (`PlayerServer`) listens for incoming messages.
2. The client (`PlayerClient`) connects to the server and sends the initial message.
3. The server replies with the received message concatenated with a counter.
4. The process continues until 10 messages are exchanged.

### Key Classes:
- **`PlayerServer.java`**: Implements the server-side logic.
- **`PlayerClient.java`**: Implements the client-side logic.

### How to Run:
Run the `start.sh` script and select the option for the multi-process implementation. The script will:
1. Start the server process.
2. Start the client process after a short delay.

---

## Execution Instructions
1. **Ensure Java and Maven are Installed**:
   Verify Java and Maven installations:
   ```bash
   java -version
   mvn -version
   ```

2. **Run the Script**:
   Execute the `start.sh` script and select the desired implementation:
   ```bash
   ./start.sh
   ```

3. **Choose the Mode**:
    - Option `1`: Runs the single-process implementation (Requirement #5).
    - Option `2`: Runs the multi-process implementation (Requirement #7).

---

## Design Considerations
- **Clean Code**: All classes have clear responsibilities, documented using Javadoc.
- **Modularity**: Both implementations coexist in the same project for easy comparison and maintainability.
- **Extensibility**: Additional communication modes (e.g., REST, gRPC) can be added if needed.

---

## 
- **Java 8+**
- **Maven 3+**

---

## Contact
For questions or issues, feel free to reach out to the developer.

