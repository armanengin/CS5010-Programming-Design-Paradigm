package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NonTerminalTest {

  @Test
  void testNonTerminalConstructor() {
    NonTerminal nonTerminal = new NonTerminal("NT");
    assertNotNull(nonTerminal);
  }

  @Test
  void testToString() {
    NonTerminal nonTerminal = new NonTerminal("NT");
    String expected = "NonTerminal{text='NT'}";
    assertEquals(expected, nonTerminal.toString());
  }

  @Test
  void testExpandWithDefinedNonTerminal() {
    Grammar mockGrammar = Mockito.mock(Grammar.class);
    Definition mockDefinition = Mockito.mock(Definition.class);

    Mockito.when(mockGrammar.getDefinition("NT")).thenReturn(mockDefinition);
    Mockito.when(mockDefinition.expand(mockGrammar)).thenReturn("expandedText");

    NonTerminal nonTerminal = new NonTerminal("NT");
    String result = nonTerminal.expand(mockGrammar);

    assertEquals("expandedText", result);
  }

  @Test
  void testExpandWithUndefinedNonTerminal() {
    Grammar mockGrammar = Mockito.mock(Grammar.class);

    Mockito.when(mockGrammar.getDefinition("NT"))
        .thenThrow(new UndefinedNonTerminalException("Undefined non-terminal: NT"));

    NonTerminal nonTerminal = new NonTerminal("NT");
    String result = nonTerminal.expand(mockGrammar);

    assertEquals("Undefined non-terminal: NT", result);
  }
}