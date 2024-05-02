package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DefinitionTest {

  @Mock
  private Grammar mockGrammar;
  private Production production1;
  private Production production2;
  private List<Production> productionList;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    production1 = mock(Production.class);
    production2 = mock(Production.class);
    when(production1.expand(mockGrammar)).thenReturn("expandedProduction1");
    when(production2.expand(mockGrammar)).thenReturn("expandedProduction2");

    productionList = Arrays.asList(production1, production2);

  }


  @Test
  void testExpandReturnsNonNullValue() {
    Definition definition = new Definition(productionList);
    String expanded = definition.expand(mockGrammar);
    assertNotNull(expanded);
  }

  @Test
  void testExpandReturnsExpectedString() {
    Definition definition = new Definition(productionList);
    String expanded = definition.expand(mockGrammar);
    assertTrue(expanded.equals("expandedProduction1") || expanded.equals("expandedProduction2"));
  }

  @Test
  void testEqualsSameObject() {
    Definition definition = new Definition(productionList);
    assertEquals(definition, definition);
  }

  @Test
  void testEqualsDifferentObjectsEqual() {
    Definition definition1 = new Definition(productionList);
    Definition definition2 = new Definition(productionList);
    assertEquals(definition1, definition2);
  }

  @Test
  void testHashCodeConsistentWithEquals() {
    Definition definition1 = new Definition(productionList);
    Definition definition2 = new Definition(productionList);
    assertEquals(definition1.hashCode(), definition2.hashCode());
  }

  @Test
  void testToStringContainsCorrectInformation() {
    Definition definition = new Definition(productionList);
    String expectedString = "Definition[productions=" + productionList + ']';
    assertEquals(expectedString, definition.toString());
  }
}