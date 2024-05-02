package assignment6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Server class represents a server that listens for client connections.
 */
public final class Server {

  private static final int MAX_CLIENTS = 10;
  private static final int PORT = 8080;
  private static final String SERVER_STARTED_MESSAGE = "Server started on port " + PORT;
  private static final String SERVER_STARTED_ERROR = "Failed to start server";
  private static final String CLIENT_CONNECTION_ERROR = "Failed to accept client connection";
  private static final String SERVER_SHUTDOWN_ERROR = "Failed to shutdown server";
  private static Server instance;
  private final ExecutorService pool = Executors.newCachedThreadPool();
  private final ServerSocket serverSocket;
  private final ConcurrentHashMap<String, Socket> clients = new ConcurrentHashMap<>();
  private boolean running = true;

  /**
   * Private constructor for the Server class.
   */
  private Server() {
    try {
      serverSocket = new ServerSocket(PORT);
      System.out.println(SERVER_STARTED_MESSAGE);
    } catch (IOException e) {
      throw new RuntimeException(SERVER_STARTED_ERROR);
    }
  }

  /**
   * Returns the instance of the Server class.
   *
   * @return the instance of the Server class
   */
  public static synchronized Server getInstance() {
    if (instance == null) {
      instance = new Server();
    }
    return instance;
  }

  /**
   * Sets the instance of the Server class.
   *
   * @param instance the instance of the Server class
   */
  public static void setInstance(Server instance) {
    Server.instance = instance;
  }

  /**
   * Returns the maximum number of clients that can connect to the server.
   *
   * @return the maximum number of clients that can connect to the server
   */
  public static int getMaxClients() {
    return MAX_CLIENTS;
  }

  /**
   * Listens for client connections.
   */
  public void listen() {
    while (running) {
      try {
        Socket socket = serverSocket.accept();
        ServerThread serverThread = new ServerThread(socket);
        pool.execute(serverThread);
      } catch (IOException e) {
        if (running) {
          throw new RuntimeException(CLIENT_CONNECTION_ERROR);
        }
      }
    }
  }

  /**
   * Shuts down the server.
   */
  public void shutdown() {
    running = false;
    try {
      serverSocket.close();
      pool.shutdown();
    } catch (IOException e) {
      throw new RuntimeException(SERVER_SHUTDOWN_ERROR);
    }
  }

  /**
   * Returns the clients connected to the server.
   *
   * @return the clients connected to the server
   */
  public ConcurrentHashMap<String, Socket> getClients() {
    return clients;
  }

  /**
   * Returns true if the server is running, false otherwise.
   *
   * @return true if the server is running, false otherwise
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Sets whether the server is running.
   *
   * @param running true if the server is running, false otherwise
   */
  public void setRunning(boolean running) {
    this.running = running;
  }

  /**
   * Returns the ExecutorService pool.
   *
   * @return the ExecutorService pool
   */
  public ExecutorService getPool() {
    return pool;
  }

  /**
   * Returns the ServerSocket.
   *
   * @return the ServerSocket
   */
  public ServerSocket getServerSocket() {
    return serverSocket;
  }

  /**
   * Returns the clients connected to the server.
   *
   * @return the clients connected to the server
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Server server)) {
      return false;
    }
    return isRunning() == server.isRunning() && Objects.equals(getPool(), server.getPool())
        && Objects.equals(getServerSocket(), server.getServerSocket())
        && Objects.equals(getClients(), server.getClients());
  }

  /**
   * Returns the hashcode of the server.
   *
   * @return the hashcode of the server
   */
  @Override
  public int hashCode() {
    return Objects.hash(isRunning(), getPool(), getServerSocket(), getClients());
  }

  /**
   * Returns the string representation of the server.
   *
   * @return the string representation of the server
   */
  @Override
  public String toString() {
    return "Server{" +
        "running=" + running +
        ", pool=" + pool +
        ", serverSocket=" + serverSocket +
        ", clients=" + clients +
        '}';
  }
}