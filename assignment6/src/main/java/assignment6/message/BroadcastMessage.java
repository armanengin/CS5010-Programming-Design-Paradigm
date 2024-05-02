package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * BroadcastMessage class.
 */
public class BroadcastMessage extends Message {

  private static final String RECEIVER = "all";
  private static final String BROADCAST_MESSAGE_FORMAT = "{0}: {1}";

  /**
   * Constructor.
   */
  public BroadcastMessage() {
    super(MessageType.BROADCAST_MESSAGE, null, RECEIVER, null);
  }

  /**
   * Constructor.
   *
   * @param sender  the sender
   * @param message the message
   */
  public BroadcastMessage(String sender, String message) {
    super(MessageType.BROADCAST_MESSAGE, sender, RECEIVER, message);
  }

  /**
   * Parse the BroadcastMessage from the input stream.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    super.setSender(in.readUTF());
    super.setMessage(in.readUTF());
  }

  /**
   * Send the BroadcastMessage to the output stream.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(MessageType.BROADCAST_MESSAGE.getValue());
    out.writeUTF(super.getSender());
    out.writeUTF(super.getMessage());
    out.flush();
  }

  /**
   * Process the BroadcastMessage.
   *
   * @return true
   */
  @Override
  public boolean process() {
    System.out.println(
        MessageFormat.format(BROADCAST_MESSAGE_FORMAT, super.getSender(), super.getMessage()));
    return true;
  }

}
