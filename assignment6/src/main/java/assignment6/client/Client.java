package assignment6.client;

import assignment6.message.ConnectMessage;
import assignment6.message.Message;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

/**
 * Client class for the chat room application.
 */
public class Client {

  private static final String BUFFER_READER_ERROR = "Failed to create BufferedReader";
  private static final int WAIT_TIME = 1000;
  private static final String SLEEP_INTERRUPTED_ERROR = "Error waiting for connection response";
  private static final String JOIN_INTERRUPTED_ERROR = "Error joining task threads";
  private static final String CONNECTED_MESSAGE = "Connected to the server.";
  private static final String CONNECTION_ERROR = "Failed to connect to server with given host:port";
  private static final String CONNECT_MESSAGE_ERROR = "Failed to send connect message";
  private final String host;
  private final int port;
  private final String username;
  private boolean connected;
  private BufferedReader stdIn;
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  /**
   * Constructor for the Client class.
   *
   * @param host     The host to connect to.
   * @param port     The port to connect to.
   * @param username The username of the client.
   */
  public Client(String host, int port, String username) {
    this.host = host;
    this.port = port;
    this.username = username;
    this.connected = true;
    try {
      this.stdIn = new BufferedReader(new InputStreamReader(System.in));
    } catch (Exception e) {
      throw new RuntimeException(BUFFER_READER_ERROR);
    }
  }

  /**
   * Starts the client.
   *
   * @throws IOException If an I/O error occurs.
   */
  public void start() throws IOException {
    connectClient();
    sendConnectMessage();

    ServerOutputHandler serverOutputHandler = new ServerOutputHandler(this);
    Thread outputThread = new Thread(serverOutputHandler);
    outputThread.start();

    try {
      Thread.sleep(WAIT_TIME);
    } catch (InterruptedException e) {
      throw new RuntimeException(SLEEP_INTERRUPTED_ERROR);
    }

    if (!connected) {
      return;
    }

    ClientInputHandler clientInputHandler = new ClientInputHandler(this);
    Thread inputThread = new Thread(clientInputHandler);
    inputThread.start();

    try {
      inputThread.join();
      outputThread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(JOIN_INTERRUPTED_ERROR);
    }
  }

  /**
   * Connects the client to the server.
   */
  public void connectClient() {
    try {
      this.socket = new Socket(host, port);
      this.in = new DataInputStream(socket.getInputStream());
      this.out = new DataOutputStream(socket.getOutputStream());
      System.out.println(CONNECTED_MESSAGE);
    } catch (IOException e) {
      throw new RuntimeException(CONNECTION_ERROR);
    }
  }

  /**
   * Sends a connect message to the server.
   */
  public void sendConnectMessage() {
    try {
      Message connectMessage = new ConnectMessage(username);
      connectMessage.send(out);
    } catch (IOException e) {
      throw new RuntimeException(CONNECT_MESSAGE_ERROR);
    }
  }

  /**
   * Gets the host of the client.
   *
   * @return the host of the client
   */
  public String getHost() {
    return host;
  }

  /**
   * Gets the port of the client.
   *
   * @return the port of the client
   */
  public int getPort() {
    return port;
  }

  /**
   * Gets the username of the client.
   *
   * @return the username of the client
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the socket of the client.
   *
   * @return the socket of the client
   */
  public Socket getSocket() {
    return socket;
  }

  /**
   * Sets the socket of the client.
   *
   * @param socket the socket of the client
   */
  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  /**
   * Gets the input stream of the client.
   *
   * @return the input stream of the client
   */
  public DataInputStream getIn() {
    return in;
  }

  /**
   * Sets the input stream of the client.
   *
   * @param in the input stream of the client
   */
  public void setIn(DataInputStream in) {
    this.in = in;
  }

  /**
   * Gets the output stream of the client.
   *
   * @return the output stream of the client
   */
  public DataOutputStream getOut() {
    return out;
  }

  /**
   * Sets the output stream of the client.
   *
   * @param out the output stream of the client
   */
  public void setOut(DataOutputStream out) {
    this.out = out;
  }

  /**
   * Gets the standard input of the client.
   *
   * @return the standard input of the client
   */
  public BufferedReader getStdIn() {
    return stdIn;
  }

  /**
   * Sets the standard input of the client.
   *
   * @param stdIn the standard input of the client
   */
  public void setStdIn(BufferedReader stdIn) {
    this.stdIn = stdIn;
  }

  /**
   * Gets the connection status of the client.
   *
   * @return the connection status of the client
   */
  public boolean isConnected() {
    return connected;
  }

  /**
   * Sets the connection status of the client.
   *
   * @param connected the connection status of the client
   */
  public void setConnected(boolean connected) {
    this.connected = connected;
  }

  /**
   * Returns true if the given object is equal to this client, false otherwise.
   *
   * @param o the given object
   * @return true if the given object is equal to this client, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Client client = (Client) o;
    return port == client.port && connected == client.connected && Objects.equals(host,
        client.host) && Objects.equals(username, client.username)
        && Objects.equals(stdIn, client.stdIn) && Objects.equals(socket,
        client.socket) && Objects.equals(in, client.in) && Objects.equals(out,
        client.out);
  }

  /**
   * Returns the hashcode of the client.
   *
   * @return the hashcode of the client
   */
  @Override
  public int hashCode() {
    return Objects.hash(host, port, username, connected, stdIn, socket, in, out);
  }

  /**
   * Returns the string representation of the client.
   *
   * @return the string representation of the client
   */
  @Override
  public String toString() {
    String sb = "Client{" + "host='" + host + '\''
        + ", port=" + port
        + ", username='" + username + '\''
        + ", connected=" + connected
        + ", stdIn=" + stdIn
        + ", socket=" + socket
        + ", in=" + in
        + ", out=" + out
        + '}';
    return sb;
  }
}