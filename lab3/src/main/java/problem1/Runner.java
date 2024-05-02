package problem1;

import java.util.Objects;

/**
 * Represents a runner, extending the Athlete class. This class includes specific
 * attributes for a runner such as their best times in 5K and half marathon events,
 * and their favorite running event. Provides methods to get and set these properties.
 * <p>
 * Inherits common athlete attributes like name, height, weight, and league from the Athlete class.
 * </p>
 *
 * @author Arman Engin Sucu
 */
public class Runner extends Athlete {
  private Double best5Ktime;
  private Double bestHalfMarathonTime;
  private String favoriteRunningEvent;

  /**
   * Constructs a new Runner instance with specified athlete's name, height, weight, league,
   * best times for 5K and half marathon, and favorite running event.
   *
   * @param athletesName         Name of the athlete.
   * @param height               Height of the athlete in cm.
   * @param weight               Weight of the athlete in pounds.
   * @param league               League in which the athlete competes.
   * @param best5Ktime           Best 5K time of the athlete.
   * @param bestHalfMarathonTime Best half marathon time of the athlete.
   * @param favoriteRunningEvent Favorite running event of the athlete.
   */
  public Runner(Name athletesName, Double height, Double weight, String league, Double best5Ktime,
      Double bestHalfMarathonTime, String favoriteRunningEvent) {
    super(athletesName, height, weight, league);
    this.best5Ktime = best5Ktime;
    this.bestHalfMarathonTime = bestHalfMarathonTime;
    this.favoriteRunningEvent = favoriteRunningEvent;
  }

  /**
   * Constructs a new Runner instance without a league. Other parameters are
   * same as the constructor above.
   *
   * @param athletesName         Name of the athlete.
   * @param height               Height of the athlete in cm.
   * @param weight               Weight of the athlete in pounds.
   * @param best5Ktime           Best 5K time of the athlete.
   * @param bestHalfMarathonTime Best half marathon time of the athlete.
   * @param favoriteRunningEvent Favorite running event of the athlete.
   */
  public Runner(Name athletesName, Double height, Double weight, Double best5Ktime,
      Double bestHalfMarathonTime, String favoriteRunningEvent) {
    super(athletesName, height, weight);
    this.best5Ktime = best5Ktime;
    this.bestHalfMarathonTime = bestHalfMarathonTime;
    this.favoriteRunningEvent = favoriteRunningEvent;
  }

  /**
   * Gets the best 5K time of the runner.
   *
   * @return The best 5K time.
   */
  public Double getBest5Ktime() {
    return best5Ktime;
  }

  /**
   * Sets the best 5K time of the runner.
   *
   * @param best5Ktime The best 5K time to set.
   */
  public void setBest5Ktime(Double best5Ktime) {
    this.best5Ktime = best5Ktime;
  }

  /**
   * Gets the best half marathon time of the runner.
   *
   * @return The best half marathon time.
   */
  public Double getBestHalfMarathonTime() {
    return bestHalfMarathonTime;
  }

  /**
   * Sets the best half marathon time of the runner.
   *
   * @param bestHalfMarathonTime The best half marathon time to set.
   */
  public void setBestHalfMarathonTime(Double bestHalfMarathonTime) {
    this.bestHalfMarathonTime = bestHalfMarathonTime;
  }

  /**
   * Gets the favorite running event of the runner.
   *
   * @return The favorite running event.
   */
  public String getFavoriteRunningEvent() {
    return favoriteRunningEvent;
  }

  /**
   * Sets the favorite running event of the runner.
   *
   * @param favoriteRunningEvent The favorite running event to set.
   */
  public void setFavoriteRunningEvent(String favoriteRunningEvent) {
    this.favoriteRunningEvent = favoriteRunningEvent;
  }

/**
 * Compares this runner to the specified object.
 * The result is true if and only if the argument is not null and is a Runner object that
 * has the same best times for 5K and half marathon, and the same favorite running event as this object.
 *
 * @param o The object to compare this Runner against.
 * @return true if the given object represents a Runner equivalent to this runner, false otherwise.
 */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Runner runner)) {
      return false;
    }
    if (!super.equals(o)){
      return false;
    }
    return Objects.equals(getBest5Ktime(), runner.getBest5Ktime())
        && Objects.equals(getBestHalfMarathonTime(), runner.getBestHalfMarathonTime())
        && Objects.equals(getFavoriteRunningEvent(), runner.getFavoriteRunningEvent());
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getBest5Ktime(), getBestHalfMarathonTime(), getFavoriteRunningEvent());
  }

  /**
   * Returns a string representation of the Runner object.
   * This string includes the best 5K time, best half marathon time, and favorite running event.
   *
   * @return A string representation of this Runner.
   */
  @Override
  public String toString() {
    return "Runner{" +
        "best5Ktime=" + best5Ktime +
        ", bestHalfMarathonTime=" + bestHalfMarathonTime +
        ", favoriteRunningEvent='" + favoriteRunningEvent + '\'' +
        '}';
  }
}


