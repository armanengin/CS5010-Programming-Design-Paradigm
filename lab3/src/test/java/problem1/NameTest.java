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
  void getFirstName() {
    assertEquals("John", name.getFirstName());
  }

  @Test
  void setFirstName() {
    name.setFirstName("Jane");
    assertEquals("Jane", name.getFirstName());
  }

  @Test
  void getMiddleName() {
    assertEquals("Middle", name.getMiddleName());
  }

  @Test
  void setMiddleName() {
    name.setMiddleName("M");
    assertEquals("M", name.getMiddleName());
  }

  @Test
  void getLastName() {
    assertEquals("Doe", name.getLastName());
  }

  @Test
  void setLastName() {
    name.setLastName("Smith");
    assertEquals("Smith", name.getLastName());
  }

  @Test
  void testEquals() {
    Name sameName = new Name("John", "Middle", "Doe");
    assertEquals(name, sameName);
  }

  @Test
  void testNotEquals() {
    Name differentName = new Name("Jane", "Middle", "Doe");
    assertNotEquals(name, differentName);
  }

  @Test
  void testHashCode() {
    Name sameName = new Name("John", "Middle", "Doe");
    assertEquals(name.hashCode(), sameName.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Name{firstName='John', middleName='Middle', lastName='Doe'}";
    assertEquals(expectedString, name.toString());
  }
}
