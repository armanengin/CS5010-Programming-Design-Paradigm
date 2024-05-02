- To **build** .jar files, run the following command in the root directory of the project:

    ```
    ./gradlew jar
    ```

  The .jar files for both client and server will be located in the `build/libs` directory.


- To **run** the server, run the following command in the root directory of the project:

    ```
    java -jar ./build/libs/server-1.0.jar
    ```
- To **run** the client, run the following command in the root directory of the project:

    ```
    java -jar ./build/libs/client-1.0.jar <host> <port> <username>
    ```

- To **test** the project, run the following command in the root directory of the project:

    ```
    ./gradlew doAll
    ``` 

  ## High-level description of the key classes and methods:

Client class: Represents the client-side of the chat application.

- **start() method:** Connects to the server, sends a connect message, and starts the input and
  message handler threads.
    - **ClientInputHandler class (inner class):**
        - Handles user input from the console.
        - Parses user commands and creates corresponding message objects.
        - Sends messages to the server.
    - **ServerMessageHandler class (inner class):**
        - Receives and processes messages from the server.
        - Displays the received messages on the console.

Server class:
Represents the server-side of the chat application.
- **start() method:** Listens for incoming client connections and creates a new ServerThread for each connected client.
- **shutdown() method:** Gracefully shuts down the server by closing the server socket and shutting down the thread pool.
- **getClientMap() method:** Returns the map of connected clients.

ServerThread class:
Represents a thread that handles communication with a single client.
- **run() method:** Continuously receives messages from the client and processes them.
- **processMessage() method:** Determines the type of received message and calls the appropriate handler method.
  - Message handler methods:
    - **handleConnectMessage():** Handles a client's connection request.
    - **handleBroadcastMessage():** Handles a broadcast message from a client.
    - **handleDirectMessage():** Handles a direct message from a client to another client.
    - **handleQueryConnectedUsers():** Handles a request to query connected users.
    - **handleDisconnectMessage():** Handles a client's disconnection request.
    - **handleSendInsult():** Handles a request to send an insult to another client.

ChatroomProtocol class:
Defines the communication protocol between the client and server.
- **processInput() method:** Processes the input stream and creates the corresponding message object based on the message type.

Message classes:
Represent different types of messages exchanged between the client and server.
- Examples: ConnectMessage, BroadcastMessage, DirectMessage, QueryConnectedUsers, DisconnectMessage, SendInsult, etc.
- Each message class has methods to send and process the message.

  ## The assumptions I made about the nature of the problem.
- Clients will connect to the server using TCP sockets.
- Each client will be uniquely identified by their username.
- The server will handle concurrent connections using a thread pool.
- if it is reached to 10 clients server should send a message that server is full
    
  ## Steps I took to ensure correctness
- Reviewed the provided requirements and specifications for both the server and client components of the application.
- Ensured that the server starts up and listens on an available port, printing a message to the console with the port information upon startup.
- Implemented functionality in the server to handle incoming client connections, up to a maximum of 10 clients, and handle each connected client appropriately.
- Implemented the required message types and their corresponding message formats as specified in the Chatroom Protocol.
- Ensured that the server responds appropriately to different client commands such as CONNECT_MESSAGE, DISCONNECT_MESSAGE, QUERY_CONNECTED_USERS, BROADCAST_MESSAGE, DIRECT_MESSAGE, FAILED_MESSAGE, and SEND_INSULT.
- Tested the server functionality using unit tests to verify that it behaves as expected in different scenarios, such as successful client connections, message broadcasting, and error handling.
