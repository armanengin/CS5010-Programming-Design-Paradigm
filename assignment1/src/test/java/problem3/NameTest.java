package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {
  private Name name;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe");
  }

  @Test
  void testConstructorAndGetters() {
    assertEquals("John", name.getFirstName());
    assertEquals("Doe", name.getLastName());
  }

  @Test
  void testEqualsWithSelf() {
    assertTrue(name.equals(name));
  }

  @Test
  void testEqualsWithNull() {
    assertFalse(name.equals(null));
  }

  @Test
  void testEqualsWithDifferentClass() {
    assertFalse(name.equals(new String("Not a Name")));
  }

  @Test
  void testEqualsWithDifferentName() {
    Name differentName = new Name("Jane", "Doe");
    assertFalse(name.equals(differentName));
  }

  @Test
  void testEqualsWithSameName() {
    Name sameName = new Name("John", "Doe");
    assertTrue(name.equals(sameName));
  }

  @Test
  void testHashCode() {
    Name sameName = new Name("John", "Doe");
    assertEquals(name.hashCode(), sameName.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Name{firstName='John', lastName='Doe'}";
    assertEquals(expectedString, name.toString());
  }

}