package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BaseballPlayerTest {

  private BaseballPlayer player;
  private Name athleteName;

  @BeforeEach
  void setUp() {
    athleteName = new Name("John", "Middle", "Doe");
    player = new BaseballPlayer(athleteName, 180.0, 75.0, "MLB", "Yankees", 0.312, 33);
  }

  @Test
  void getAthletesName() {
    assertEquals(athleteName, player.getAthletesName());
  }

  @Test
  void getHeight() {
    assertEquals(Double.valueOf(180.0), player.getHeight());
  }

  @Test
  void getWeight() {
    assertEquals(Double.valueOf(75.0), player.getWeight());
  }

  @Test
  void getLeague() {
    assertEquals("MLB", player.getLeague());
  }

  @Test
  void getTeam() {
    assertEquals("Yankees", player.getTeam());
  }

  @Test
  void setTeam() {
    player.setTeam("Red Sox");
    assertEquals("Red Sox", player.getTeam());
  }

  @Test
  void getAvgBatting() {
    assertEquals(Double.valueOf(0.312), player.getAvgBatting());
  }

  @Test
  void setAvgBatting() {
    player.setAvgBatting(0.325);
    assertEquals(Double.valueOf(0.325), player.getAvgBatting());
  }

  @Test
  void getSeasonHomeRun() {
    assertEquals(33, player.getSeasonHomeRun());
  }

  @Test
  void setSeasonHomeRun() {
    player.setSeasonHomeRun(40);
    assertEquals(40, player.getSeasonHomeRun());
  }

  @Test
  void testEquals() {
    BaseballPlayer anotherPlayer = new BaseballPlayer(athleteName, 180.0, 75.0, "MLB", "Yankees", 0.312, 33);
    assertEquals(player, anotherPlayer);
  }

  @Test
  void testNotEquals() {
    BaseballPlayer differentPlayer = new BaseballPlayer(athleteName, 180.0, 75.0, "MLB", "Red Sox", 0.312, 33);
    assertNotEquals(player, differentPlayer);
  }

  @Test
  void testHashCode() {
    BaseballPlayer anotherPlayer = new BaseballPlayer(athleteName, 180.0, 75.0, "MLB", "Yankees", 0.312, 33);
    assertEquals(player.hashCode(), anotherPlayer.hashCode());
  }

  @Test
  void testToString() {
    String expected = "BaseballPlayer{team='Yankees', avgBatting=0.312, seasonHomeRun=33}";
    assertEquals(expected, player.toString());
  }
}
