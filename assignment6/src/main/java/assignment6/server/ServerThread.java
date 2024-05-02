package assignment6.server;

import assignment4.CLI;
import assignment6.message.BroadcastMessage;
import assignment6.message.ChatroomProtocol;
import assignment6.message.ConnectResponse;
import assignment6.message.DirectMessage;
import assignment6.message.FailedMessage;
import assignment6.message.Message;
import assignment6.message.QueryUserResponse;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A class representing a server thread.
 */
public class ServerThread implements Runnable {

  public static final String SEVER_INSULT_ERROR = "You can't insult the server.";
  public static final String USER_DELIMITER = " -> ";
  public static final String ERROR_CLOSING_CLIENT_SOCKET = "Error closing client socket.";
  private static final String CLIENT_MESSAGE_ERROR = "Error reading from client";
  private static final String MESSAGE_TYPE_ERROR = "Invalid message type";
  private static final String USERNAME_TAKEN_ERROR = "Username already taken";
  private static final String USER_CONNECTED_MESSAGE = "{0} connected.";
  private static final String SERVER_CONNECT_RESPONSE = "There are {0} other connected clients.";
  private static final String RECIPIENT_NOT_FOUND_ERROR = "Recipient not found.";
  private static final String USER_DISCONNECTED_MESSAGE = "{0} disconnected.";
  private static final String RECIPIENT_NOT_SPECIFIED_ERROR = "Recipient not specified.";
  private static final String SELF_INSULT_ERROR = "You can't insult yourself.";
  private final Socket socket;
  private final DataInputStream in;
  private final DataOutputStream out;
  private final ConcurrentHashMap<String, Socket> clients;
  private String username;
  private boolean connected;

  /**
   * Constructor for the ServerThread class.
   *
   * @param socket the socket to connect to
   * @throws IOException if an I/O error occurs when creating the input or output stream
   */
  public ServerThread(Socket socket) throws IOException {
    this.socket = socket;
    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
    this.clients = Server.getInstance().getClients();
    this.connected = true;
  }

  /**
   * Run the server thread.
   */
  @Override
  public void run() {
    try {
      while (connected) {
        Message fromClient = ChatroomProtocol.processInput(in);
        handleMessage(fromClient);
      }
    } catch (IOException e) {
      throw new RuntimeException(CLIENT_MESSAGE_ERROR);
    } finally {
      disconnect();
    }
  }

  /**
   * Handle the message from the client.
   *
   * @param fromClient the message from the client
   * @throws IOException if an I/O error occurs
   */
  private void handleMessage(Message fromClient) throws IOException {
    String sender = fromClient.getSender();
    String recipient = fromClient.getReceiver();
    String messageText = fromClient.getMessage();

    Message fromServer = switch (fromClient.getMessageType()) {
      case CONNECT_MESSAGE -> handleConnectMessage(sender);
      case BROADCAST_MESSAGE -> handleBroadcastMessage(sender, messageText);
      case DIRECT_MESSAGE -> handleDirectMessage(sender, recipient, messageText);
      case QUERY_CONNECTED_USERS -> handleQueryConnectedUsers(sender);
      case DISCONNECT_MESSAGE -> handleDisconnectMessage(sender);
      case SEND_INSULT -> handleSendInsult(sender, recipient);
      default -> new FailedMessage(sender, MESSAGE_TYPE_ERROR);
    };

    if (fromServer != null) {
      fromServer.send(out);
    }
  }

  /**
   * Handle the connect message.
   *
   * @param sender the sender of the message
   * @return the response message
   */
  private Message handleConnectMessage(String sender) {
    if (clients.containsKey(sender)) {
      return new ConnectResponse(false, USERNAME_TAKEN_ERROR);
    } else if (clients.size() >= Server.getMaxClients()) {
      connected = false;
      return new ConnectResponse(false, ChatroomProtocol.SERVER_FULL);
    } else {
      this.username = sender;
      clients.put(sender, socket);
      System.out.println(MessageFormat.format(USER_CONNECTED_MESSAGE, sender));
      return new ConnectResponse(true,
          MessageFormat.format(SERVER_CONNECT_RESPONSE, clients.size()));
    }
  }

  /**
   * Handle the broadcast message.
   *
   * @param sender      the sender of the message
   * @param messageText the message text
   * @return the response message
   * @throws IOException if an I/O error occurs
   */
  private Message handleBroadcastMessage(String sender, String messageText) throws IOException {
    for (String client : clients.keySet()) {
      Socket clientSocket = clients.get(client);
      DataOutputStream clientOut = new DataOutputStream(clientSocket.getOutputStream());
      new BroadcastMessage(sender, messageText).send(clientOut);
    }
    return null;
  }

