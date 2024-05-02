package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TerminalTest {

  private Terminal terminal;

  @BeforeEach
  void setUp() {
    terminal = new Terminal("exampleText");
  }

  @Test
  void expand_ReturnsOriginalText() {
    // The Grammar parameter is not used in Terminal.expand, so it can be null
    String expandedText = terminal.expand(null);
    assertEquals("exampleText", expandedText, "Terminal.expand should return the original text.");
  }

  @Test
  void toString_ReturnsCorrectFormat() {
    String expectedString = "Terminal{text='exampleText'}";
    assertEquals(expectedString, terminal.toString(),
        "Terminal.toString should return the correct string representation.");
  }
}