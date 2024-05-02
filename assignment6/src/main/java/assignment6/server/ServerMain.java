package assignment6.server;

import java.io.IOException;

/**
 * Main class to start the server.
 */
public class ServerMain {

  private static final String SERVER_SHUTDOWN_MESSAGE = "Server is shutting down...";

  /**
   * Main method to start the server.
   *
   * @param args command line arguments
   * @throws IOException if an I/O error occurs
   */
  public static void main(String[] args) throws IOException {
    Server server = Server.getInstance();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println(SERVER_SHUTDOWN_MESSAGE);
      server.shutdown();
    }));

    server.listen();
  }

}