  /**
   * Handle the direct message.
   *
   * @param sender      the sender of the message
   * @param recipient   the recipient of the message
   * @param messageText the message text
   * @return the response message
   * @throws IOException if an I/O error occurs
   */
  private Message handleDirectMessage(String sender, String recipient, String messageText)
      throws IOException {
    Socket recipientSocket = clients.get(recipient);
    if (recipientSocket != null) {
      DataOutputStream recipientOut = new DataOutputStream(recipientSocket.getOutputStream());
      new DirectMessage(sender, recipient, messageText).send(recipientOut);
      return null;
    } else {
      return new FailedMessage(sender, RECIPIENT_NOT_FOUND_ERROR);
    }
  }

  /**
   * Handle the query connected users message.
   *
   * @param sender the sender of the message
   * @return the response message
   */
  private Message handleQueryConnectedUsers(String sender) {
    int numUsers = clients.size() - 1;
    String[] clientsArray = clients.keySet().stream()
        .filter(client -> !client.equals(sender))
        .toArray(String[]::new);
    return new QueryUserResponse(numUsers, clientsArray);
  }

  /**
   * Handle the disconnect message.
   *
   * @param sender the sender of the message
   * @return the response message
   */
  private Message handleDisconnectMessage(String sender) {
    connected = false;
    System.out.println(MessageFormat.format(USER_DISCONNECTED_MESSAGE, sender));
    return new ConnectResponse(true, ChatroomProtocol.SERVER_DISCONNECT_MESSAGE);
  }

  /**
   * Handle the send insult message.
   *
   * @param sender    the sender of the message
   * @param recipient the recipient of the message
   * @return the response message
   * @throws IOException if an I/O error occurs
   */
  private Message handleSendInsult(String sender, String recipient) throws IOException {
    if (recipient == null) {
      return new FailedMessage(sender, RECIPIENT_NOT_SPECIFIED_ERROR);
    }

    if (recipient.equals(sender)) {
      return new FailedMessage(sender, SELF_INSULT_ERROR);
    }

    if (recipient.equals(ChatroomProtocol.SERVER_NAME)) {
      return new FailedMessage(sender, SEVER_INSULT_ERROR);
    }

    if (!clients.containsKey(recipient)) {
      return new FailedMessage(sender, RECIPIENT_NOT_FOUND_ERROR);
    }

    String insult = CLI.getInstance().generateInsult();
    return handleBroadcastMessage(sender + USER_DELIMITER + recipient, insult);
  }

  /**
   * Disconnect the client.
   */
  private void disconnect() {
    if (username == null) {
      return;
    }

    Socket clientSocket = clients.remove(this.username);
    if (clientSocket != null) {
      try {
        clientSocket.close();
        in.close();
        out.close();
      } catch (IOException e) {
        throw new RuntimeException(ERROR_CLOSING_CLIENT_SOCKET);
      }
    }
  }

  /**
   * Get the socket.
   *
   * @return the socket
   */
  public Socket getSocket() {
    return socket;
  }

  /**
   * Get the input stream.
   *
   * @return the input stream
   */
  public DataInputStream getIn() {
    return in;
  }

  /**
   * Get the output stream.
   *
   * @return the output stream
   */
  public DataOutputStream getOut() {
    return out;
  }

  /**
   * Get the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Set the username.
   *
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Get the map of connected clients.
   *
   * @return the map of connected clients
   */
  public ConcurrentHashMap<String, Socket> getClients() {
    return clients;
  }

  /**
   * Check if the client is connected.
   *
   * @return true if the client is connected, false otherwise
   */
  public boolean isConnected() {
    return connected;
  }

  /**
   * Set if the client is connected.
   *
   * @param connected true if the client is connected, false otherwise
   */
  public void setConnected(boolean connected) {
    this.connected = connected;
  }

  /**
   * Returns true if the given object is equal to this ServerThread, false otherwise.
   *
   * @return true if the given object is equal to this ServerThread, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ServerThread that)) {
      return false;
    }
    return isConnected() == that.isConnected() && Objects.equals(getSocket(),
        that.getSocket()) && Objects.equals(getIn(), that.getIn())
        && Objects.equals(getOut(), that.getOut()) && Objects.equals(
        getUsername(), that.getUsername()) && Objects.equals(getClients(),
        that.getClients());
  }

  /**
   * Returns the hashcode of the ServerThread.
   *
   * @return the hashcode of the ServerThread
   */
  @Override
  public int hashCode() {
    return Objects.hash(getSocket(), getIn(), getOut(), getUsername(), getClients(), isConnected());
  }

  /**
   * Returns the string representation of the ServerThread.
   *
   * @return the string representation of the ServerThread
   */
  @Override
  public String toString() {
    return "ServerThread{" +
        "socket=" + socket +
        ", in=" + in +
        ", out=" + out +
        ", username='" + username + '\'' +
        ", clients=" + clients +
        ", connected=" + connected +
        '}';
  }
}
