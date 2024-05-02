package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PosnTest {

  @Test
  void testEqualsReflexive() {
    Posn posn = new Posn(1, 2);
    assertEquals(posn, posn, "A posn should be equal to itself.");
  }

  @Test
  void testEqualsNull() {
    Posn posn = new Posn(1, 2);
    assertNotEquals(posn, null, "A posn should not be equal to null.");
  }

  @Test
  void testEqualsDifferentClass() {
    Posn posn = new Posn(1, 2);
    Object otherObject = new Object();
    assertNotEquals(posn, otherObject, "A posn should not be equal to an object of a different type.");
  }

  @Test
  void testEqualsDifferentX() {
    Posn posn1 = new Posn(1, 2);
    Posn posn2 = new Posn(3, 2);
    assertNotEquals(posn1, posn2, "Posn objects with different 'x' values should not be equal.");
  }

  @Test
  void testEqualsDifferentY() {
    Posn posn1 = new Posn(1, 2);
    Posn posn2 = new Posn(1, 3);
    assertNotEquals(posn1, posn2, "Posn objects with different 'y' values should not be equal.");
  }

  @Test
  void testEqualsBothNullFields() {
    Posn posn1 = new Posn(null, null);
    Posn posn2 = new Posn(null, null);
    assertEquals(posn1, posn2, "Two Posn objects with null fields should be equal.");
  }

  @Test
  void testHashCodeConsistency() {
    Posn posn1 = new Posn(1, 2);
    Posn posn2 = new Posn(1, 2);
    assertEquals(posn1.hashCode(), posn2.hashCode(), "Hash codes should be the same for equal Posn objects.");
  }

  @Test
  void testHashCodeDifferent() {
    Posn posn1 = new Posn(1, 2);
    Posn posn2 = new Posn(3, 4);
    assertNotEquals(posn1.hashCode(), posn2.hashCode(), "Hash codes should be different for non-equal Posn objects.");
  }
}