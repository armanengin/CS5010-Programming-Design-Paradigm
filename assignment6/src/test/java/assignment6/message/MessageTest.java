package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageTest {

  static class TestMessage extends Message {
    public TestMessage(MessageType type, String sender, String receiver, String message) {
      super(type, sender, receiver, message);
    }

    @Override
    public boolean process() {
      return true; // Simple return for testing purpose
    }

    @Override
    public void parse(DataInputStream in) throws IOException {
      setSender(in.readUTF());
      setReceiver(in.readUTF());
      setMessage(in.readUTF());
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
      out.writeInt(getMessageType().getValue());
      out.writeUTF(getSender());
      out.writeUTF(getReceiver());
      out.writeUTF(getMessage());
    }
  }

  @Test
  public void testMessageTypeIsSetCorrectly() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    assertEquals(MessageType.CONNECT_MESSAGE, message.getMessageType());
  }

  @Test
  public void testSenderIsSetCorrectly() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    assertEquals("Alice", message.getSender());
  }

  @Test
  public void testReceiverIsSetCorrectly() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    assertEquals("Server", message.getReceiver());
  }

  @Test
  public void testMessageIsSetCorrectly() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    assertEquals("Hi", message.getMessage());
  }

  @Test
  public void testParseSetsSenderCorrectly() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("Alice");
    dos.writeUTF("Server");
    dos.writeUTF("Hi");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, null, null, null);
    message.parse(dis);

    assertEquals("Alice", message.getSender());
  }

  @Test
  public void testParseSetsReceiverCorrectly() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("Alice");
    dos.writeUTF("Server");
    dos.writeUTF("Hi");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, null, null, null);
    message.parse(dis);

    assertEquals("Server", message.getReceiver());
  }

  @Test
  public void testParseSetsMessageCorrectly() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF("Alice");
    dos.writeUTF("Server");
    dos.writeUTF("Hi");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, null, null, null);
    message.parse(dis);

    assertEquals("Hi", message.getMessage());
  }

  @Test
  public void testSendWritesMessageTypeCorrectly() throws IOException {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.CONNECT_MESSAGE.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesSenderCorrectly() throws IOException {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip the message type

    assertEquals("Alice", dis.readUTF());
  }

  @Test
  public void testSendWritesReceiverCorrectly() throws IOException {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip the message type
    dis.readUTF(); // Skip the sender

    assertEquals("Server", dis.readUTF());
  }

  @Test
  public void testSendWritesMessageCorrectly() throws IOException {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hi");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip the message type
    dis.readUTF(); // Skip the sender
    dis.readUTF(); // Skip the receiver

    assertEquals("Hi", dis.readUTF());
  }


  @Test
  public void testEqualsWithSameObject() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    assertTrue(message.equals(message));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    assertFalse(message.equals(new Object()));
  }

  @Test
  public void testEqualsWithDifferentContent() {
    Message message1 = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    Message message2 = new TestMessage(MessageType.CONNECT_MESSAGE, "Bob", "Server", "Hello");
    assertFalse(message1.equals(message2));
  }

  @Test
  public void testEqualsWithSameContent() {
    Message message1 = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    Message message2 = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    assertTrue(message1.equals(message2));
  }

  @Test
  public void testHashCodeConsistency() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    int hashCode1 = message.hashCode();
    int hashCode2 = message.hashCode();
    assertEquals(hashCode1, hashCode2);
  }

  @Test
  public void testHashCodeDifference() {
    Message message1 = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    Message message2 = new TestMessage(MessageType.CONNECT_MESSAGE, "Bob", "Server", "Hello");
    assertNotEquals(message1.hashCode(), message2.hashCode());
  }

  @Test
  public void testToStringFormat() {
    Message message = new TestMessage(MessageType.CONNECT_MESSAGE, "Alice", "Server", "Hello");
    String expected = "Message{messageType=MessageType{value=19}, sender='Alice', receiver='Server', message='Hello'}";
    assertEquals(expected, message.toString());
  }
}