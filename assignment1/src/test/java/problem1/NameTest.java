package problem1;

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
  void constructorSetsFirstNameCorrectly() {
    assertEquals("John", name.getFirstName());
  }

  @Test
  void constructorSetsMiddleNameCorrectly() {
    assertEquals("Middle", name.getMiddleName());
  }

  @Test
  void constructorSetsLastNameCorrectly() {
    assertEquals("Doe", name.getLastName());
  }

  @Test
  void setFirstNameUpdatesValueCorrectly() {
    name.setFirstName("Jane");
    assertEquals("Jane", name.getFirstName());
  }

  @Test
  void setMiddleNameUpdatesValueCorrectly() {
    name.setMiddleName("M");
    assertEquals("M", name.getMiddleName());
  }

  @Test
  void setLastNameUpdatesValueCorrectly() {
    name.setLastName("Smith");
    assertEquals("Smith", name.getLastName());
  }

  @Test
  void equalsReturnsTrueForSameObject() {
    assertEquals(name, name);
  }

  @Test
  void equalsReturnsTrueForDifferentObjectWithSameValues() {
    Name otherName = new Name("John", "Middle", "Doe");
    assertEquals(name, otherName);
  }

  @Test
  void equalsReturnsFalseForDifferentObjectWithDifferentValues() {
    Name otherName = new Name("Alice", "Middle", "Smith");
    assertNotEquals(name, otherName);
  }

  @Test
  void hashCodeIsConsistentWithEquals() {
    Name otherName = new Name("John", "Middle", "Doe");
    assertEquals(name.hashCode(), otherName.hashCode());
  }

  @Test
  void toStringProducesCorrectStringRepresentation() {
    String expectedString = "Name{" +
        "firstName='John', " +
        "middleName='Middle', " +
        "lastName='Doe'" +
        '}';
    assertEquals(expectedString, name.toString());
  }
}
