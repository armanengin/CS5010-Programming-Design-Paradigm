package problem1;

import java.util.Objects;

/**
 * Represents a baseball player, extending the Athlete class. This class includes specific
 * attributes for a baseball player such as team, average batting score, and season home runs.
 * Provides methods to get and set these properties.
 * <p>
 * Inherits common athlete attributes like name, height, weight, and league from the Athlete class.
 * </p>
 *
 * @author Arman Engin Sucu
 */
public class BaseballPlayer extends Athlete {
  private String team;
  private Double avgBatting;
  private int seasonHomeRun;

  /**
   * Constructs a new BaseballPlayer instance with specified athlete's name, height, weight, league,
   * and specific baseball attributes like team, average batting score, and season home runs.
   *
   * @param athletesName     Name of the athlete.
   * @param height           Height of the athlete in cm.
   * @param weight           Weight of the athlete in pounds.
   * @param league           League in which the athlete plays.
   * @param team             The baseball team of the player.
   * @param avgBatting       The average batting score of the player.
   * @param seasonHomeRun    The number of home runs in the season.
   */
  public BaseballPlayer(Name athletesName, Double height, Double weight, String league, String team,
      Double avgBatting, int seasonHomeRun) {
    super(athletesName, height, weight, league);
    this.team = team;
    this.avgBatting = avgBatting;
    this.seasonHomeRun = seasonHomeRun;
  }

  /**
   * Constructs a new BaseballPlayer instance without a league. Other parameters are
   * same as the constructor above.
   *
   * @param athletesName     Name of the athlete.
   * @param height           Height of the athlete in cm.
   * @param weight           Weight of the athlete in pounds.
   * @param team             The baseball team of the player.
   * @param avgBatting       The average batting score of the player.
   * @param seasonHomeRun    The number of home runs in the season.
   */
  public BaseballPlayer(Name athletesName, Double height, Double weight, String team,
      Double avgBatting, int seasonHomeRun) {
    super(athletesName, height, weight);
    this.team = team;
    this.avgBatting = avgBatting;
    this.seasonHomeRun = seasonHomeRun;
  }

  /**
   * Gets the team name of the baseball player.
   *
   * @return The team name.
   */
  public String getTeam() {
    return team;
  }

  /**
   * Sets the team name of the baseball player.
   *
   * @param team The team name to set.
   */
  public void setTeam(String team) {
    this.team = team;
  }

  /**
   * Gets the average batting score of the baseball player.
   *
   * @return The average batting score.
   */
  public Double getAvgBatting() {
    return avgBatting;
  }

  /**
   * Sets the average batting score of the baseball player.
   *
   * @param avgBatting The average batting score to set.
   */
  public void setAvgBatting(Double avgBatting) {
    this.avgBatting = avgBatting;
  }

  /**
   * Gets the season home runs of the baseball player.
   *
   * @return The number of season home runs.
   */
  public int getSeasonHomeRun() {
    return seasonHomeRun;
  }

  /**
   * Sets the season home runs for the baseball player.
   *
   * @param seasonHomeRun The number of season home runs to set.
   */
  public void setSeasonHomeRun(int seasonHomeRun) {
    this.seasonHomeRun = seasonHomeRun;
  }

  /**
   * Compares this baseball player to the specified object.
   * The result is true if and only if the argument is not null and is a BaseballPlayer object that
   * has the same team, average batting, and season home runs as this object.
   *
   * @param o The object to compare this BaseballPlayer against.
   * @return true if the given object represents a BaseballPlayer equivalent to this player, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseballPlayer that)) {
      return false;
    }
    return getSeasonHomeRun() == that.getSeasonHomeRun() && Objects.equals(getTeam(),
        that.getTeam()) && Objects.equals(getAvgBatting(), that.getAvgBatting());
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getTeam(), getAvgBatting(), getSeasonHomeRun());
  }

  /**
   * Returns a string representation of the BaseballPlayer object.
   * This string includes the team name, average batting score, and season home runs.
   *
   * @return A string representation of this BaseballPlayer.
   */
  @Override
  public String toString() {
    return "BaseballPlayer{" +
        "team='" + team + '\'' +
        ", avgBatting=" + avgBatting +
        ", seasonHomeRun=" + seasonHomeRun +
        '}';
  }
}
