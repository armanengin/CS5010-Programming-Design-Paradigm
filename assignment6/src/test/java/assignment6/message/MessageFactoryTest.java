package assignment6.message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageFactoryTest {
  @Test
  public void testCreateConnectMessage() {
    Message message = MessageFactory.createMessage(MessageType.CONNECT_MESSAGE);
    assertTrue(message instanceof ConnectMessage);
  }

  @Test
  public void testCreateConnectResponse() {
    Message message = MessageFactory.createMessage(MessageType.CONNECT_RESPONSE);
    assertTrue(message instanceof ConnectResponse);
  }

  @Test
  public void testCreateDisconnectMessage() {
    Message message = MessageFactory.createMessage(MessageType.DISCONNECT_MESSAGE);
    assertTrue(message instanceof DisconnectMessage);
  }

  @Test
  public void testCreateQueryConnectedUsers() {
    Message message = MessageFactory.createMessage(MessageType.QUERY_CONNECTED_USERS);
    assertTrue(message instanceof QueryConnectedUsers);
  }

  @Test
  public void testCreateQueryUserResponse() {
    Message message = MessageFactory.createMessage(MessageType.QUERY_USER_RESPONSE);
    assertTrue(message instanceof QueryUserResponse);
  }

  @Test
  public void testCreateBroadcastMessage() {
    Message message = MessageFactory.createMessage(MessageType.BROADCAST_MESSAGE);
    assertTrue(message instanceof BroadcastMessage);
  }

  @Test
  public void testCreateDirectMessage() {
    Message message = MessageFactory.createMessage(MessageType.DIRECT_MESSAGE);
    assertTrue(message instanceof DirectMessage);
  }

  @Test
  public void testCreateFailedMessage() {
    Message message = MessageFactory.createMessage(MessageType.FAILED_MESSAGE);
    assertTrue(message instanceof FailedMessage);
  }

  @Test
  public void testCreateSendInsult() {
    Message message = MessageFactory.createMessage(MessageType.SEND_INSULT);
    assertTrue(message instanceof SendInsult);
  }
}