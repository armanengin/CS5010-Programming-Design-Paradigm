package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FailedMessageTest {

  @Test
  public void testConstructorWithoutParametersSetsSender() {
    FailedMessage message = new FailedMessage();
    assertEquals(ChatroomProtocol.SERVER_NAME, message.getSender());
  }

  @Test
  public void testConstructorWithParametersSetsReceiver() {
    String expectedReceiver = "receiver";
    FailedMessage message = new FailedMessage(expectedReceiver, "Failure reason");
    assertEquals(expectedReceiver, message.getReceiver());
  }

  @Test
  public void testConstructorWithParametersSetsMessage() {
    String expectedMessage = "Failure reason";
    FailedMessage message = new FailedMessage("receiver", expectedMessage);
    assertEquals(expectedMessage, message.getMessage());
  }

  @Test
  public void testParseSetsMessage() throws IOException {
    String expectedMessage = "Test failure message";
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(expectedMessage);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    FailedMessage message = new FailedMessage();
    message.parse(dis);

    assertEquals(expectedMessage, message.getMessage());
  }

  @Test
  public void testSendWritesCorrectMessageType() throws IOException {
    FailedMessage message = new FailedMessage("receiver", "Failure reason");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.FAILED_MESSAGE.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesCorrectMessageContent() throws IOException {
    String expectedMessage = "Failure reason";
    FailedMessage message = new FailedMessage("receiver", expectedMessage);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type

    assertEquals(expectedMessage, dis.readUTF());
  }

  @Test
  public void testProcessOutputsCorrectFormat() {
    String sender = ChatroomProtocol.SERVER_NAME;
    String messageContent = "Failure reason";
    FailedMessage message = new FailedMessage("receiver", messageContent);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // Reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains(sender + ": " + messageContent));
  }

  @Test
  public void testProcessReturnsTrue() {
    FailedMessage message = new FailedMessage("receiver", "Failure reason");
    assertTrue(message.process());
  }
}