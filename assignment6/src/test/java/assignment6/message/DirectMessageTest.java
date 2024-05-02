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

class DirectMessageTest {
  @Test
  public void testConstructorWithParametersSetsSender() {
    DirectMessage message = new DirectMessage("sender", "receiver", "Hello, World!");
    assertEquals("sender", message.getSender());
  }

  @Test
  public void testConstructorWithParametersSetsReceiver() {
    DirectMessage message = new DirectMessage("sender", "receiver", "Hello, World!");
    assertEquals("receiver", message.getReceiver());
  }

  @Test
  public void testConstructorWithParametersSetsMessage() {
    DirectMessage message = new DirectMessage("sender", "receiver", "Hello, World!");
    assertEquals("Hello, World!", message.getMessage());
  }

  @Test
  public void testParseSetsSender() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("sender");
    dos.writeUTF("receiver");
    dos.writeUTF("message");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    DirectMessage message = new DirectMessage();
    message.parse(dis);

    assertEquals("sender", message.getSender());
  }

  @Test
  public void testParseSetsReceiver() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("sender");
    dos.writeUTF("receiver");
    dos.writeUTF("message");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    DirectMessage message = new DirectMessage();
    message.parse(dis);

    assertEquals("receiver", message.getReceiver());
  }

  @Test
  public void testParseSetsMessage() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("sender");
    dos.writeUTF("receiver");
    dos.writeUTF("message");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    DirectMessage message = new DirectMessage();
    message.parse(dis);

    assertEquals("message", message.getMessage());
  }

  @Test
  public void testSendWritesCorrectMessageType() throws IOException {
    DirectMessage message = new DirectMessage("sender", "receiver", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.DIRECT_MESSAGE.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesCorrectSender() throws IOException {
    DirectMessage message = new DirectMessage("sender", "receiver", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type

    assertEquals("sender", dis.readUTF());
  }

  @Test
  public void testSendWritesCorrectReceiver() throws IOException {
    DirectMessage message = new DirectMessage("sender", "receiver", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    dis.readUTF(); // Skip sender

    assertEquals("receiver", dis.readUTF());
  }

  @Test
  public void testSendWritesCorrectMessageContent() throws IOException {
    DirectMessage message = new DirectMessage("sender", "receiver", "message");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    dis.readUTF(); // Skip sender
    dis.readUTF(); // Skip receiver

    assertEquals("message", dis.readUTF());
  }

  @Test
  public void testProcessOutputFormat() {
    String sender = "Alice";
    String receiver = "Bob";
    String messageContent = "Hello, Bob!";
    DirectMessage message = new DirectMessage(sender, receiver, messageContent);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // Reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains("Alice -> Bob: Hello, Bob!"));
  }
}