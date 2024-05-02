package assignment4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GrammarElementTest {

  @Test
  void equalsWithSameObject() {
    GrammarElement element = new TestableGrammarElement("text");
    assertEquals(element, element);
  }

  @Test
  void equalsWithDifferentObjectSameText() {
    GrammarElement element1 = new TestableGrammarElement("text");
    GrammarElement element2 = new TestableGrammarElement("text");
    assertEquals(element1, element2);
  }

  @Test
  void equalsWithDifferentObjectDifferentText() {
    GrammarElement element1 = new TestableGrammarElement("text1");
    GrammarElement element2 = new TestableGrammarElement("text2");
    assertNotEquals(element1, element2);
  }

  @Test
  void equalsWithNull() {
    GrammarElement element = new TestableGrammarElement("text");
    assertNotEquals(null, element);
  }

  @Test
  void equalsWithDifferentType() {
    GrammarElement element = new TestableGrammarElement("text");
    Object other = new Object();
    assertNotEquals(element, other);
  }

  @Test
  void hashCodeConsistencyCheck() {
    GrammarElement element = new TestableGrammarElement("text");
    int expectedHashCode = element.hashCode();
    assertEquals(expectedHashCode, element.hashCode());
  }

  @Test
  void toStringContainsClassName() {
    GrammarElement element = new TestableGrammarElement("text");
    String expectedString = "GrammarElement{";
    assertTrue(element.toString().contains(expectedString));
  }

  @Test
  void toStringContainsText() {
    GrammarElement element = new TestableGrammarElement("text");
    String expectedString = "text='" + element.text + '\'';
    assertTrue(element.toString().contains(expectedString));
  }

  // Concrete subclass for testing abstract GrammarElement
  static class TestableGrammarElement extends GrammarElement {

    TestableGrammarElement(String text) {
      super(text);
    }

    @Override
    public String expand(Grammar grammar) {
      return text;
    }
  }
}