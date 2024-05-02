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

class QueryUserResponseTest {

  // Constructor Tests
  @Test
  public void testConstructorSetsNumberOfUsers() {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    assertEquals(3, message.getNumberOfUsers());
  }

  @Test
  public void testConstructorSetsUsersArray() {
    String[] users = {"user1", "user2", "user3"};
    QueryUserResponse message = new QueryUserResponse(3, users);
    assertArrayEquals(users, message.getUsers());
  }

  // Parse Method Tests
  @Test
  public void testParseNumberOfUsers() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(3);
    dos.writeUTF("user1");
    dos.writeUTF("user2");
    dos.writeUTF("user3");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    QueryUserResponse message = new QueryUserResponse();
    message.parse(dis);

    assertEquals(3, message.getNumberOfUsers());
  }

  @Test
  public void testParseUsers() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeInt(3);
    dos.writeUTF("user1");
    dos.writeUTF("user2");
    dos.writeUTF("user3");

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    QueryUserResponse message = new QueryUserResponse();
    message.parse(dis);

    assertArrayEquals(new String[]{"user1", "user2", "user3"}, message.getUsers());
  }

  // Send Method Tests
  @Test
  public void testSendWritesMessageType() throws IOException {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);

    assertEquals(MessageType.QUERY_USER_RESPONSE.getValue(), dis.readInt());
  }

  @Test
  public void testSendWritesNumberOfUsers() throws IOException {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type

    assertEquals(3, dis.readInt());
  }


  // Tests to check each user name written by the send method
  @Test
  public void testSendWritesFirstUserName() throws IOException {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    dis.readInt(); // Skip number of users

    assertEquals("user1", dis.readUTF());
  }

  @Test
  public void testSendWritesSecondUserName() throws IOException {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    dis.readInt(); // Skip number of users
    dis.readUTF(); // Skip first user

    assertEquals("user2", dis.readUTF());
  }

  @Test
  public void testSendWritesThirdUserName() throws IOException {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    message.send(dos);

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    DataInputStream dis = new DataInputStream(bais);
    dis.readInt(); // Skip message type
    dis.readInt(); // Skip number of users
    dis.readUTF(); // Skip first user
    dis.readUTF(); // Skip second user

    assertEquals("user3", dis.readUTF());
  }

  // Process Method Tests
  @Test
  public void testProcessReturnsTrue() {
    QueryUserResponse message = new QueryUserResponse(3, "user1", "user2", "user3");
    assertTrue(message.process());
  }

  @Test
  public void testProcessOutputFormat() {
    String[] users = {"user1", "user2", "user3"};
    QueryUserResponse message = new QueryUserResponse(3, users);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);

    message.process();
    System.setOut(System.out); // Reset to original out

    String output = baos.toString().trim();
    assertTrue(output.contains("Server: 3 users connected."));
  }


  // Test for setNumberOfUsers and getNumberOfUsers
  @Test
  public void testSetNumberOfUsers() {
    QueryUserResponse response = new QueryUserResponse();
    response.setNumberOfUsers(3);
    assertEquals(3, response.getNumberOfUsers());
  }

  // Test for setUsers and getUsers
  @Test
  public void testSetUsers() {
    QueryUserResponse response = new QueryUserResponse();
    String[] users = {"Alice", "Bob", "Charlie"};
    response.setUsers(users);
    assertArrayEquals(users, response.getUsers());
  }

  // Tests for equals method
  @Test
  public void testEqualsWithSameObject() {
    QueryUserResponse response = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    assertTrue(response.equals(response));
  }

  @Test
  public void testEqualsWithIdenticalObjects() {
    QueryUserResponse response1 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    QueryUserResponse response2 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    assertTrue(response1.equals(response2));
  }

  @Test
  public void testEqualsWithDifferentObjects() {
    QueryUserResponse response1 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    QueryUserResponse response3 = new QueryUserResponse(2, "Alice", "Bob");
    assertFalse(response1.equals(response3));
  }

  @Test
  public void testEqualsWithNull() {
    QueryUserResponse response1 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    assertFalse(response1.equals(null));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    QueryUserResponse response1 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    assertFalse(response1.equals(new Object()));
  }

  // Tests for hashCode method
  @Test
  public void testHashCodeConsistency() {
    QueryUserResponse response = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    assertEquals(response.hashCode(), response.hashCode());
  }

  @Test
  public void testHashCodeEqualityForIdenticalObjects() {
    QueryUserResponse response1 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    QueryUserResponse response2 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    assertEquals(response1.hashCode(), response2.hashCode());
  }

  @Test
  public void testHashCodeInequalityForDifferentObjects() {
    QueryUserResponse response1 = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    QueryUserResponse response3 = new QueryUserResponse(2, "Alice", "Bob");
    assertNotEquals(response1.hashCode(), response3.hashCode());
  }

  // Test for toString method
  @Test
  public void testToStringFormat() {
    QueryUserResponse response = new QueryUserResponse(3, "Alice", "Bob", "Charlie");
    String expected = "QueryUserResponse{numberOfUsers=3, users=[Alice, Bob, Charlie]}";
    assertEquals(expected, response.toString());
  }
}