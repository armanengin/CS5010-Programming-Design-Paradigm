package problem1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHandlerTest {
  private CommandHandler commandHandler;
  private String[] initialCommands;
  @BeforeEach
  void setUp() {
    initialCommands = new String[]{"--csv-file", "data.csv", "--output-dir", "output"};
    commandHandler = new CommandHandler(initialCommands);
  }

  @Test
  void getCommandArguments_Positive() {
    HashMap<String, String> expectedArgs = new HashMap<>();
    expectedArgs.put("--csv-file", "data.csv");
    expectedArgs.put("--output-dir", "output");

    assertEquals(expectedArgs, commandHandler.getCommandArguments());
  }

  @Test
  void missingCsvFileThrowsException() {
    String[] commands = {"--output-dir", "/output"};
    CommandHandler handler = new CommandHandler(commands);
    Exception exception = assertThrows(IllegalArgumentException.class, handler::getCommandArguments);
    assertTrue(exception.getMessage().contains("Missing argument --csv-file"));
  }

  @Test
  void missingOutputDirThrowsException() {
    String[] commands = {"--csv-file", "/data.csv"};
    CommandHandler handler = new CommandHandler(commands);
    Exception exception = assertThrows(IllegalArgumentException.class, handler::getCommandArguments);
    assertTrue(exception.getMessage().contains("Missing argument --output-dir"));
  }

  @Test
  void emailWithoutTemplateThrowsException() {
    String[] commands = {"--email", "--csv-file", "/data.csv", "--output-dir", "/output"};
    CommandHandler handler = new CommandHandler(commands);
    Exception exception = assertThrows(IllegalArgumentException.class, handler::getCommandArguments);
    assertTrue(exception.getMessage().contains("Email operation initiated without an email template"));
  }

  @Test
  void letterWithoutTemplateThrowsException() {
    String[] commands = {"--letter", "--csv-file", "/data.csv", "--output-dir", "/output"};
    CommandHandler handler = new CommandHandler(commands);
    Exception exception = assertThrows(IllegalArgumentException.class, handler::getCommandArguments);
    assertTrue(exception.getMessage().contains("Letter operation initiated without a letter template"));
  }

  @Test
  void emailTemplateWithoutEmailThrowsException() {
    String[] commands = {"--email-template", "/template.txt", "--csv-file", "/data.csv", "--output-dir", "/output"};
    CommandHandler handler = new CommandHandler(commands);
    Exception exception = assertThrows(IllegalArgumentException.class, handler::getCommandArguments);
    assertTrue(exception.getMessage().contains("An email template is provided without specifying the email operation"));
  }

  @Test
  void letterTemplateWithoutLetterThrowsException() {
    String[] commands = {"--letter-template", "/template.txt", "--csv-file", "/data.csv", "--output-dir", "/output"};
    CommandHandler handler = new CommandHandler(commands);
    Exception exception = assertThrows(IllegalArgumentException.class, handler::getCommandArguments);
    assertTrue(exception.getMessage().contains("A letter template is provided without specifying the letter operation"));
  }

  @Test
  void getCommands() {
    assertArrayEquals(initialCommands, commandHandler.getCommands());
  }

  @Test
  void setCommands() {
    String[] newCommands = new String[]{"--letter", "--letter-template", "template.txt"};
    commandHandler.setCommands(newCommands);

    assertArrayEquals(newCommands, commandHandler.getCommands());
  }

  @Test
  void testEquals_SameInstance() {
    assertEquals(commandHandler, commandHandler);
  }

  @Test
  void testEquals_DifferentInstanceEqualCommands() {
    CommandHandler anotherCommandHandler = new CommandHandler(initialCommands);
    assertEquals(commandHandler, anotherCommandHandler);
  }

  @Test
  void testEquals_DifferentCommands() {
    String[] differentCommands = new String[]{"--letter"};
    CommandHandler differentCommandHandler = new CommandHandler(differentCommands);
    assertNotEquals(commandHandler, differentCommandHandler);
  }

  @Test
  void testHashCode() {
    CommandHandler anotherCommandHandler = new CommandHandler(initialCommands);
    assertEquals(commandHandler.hashCode(), anotherCommandHandler.hashCode());
  }

  @Test
  void testToString() {
    String expectedToString = "CommandHandler{commands=" + java.util.Arrays.toString(initialCommands) + "}";
    assertEquals(expectedToString, commandHandler.toString());
  }
}