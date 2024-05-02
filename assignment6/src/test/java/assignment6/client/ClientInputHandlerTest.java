package assignment6.client;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientInputHandlerTest {

  @Mock
  private Client client;

  @Mock
  private BufferedReader stdIn;

  @Mock
  private PrintWriter out;

  private ClientInputHandler inputHandler;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    inputHandler = new ClientInputHandler(client);
  }

  @Test
  void run() throws IOException {
    when(client.isConnected()).thenReturn(true, true, false);
    when(client.getStdIn()).thenReturn(stdIn);
    when(stdIn.readLine()).thenReturn("@ recipient message", "logoff");

    inputHandler.run();

    verify(client, times(3)).isConnected();
    verify(client, times(1)).getStdIn();
    verify(stdIn, times(1)).readLine();
  }

  @Test
  void testEquals() {
    Client otherClient = mock(Client.class);
    ClientInputHandler otherHandler = new ClientInputHandler(otherClient);

    assertTrue(inputHandler.equals(inputHandler));
    assertFalse(inputHandler.equals(otherHandler));
    assertFalse(inputHandler.equals(null));
    assertFalse(inputHandler.equals(new Object()));
  }

  @Test
  void testHashCode() {
    Client otherClient = mock(Client.class);
    ClientInputHandler otherHandler = new ClientInputHandler(otherClient);

    assertEquals(inputHandler.hashCode(), inputHandler.hashCode());
    assertNotEquals(inputHandler.hashCode(), otherHandler.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "ClientInputHandler{client=" + client + "}";
    assertEquals(expectedString, inputHandler.toString());
  }
}