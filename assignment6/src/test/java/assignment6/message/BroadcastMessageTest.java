package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import assignment6.message.BroadcastMessage;
import assignment6.message.MessageType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BroadcastMessageTest {

  @Test
  public void testBroadcastMessageHasCorrectType() {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    assertEquals(MessageType.BROADCAST_MESSAGE, message.getMessageType());
  }

  @Test
  public void testBroadcastMessageHasCorrectSender() {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    assertEquals("sender", message.getSender());
  }

  @Test
  public void testBroadcastMessageHasCorrectReceiver() {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    assertEquals("all", message.getReceiver());
  }

  @Test
  public void testBroadcastMessageHasCorrectMessage() {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    assertEquals("message", message.getMessage());
  }

  @Test
  public void testParseSetsCorrectSender() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(baos);
    out.writeUTF("sender");
    out.writeUTF("message");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream in = new DataInputStream(bais);

    BroadcastMessage message = new BroadcastMessage();
    message.parse(in);

    assertEquals("sender", message.getSender());
  }

  @Test
  public void testParseSetsCorrectMessage() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(baos);
    out.writeUTF("sender");
    out.writeUTF("message");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream in = new DataInputStream(bais);

    BroadcastMessage message = new BroadcastMessage();
    message.parse(in);

    assertEquals("message", message.getMessage());
  }

  @Test
  public void testSendWritesCorrectMessageType() throws IOException {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(baos);
    message.send(out);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream in = new DataInputStream(bais);

    assertEquals(MessageType.BROADCAST_MESSAGE.getValue(), in.readInt());
  }

  @Test
  public void testSendWritesCorrectSender() throws IOException {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(baos);
    message.send(out);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream in = new DataInputStream(bais);
    in.readInt(); // skip the message type to get to the sender

    assertEquals("sender", in.readUTF());
  }

  @Test
  public void testSendWritesCorrectMessageContent() throws IOException {
    BroadcastMessage message = new BroadcastMessage("sender", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(baos);
    message.send(out);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream in = new DataInputStream(bais);
    in.readInt(); // skip the message type
    in.readUTF(); // skip the sender

    assertEquals("message", in.readUTF());
  }

  @Test
  public void testProcessOutputsCorrectFormat() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(baos));

    BroadcastMessage message = new BroadcastMessage("sender", "Hello, World!");
    message.process();
    System.setOut(System.out);

    String output = baos.toString().trim();
    assertTrue(output.endsWith("sender: Hello, World!"));
  }
}