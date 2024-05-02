package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrammarParserTest {

  private String validJson;
  private String incompleteJson;
  private String invalidJsonSyntax;

  @BeforeEach
  void setUp() {
    validJson = """
        {
          "grammarTitle": "Test Grammar",
          "grammarDesc": "A simple test grammar.",
          "start": ["<expression>"],
          "expression": ["test expression"]
        }
        """;

    incompleteJson = """
        {
          "grammarDesc": "A simple test grammar.",
          "start": ["<expression>"],
          "expression": ["test expression"]
        }
        """;

    invalidJsonSyntax = """
        {
          "grammarTitle": "Test Grammar",
          "grammarDesc": "A simple test grammar.",
          "start": ["<expression>"],
          "expression": ["test expression"
        }
        """;
  }

  @Test
  void parseValidJson_ExtractsCorrectTitle() {
    Grammar grammar = GrammarParser.parse(validJson);
    assertEquals("Test Grammar", grammar.grammarTitle());
  }

  @Test
  void parseValidJson_ExtractsCorrectDescription() {
    Grammar grammar = GrammarParser.parse(validJson);
    assertEquals("A simple test grammar.", grammar.grammarDesc());
  }

  @Test
  void parseValidJson_IncludesStartDefinition() {
    Grammar grammar = GrammarParser.parse(validJson);
    assertTrue(grammar.definitions().containsKey("start"));
  }

  @Test
  void parseValidJson_IncludesExpressionDefinition() {
    Grammar grammar = GrammarParser.parse(validJson);
    assertTrue(grammar.definitions().containsKey("expression"));
  }

  @Test
  void parseValidJson_ExpressionDefinitionHasCorrectProduction() {
    Grammar grammar = GrammarParser.parse(validJson);
    String expectedProduction = "test expression";
    assertEquals(expectedProduction,
        grammar.definitions().get("expression").productions().get(0).expand(grammar));
  }


  @Test
  void parseIncompleteJson_ThrowsException() {
    assertThrows(NullPointerException.class, () -> GrammarParser.parse(incompleteJson));
  }

  @Test
  void parseInvalidJsonSyntax_ThrowsException() {
    assertThrows(JsonSyntaxException.class, () -> GrammarParser.parse(invalidJsonSyntax));
  }
}