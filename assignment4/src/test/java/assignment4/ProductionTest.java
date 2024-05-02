package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ProductionTest {

  private Grammar mockGrammar;
  private GrammarElement mockElement1;
  private GrammarElement mockElement2;
  private List<GrammarElement> elements;
  private Production production;

  @BeforeEach
  void setUp() {
    mockGrammar = Mockito.mock(Grammar.class);
    mockElement1 = Mockito.mock(GrammarElement.class);
    mockElement2 = Mockito.mock(GrammarElement.class);

    elements = Arrays.asList(mockElement1, mockElement2);
    production = new Production(elements);
  }

  @Test
  void expand_withValidElements_returnsCorrectExpansion() {
    when(mockElement1.expand(mockGrammar)).thenReturn("Hello");
    when(mockElement2.expand(mockGrammar)).thenReturn("World");

    String expected = "Hello World";
    String actual = production.expand(mockGrammar);

    assertEquals(expected, actual,
        "The expansion of the production should correctly combine the elements.");
  }

  @Test
  void equals_withSameElements_returnsTrue() {
    Production other = new Production(elements);
    assertTrue(production.equals(other), "Productions with the same elements should be equal.");
  }

  @Test
  void hashCode_withSameElements_returnsSameHashCode() {
    Production other = new Production(elements);
    assertEquals(production.hashCode(), other.hashCode(),
        "Productions with the same elements should have the same hash code.");
  }

  @Test
  void toString_returnsExpectedString() {
    String expected = "Production[elements=[" + mockElement1 + ", " + mockElement2 + "]]";
    assertEquals(expected, production.toString(),
        "The toString method should return the expected string representation.");
  }
}