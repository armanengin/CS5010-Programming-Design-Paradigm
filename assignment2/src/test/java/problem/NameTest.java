package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameTest {
  private Name name;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Middle", "Doe");
  }

  @Test
  void testConstructorAndGetters() {
    assertEquals("John", name.getFirstName(), "The first name should be 'John'");
    assertEquals("Middle", name.getMiddleName(), "The middle name should be 'Middle'");
    assertEquals("Doe", name.getLastName(), "The last name should be 'Doe'");
  }

  @Test
  void testSetFirstName() {
    name.setFirstName("Jane");
    assertEquals("Jane", name.getFirstName(), "The first name should be updated to 'Jane'");
  }

  @Test
  void testSetMiddleName() {
    name.setMiddleName("Elizabeth");
    assertEquals("Elizabeth", name.getMiddleName(), "The middle name should be updated to 'Elizabeth'");
  }

  @Test
  void testSetLastName() {
    name.setLastName("Smith");
    assertEquals("Smith", name.getLastName(), "The last name should be updated to 'Smith'");
  }

  @Test
  void testEquals() {
    Name sameName = new Name("John", "Middle", "Doe");
    Name differentName = new Name("Jane", "Elizabeth", "Smith");
    assertEquals(name, sameName, "Two names with the same fields should be equal");
    assertNotEquals(name, differentName, "Different names should not be equal");
  }

  @Test
  void testHashCode() {
    Name sameName = new Name("John", "Middle", "Doe");
    assertEquals(name.hashCode(), sameName.hashCode(), "Hash codes should be equal for equal objects");
  }

  @Test
  void testToString() {
    String expectedString = "Name{firstName='John', middleName='Middle', lastName='Doe'}";
    assertEquals(expectedString, name.toString(), "toString should return the correct representation");
  }
}
