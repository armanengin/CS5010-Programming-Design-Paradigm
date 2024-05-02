package assignment5.concurrentSolution;

import static assignment5.concurrentSolution.Main.main;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void tearDown() {
    System.setOut(originalOut);
  }

  @Test
  @DisplayName("Main method runs without throwing exceptions")
  void main_runsWithoutExceptions() {
    String[] args = {};
    assertDoesNotThrow(() -> main(args));
  }

  @Test
  @DisplayName("Main method processes arguments")
  void main_processesArguments() {
    // Run the main method with a specific threshold argument
    main(new String[]{"src" + File.separator + "test" + File.separator + "resources", "10000"});

    // Check that the expected output file has been created
    File file = new File("out", "activity-10000.csv");
    assertTrue(file.exists(),
        "The file activity-10000.csv should exist after running the main method with a threshold of 10000.");
  }
}