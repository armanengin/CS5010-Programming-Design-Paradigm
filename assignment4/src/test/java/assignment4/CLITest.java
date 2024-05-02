package assignment4;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CLITest {

  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    resetCLIInstance(); // Reset the CLI singleton instance before each test
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }

  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }

  private String getOutput() {
    return testOut.toString();
  }

  private void resetCLIInstance() throws NoSuchFieldException, IllegalAccessException {
    Field instance = CLI.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null); // Set the static instance to null
  }

  @Test
  void getInstanceReturnsTheSameInstance() {
    CLI firstInstance = CLI.getInstance();
    CLI secondInstance = CLI.getInstance();
    assertSame(firstInstance, secondInstance,
        "getInstance should return the same instance every time");
  }

  @Test
  void testLoadingGrammarsMessage() {
    provideInput("q\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/resources");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.PROMPT_LOADING));
  }

  @Test
  void testLoadingBadGrammars() {
    // Execute the method and expect IOException to be thrown
    assertThrows(RuntimeException.class, () ->
        CLI.getInstance().parseGrammarFromFile("bad-grammar.json"));

  }

  @Test
  void testNoGrammarsFoundMessage() {
    provideInput("q\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.NO_GRAMMARS_FOUND_ERROR));
  }

  @Test
  void testGrammarSelectionPrompt() {
    provideInput("1\nq\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/resources");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.PROMPT_GRAMMAR_CHOICE));
  }

  @Test
  void testInvalidInputForGrammarSelection() {
    provideInput("invalid\nq\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/resources");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.PROMPT_INVALID_INPUT));
  }

  @Test
  void testOutOfRangeGrammarSelection() {
    provideInput("999\nq\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/resources");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.PROMPT_OUT_OF_RANGE_ERROR));
  }

  @Test
  void testContinuePromptAfterGeneratingSentence() {
    provideInput("1\ny\nn\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/resources");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.PROMPT_CONTINUE));
  }

  @Test
  void testInvalidInputForContinuePrompt() {
    provideInput("1\ninvalid\nn\n");
    CLI.getInstance().loadAndSelectGrammar("src/main/resources");
    String expected = getOutput();
    assertTrue(expected.contains(CLI.PROMPT_INVALID_INPUT_FOR_CONTINUE));
  }

  @Test
  void toStringContainsScanner() {
    CLI instance = CLI.getInstance();
    String instanceString = instance.toString();
    assertTrue(instanceString.contains("scanner="), "toString should contain 'scanner='");
  }

  @Test
  void toStringContainsGrammars() {
    CLI instance = CLI.getInstance();
    String instanceString = instance.toString();
    assertTrue(instanceString.contains(", grammars="), "toString should contain ', grammars='");
  }

}