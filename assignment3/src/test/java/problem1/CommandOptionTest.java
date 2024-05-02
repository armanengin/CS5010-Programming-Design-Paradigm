package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandOptionTest {

  @Test
  void getCommand_CSV_FILE() {
    assertEquals("--csv-file", CommandOption.CSV_FILE.getCommand());
  }

  @Test
  void getCommand_EMAIL() {
    assertEquals("--email", CommandOption.EMAIL.getCommand());
  }

  @Test
  void getCommand_EMAIL_TEMPLATE() {
    assertEquals("--email-template", CommandOption.EMAIL_TEMPLATE.getCommand());
  }

  @Test
  void getCommand_LETTER() {
    assertEquals("--letter", CommandOption.LETTER.getCommand());
  }

  @Test
  void getCommand_LETTER_TEMPLATE() {
    assertEquals("--letter-template", CommandOption.LETTER_TEMPLATE.getCommand());
  }

  @Test
  void getCommand_OUTPUT_DIRECTORY() {
    assertEquals("--output-dir", CommandOption.OUTPUT_DIRECTORY.getCommand());
  }

  @Test
  void toString_ReturnsCommand_CSV_FILE() {
    assertEquals("CommandOption{command='--csv-file'}", CommandOption.CSV_FILE.toString());
  }

  @Test
  void toString_ReturnsCommand_EMAIL() {
    assertEquals("CommandOption{command='--email'}", CommandOption.EMAIL.toString());
  }

  @Test
  void toString_ReturnsCommand_EMAIL_TEMPLATE() {
    assertEquals("CommandOption{command='--email-template'}", CommandOption.EMAIL_TEMPLATE.toString());
  }

  @Test
  void toString_ReturnsCommand_LETTER() {
    assertEquals("CommandOption{command='--letter'}", CommandOption.LETTER.toString());
  }

  @Test
  void toString_ReturnsCommand_LETTER_TEMPLATE() {
    assertEquals("CommandOption{command='--letter-template'}", CommandOption.LETTER_TEMPLATE.toString());
  }

  @Test
  void toString_ReturnsCommand_OUTPUT_DIRECTORY() {
    assertEquals("CommandOption{command='--output-dir'}", CommandOption.OUTPUT_DIRECTORY.toString());
  }
}