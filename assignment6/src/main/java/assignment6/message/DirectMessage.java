package assignment6.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

/**
 * DirectMessage class.
 */
public class DirectMessage extends Message {

  private static final String DIRECT_MESSAGE_FORMAT = "{0} -> {1}: {2}";

  /**
   * Default constructor.
   */
  public DirectMessage() {
    super(MessageType.DIRECT_MESSAGE, null, null, null);
  }

  /**
   * Constructor with parameters.
   *
   * @param sender   the sender of the message
   * @param receiver the receiver of the message
   * @param message  the message
   */
  public DirectMessage(String sender, String receiver, String message) {
    super(MessageType.DIRECT_MESSAGE, sender, receiver, message);
  }

  /**
   * Parse the DirectMessage.
   *
   * @param in the input stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void parse(DataInputStream in) throws IOException {
    super.setSender(in.readUTF());
    super.setReceiver(in.readUTF());
    super.setMessage(in.readUTF());
  }

  /**
   * Send the DirectMessage.
   *
   * @param out the output stream
   * @throws IOException if an I/O error occurs
   */
  @Override
  public void send(DataOutputStream out) throws IOException {
    out.writeInt(super.getMessageType().getValue());
    out.writeUTF(super.getSender());
    out.writeUTF(super.getReceiver());
    out.writeUTF(super.getMessage());
  }

  /**
   * Process the DirectMessage.
   *
   * @return true if the message is processed successfully
   */
  @Override
  public boolean process() {
    System.out.println(
        MessageFormat.format(DIRECT_MESSAGE_FORMAT, super.getSender(), super.getReceiver(),
            super.getMessage()));
    return true;
  }

}
