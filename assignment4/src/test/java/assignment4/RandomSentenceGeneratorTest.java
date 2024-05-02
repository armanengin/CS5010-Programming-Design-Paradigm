package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomSentenceGeneratorTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  void main_NoArguments_ShowsUsage() {
    RandomSentenceGenerator.main(new String[]{});
    String output = outputStreamCaptor.toString().trim();
    assertEquals("Usage: ./assignment4-1.0.jar <grammar directory path>", output);
  }

  @Test
  void main_WithValidArguments_ExpectedBehavior() {
    // This directory should exist and contain valid grammar files for the CLI to process.
    String validGrammarDirectoryPath = "src/main/resources";
    RandomSentenceGenerator.main(new String[]{validGrammarDirectoryPath});
  }

}