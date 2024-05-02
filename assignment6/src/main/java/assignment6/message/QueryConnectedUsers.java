package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * QueryConnectedUsers class.
 */
public class QueryConnectedUsers extends Message {

  private static final String QUERY_CONNECTED_USERS_MESSAGE_FORMAT =
      "Client: QUERY_CONNECTED_USERS {0}";


  /**
   * Constructor for QueryConnectedUsers.
   */
  public QueryConnectedUsers() {
    super(MessageType.QUERY_CONNECTED_USERS, null, ChatroomProtocol.SERVER_NAME, null);
  }

  /**
   * Constructor for QueryConnectedUsers.
   *
   * @param sender the sender
   */
  public QueryConnectedUsers(String sender) {
    super(MessageType.QUERY_CONNECTED_USERS, sender, ChatroomProtocol.SERVER_NAME, null);
  }

  /**
   * Parse the QueryConnectedUsers from the input stream.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    super.setSender(in.readUTF());
  }

  /**
   * Send the QueryConnectedUsers to the output stream.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(super.getMessageType().getValue());
    out.writeUTF(super.getSender());
  }

  /**
   * Process the QueryConnectedUsers.
   *
   * @return true
   */
  @Override
  public boolean process() {
    System.out.println(
        MessageFormat.format(QUERY_CONNECTED_USERS_MESSAGE_FORMAT, super.getSender()));
    return true;
  }
}
