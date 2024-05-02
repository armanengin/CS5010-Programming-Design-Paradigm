package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * The DisconnectMessage class represents a message that is sent when a client disconnects from the
 * server.
 */
public class DisconnectMessage extends Message {

  private static final String DISCONNECT_MESSAGE_FORMAT = "{0}: {1}";

  /**
   * Constructor.
   */
  public DisconnectMessage() {
    super(MessageType.DISCONNECT_MESSAGE, null, ChatroomProtocol.SERVER_NAME, null);
  }

  /**
   * Constructor.
   *
   * @param sender the sender
   */
  public DisconnectMessage(String sender) {
    super(MessageType.DISCONNECT_MESSAGE, sender, ChatroomProtocol.SERVER_NAME, null);
  }

  /**
   * Parse the DisconnectMessage from the input stream.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    setSender(in.readUTF());
  }

  /**
   * Send the DisconnectMessage to the output stream.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(MessageType.DISCONNECT_MESSAGE.getValue());
    out.writeUTF(getSender());
    out.flush();
  }

  /**
   * Process the DisconnectMessage.
   *
   * @return false
   */
  @Override
  public boolean process() {
    System.out.println(MessageFormat.format(DISCONNECT_MESSAGE_FORMAT, getSender(), getMessage()));
    return false;
  }

}
