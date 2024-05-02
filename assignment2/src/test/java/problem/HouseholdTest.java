package problem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HouseholdTest {
  private PaperTowel paperTowel;

  @BeforeEach
  void setUp() {
    paperTowel = new PaperTowel("Acme Co.", "Super Absorbent Towel", 5.99, 0, 6);
  }

  @Test
  void testGetNumOfUnits() {
    assertEquals(6, paperTowel.getNumOfUnits());
  }

  @Test
  void testSetNumOfUnits() {
    paperTowel.setNumOfUnits(12);
    assertEquals(12, paperTowel.getNumOfUnits());
  }

  @Test
  void testEquals() {
    PaperTowel samePaperTowel = new PaperTowel("Acme Co.", "Super Absorbent Towel", 5.99, 0, 6);
    assertEquals(paperTowel, samePaperTowel);
  }

  @Test
  void testNotEquals() {
    PaperTowel differentPaperTowel = new PaperTowel("Acme Co.", "Standard Towel", 4.99, 0, 4);
    assertNotEquals(paperTowel, differentPaperTowel);
  }

  @Test
  void testHashCode() {
    PaperTowel samePaperTowel = new PaperTowel("Acme Co.", "Super Absorbent Towel", 5.99, 0, 6);
    assertEquals(paperTowel.hashCode(), samePaperTowel.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Household{manufacturer='Acme Co.'," +
         " productName='Super Absorbent Towel', price=5.99, minAge=0, numOfUnits=6}";
    assertEquals(expectedString, paperTowel.toString());
  }
}