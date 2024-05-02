package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GrammarTest {

  @Mock
  private Definition mockStartDefinition;
  private Map<String, Definition> definitions;
  private Grammar grammar;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    definitions = new HashMap<>();
    definitions.put("start", mockStartDefinition);
    grammar = new Grammar("Test Grammar", "A grammar for testing", definitions);
    when(mockStartDefinition.expand(grammar)).thenReturn("Mocked expansion of start");
  }

  @Test
  void generateSuccessfullyExpandsStartDefinition() {
    String result = grammar.generate();
    assertEquals("Mocked expansion of start", result);
  }

  @Test
  void generateThrowsIllegalArgumentExceptionForMissingStartDefinition() {
    Grammar incompleteGrammar = new Grammar("Incomplete Grammar", "Lacks start definition",
        new HashMap<>());
    assertThrows(IllegalArgumentException.class, incompleteGrammar::generate);
  }

  @Test
  void getDefinitionReturnsNonNullForExistingKey() {
    Definition result = grammar.getDefinition("start");
    assertNotNull(result);
  }

  @Test
  void getDefinitionReturnsExpectedDefinitionForExistingKey() {
    Definition result = grammar.getDefinition("start");
    assertSame(mockStartDefinition, result);
  }

  @Test
  void getDefinitionThrowsUndefinedNonTerminalExceptionForMissingKey() {
    assertThrows(UndefinedNonTerminalException.class, () -> grammar.getDefinition("nonexistent"));
  }

  @Test
  void grammarEqualsItself() {
    assertEquals(grammar, grammar);
  }

  @Test
  void grammarEqualsAnotherWithSameContent() {
    Grammar sameGrammar = new Grammar("Test Grammar", "A grammar for testing", definitions);
    assertEquals(grammar, sameGrammar);
  }

  @Test
  void grammarNotEqualsDifferentGrammar() {
    Grammar differentGrammar = new Grammar("Different Grammar", "A different grammar", definitions);
    assertNotEquals(grammar, differentGrammar);
  }

  @Test
  void grammarHashCodeIsConsistent() {
    int expectedHashCode = grammar.hashCode();
    assertEquals(expectedHashCode, grammar.hashCode());
  }

  @Test
  void grammarToStringContainsClassName() {
    String result = grammar.toString();
    assertTrue(result.contains("Grammar["));
  }

  @Test
  void grammarToStringContainsTitle() {
    String result = grammar.toString();
    assertTrue(result.contains("grammarTitle=Test Grammar"));
  }

  @Test
  void grammarToStringContainsDescription() {
    String result = grammar.toString();
    assertTrue(result.contains("grammarDesc=A grammar for testing"));
  }

  @Test
  void grammarToStringContainsDefinitions() {
    String result = grammar.toString();
    assertTrue(result.contains("definitions="));
  }
}