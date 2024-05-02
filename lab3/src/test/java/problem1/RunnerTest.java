package problem1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunnerTest {

  private Runner runner;
  private Name athleteName;

  @BeforeEach
  void setUp() {
    athleteName = new Name("John", "Middle", "Doe");
    runner = new Runner(athleteName, 180.0, 75.0, "Marathon League", 15.5, 120.0, "Marathon");
  }

  @Test
  void getAthletesName() {
    assertEquals(athleteName, runner.getAthletesName());
  }

  @Test
  void getHeight() {
    assertEquals(Double.valueOf(180.0), runner.getHeight());
  }

  @Test
  void getWeight() {
    assertEquals(Double.valueOf(75.0), runner.getWeight());
  }

  @Test
  void getLeague() {
    assertEquals("Marathon League", runner.getLeague());
  }

  @Test
  void getBest5Ktime() {
    assertEquals(Double.valueOf(15.5), runner.getBest5Ktime());
  }

  @Test
  void setBest5Ktime() {
    runner.setBest5Ktime(14.0);
    assertEquals(Double.valueOf(14.0), runner.getBest5Ktime());
  }

  @Test
  void getBestHalfMarathonTime() {
    assertEquals(Double.valueOf(120.0), runner.getBestHalfMarathonTime());
  }

  @Test
  void setBestHalfMarathonTime() {
    runner.setBestHalfMarathonTime(115.0);
    assertEquals(Double.valueOf(115.0), runner.getBestHalfMarathonTime());
  }

  @Test
  void getFavoriteRunningEvent() {
    assertEquals("Marathon", runner.getFavoriteRunningEvent());
  }

  @Test
  void setFavoriteRunningEvent() {
    runner.setFavoriteRunningEvent("5K Race");
    assertEquals("5K Race", runner.getFavoriteRunningEvent());
  }

  @Test
  void testEqualsSameObject() {
    assertEquals(runner, runner);
  }

  @Test
  void testEqualsDifferentType() {
    Object otherObject = new Object();
    assertNotEquals(runner, otherObject);
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(runner, null);
  }

  @Test
  void testEqualsDifferentName() {
    Name differentName = new Name("Jane", "Middle", "Doe");
    Runner differentRunner = new Runner(differentName, 180.0, 75.0, "Marathon League", 15.5, 120.0, "Marathon");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testEqualsDifferentHeight() {
    Runner differentRunner = new Runner(athleteName, 190.0, 75.0, "Marathon League", 15.5, 120.0, "Marathon");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testEqualsDifferentWeight() {
    Runner differentRunner = new Runner(athleteName, 180.0, 80.0, "Marathon League", 15.5, 120.0, "Marathon");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testEqualsDifferentLeague() {
    Runner differentRunner = new Runner(athleteName, 180.0, 75.0, "5K League", 15.5, 120.0, "Marathon");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testEqualsDifferentBest5KTime() {
    Runner differentRunner = new Runner(athleteName, 180.0, 75.0, "Marathon League", 16.0, 120.0, "Marathon");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testEqualsDifferentBestHalfMarathonTime() {
    Runner differentRunner = new Runner(athleteName, 180.0, 75.0, "Marathon League", 15.5, 125.0, "Marathon");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testEqualsDifferentFavoriteRunningEvent() {
    Runner differentRunner = new Runner(athleteName, 180.0, 75.0, "Marathon League", 15.5, 120.0, "5K");
    assertNotEquals(runner, differentRunner);
  }

  @Test
  void testHashCode() {
    Runner anotherRunner = new Runner(athleteName, 180.0, 75.0, "Marathon League", 15.5, 120.0, "Marathon");
    assertEquals(runner.hashCode(), anotherRunner.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Runner{best5Ktime=15.5, bestHalfMarathonTime=120.0, favoriteRunningEvent='Marathon'}";
    assertEquals(expectedString, runner.toString());
  }
}
