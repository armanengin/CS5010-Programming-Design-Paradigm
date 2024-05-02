package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChatroomProtocolTest {

  // Helper method to create a DataInputStream mock
  private DataInputStream createDataInputStreamMock(MessageType messageType, String sender, String content) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(messageType.getValue());
    dos.writeUTF(sender);
    dos.writeUTF(content);
    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    return new DataInputStream(bais);
  }

  @Test
  public void testProcessInputReturnsNonNullMessage() throws IOException {
    DataInputStream in = createDataInputStreamMock(MessageType.BROADCAST_MESSAGE, "sender", "message");
    Message result = ChatroomProtocol.processInput(in);
    assertNotNull(result);
  }

  @Test
  public void testProcessInputReturnsBroadcastMessageInstance() throws IOException {
    DataInputStream in = createDataInputStreamMock(MessageType.BROADCAST_MESSAGE, "sender", "message");
    Message result = ChatroomProtocol.processInput(in);
    assertTrue(result instanceof BroadcastMessage);
  }

  @Test
  public void testProcessInputSetsSenderCorrectly() throws IOException {
    String sender = "sender";
    DataInputStream in = createDataInputStreamMock(MessageType.BROADCAST_MESSAGE, sender, "message");
    Message result = ChatroomProtocol.processInput(in);
    assertEquals(sender, result.getSender()); // This assumes that there's a getSender() method in your Message class.
  }

  @Test
  public void testProcessInputSetsMessageCorrectly() throws IOException {
    String messageContent = "message";
    DataInputStream in = createDataInputStreamMock(MessageType.BROADCAST_MESSAGE, "sender", messageContent);
    Message result = ChatroomProtocol.processInput(in);
    assertEquals(messageContent, result.getMessage()); // This assumes that there's a getMessage() method in your Message class.
  }
}