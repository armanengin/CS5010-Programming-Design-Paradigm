package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AthleteTest {

  private Athlete athlete;
  private Name athleteName;
  @BeforeEach
  public void setUp() {
    athleteName = new Name("John", "Middle", "Doe");
    athlete = new Athlete(athleteName, 180.0, 75.0, "Marathon League");
  }

  @Test
  void getAthletesName() {
    assertEquals(athleteName, athlete.getAthletesName());
  }

  @Test
  void getHeight() {
    assertEquals(Double.valueOf(180.0), athlete.getHeight());
  }

  @Test
  void getWeight() {
    assertEquals(Double.valueOf(75.0), athlete.getWeight());
  }

  @Test
  void getLeague() {
    assertEquals("Marathon League", athlete.getLeague());
  }

  @Test
  void testEqualsSelf() {
    assertEquals(athlete, athlete, "An athlete should be equal to itself.");
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(null, athlete, "An athlete should not be equal to null.");
  }

  @Test
  void testEqualsDifferentClass() {
    Object otherObject = new Object();
    assertNotEquals(athlete, otherObject, "An athlete should not be equal to an object of a different type.");
  }

  @Test
  void testEqualsDifferentName() {
    Name differentName = new Name("Jane", "Middle", "Doe");
    Athlete differentAthlete = new Athlete(differentName, 180.0, 75.0, "Marathon League");
    assertNotEquals(athlete, differentAthlete, "An athlete should not be equal to another athlete with a different name.");
  }

  @Test
  void testEqualsDifferentHeight() {
    Athlete differentAthlete = new Athlete(athleteName, 190.0, 75.0, "Marathon League");
    assertNotEquals(athlete, differentAthlete, "An athlete should not be equal to another athlete with a different height.");
  }

  @Test
  void testEqualsDifferentWeight() {
    Athlete differentAthlete = new Athlete(athleteName, 180.0, 80.0, "Marathon League");
    assertNotEquals(athlete, differentAthlete, "An athlete should not be equal to another athlete with a different weight.");
  }

  @Test
  void testEqualsDifferentLeague() {
    Athlete differentAthlete = new Athlete(athleteName, 180.0, 75.0, "5K League");
    assertNotEquals(athlete, differentAthlete, "An athlete should not be equal to another athlete with a different league.");
  }

  @Test
  void testHashCodeConsistency() {
    int expectedHashCode = athlete.hashCode();
    int actualHashCode = new Athlete(athleteName, 180.0, 75.0, "Marathon League").hashCode();
    assertEquals(expectedHashCode, actualHashCode, "Hash codes should be the same for equal objects.");
  }

  @Test
  void testHashCodeConsistencyWithDifferentValues() {
    int athleteHashCode = athlete.hashCode();
    int differentAthleteHashCode = new Athlete(athleteName, 190.0, 80.0, "5K League").hashCode();
    assertNotEquals(athleteHashCode, differentAthleteHashCode, "Hash codes should not be the same for non-equal objects.");
  }
}