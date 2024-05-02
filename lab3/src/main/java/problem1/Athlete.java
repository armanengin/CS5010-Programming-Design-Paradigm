package problem1;

import java.util.Objects;

/**
 * Class Athlete contains information about an athlete, including athlete's name, their height, weight and league.
 *
 * @author Arman Engin Sucu
 */
public class Athlete {

  private Name athletesName;
  private Double height;
  private Double weight;
  private String league;

  /**
   * Constructs a new athlete, based upon all the provided input parameters.
   * @param athletesName - object Name, containing athlete's first, middle and last name
   * @param height - athlete's height, expressed as a Double in cm (e.g., 6'2'' is recorded as 187.96cm)
   * @param weight - athlete's weigh, expressed as a Double in pounds (e.g. 125, 155, 200 pounds)
   * @param league - athelete's league, expressed as String
   */
  public Athlete(Name athletesName, Double height, Double weight, String league) {
    this.athletesName = athletesName;
    this.height = height;
    this.weight = weight;
    this.league = league;
  }

  /**
   * Constructs a new athlete, based upon all of the provided input parameters.
   * @param athletesName - object Name, containing athlete's first, middle and last name
   * @param height - athlete's height, expressed as a Double in cm (e.g., 6'2'' is recorded as 187.96cm)
   * @param weight - athlete's weigh, expressed as a Double in pounds (e.g. 125, 155, 200 pounds)
   */

  public Athlete(Name athletesName, Double height, Double weight) {
    this.athletesName = athletesName;
    this.height = height;
    this.weight = weight;
    this.league = null;
  }

  /**
   * Returns athlete's name as an object Name
   *
   * @return Name athlete's name that includes first, middle and last name
   */
  public Name getAthletesName() {
    return athletesName;
  }

  /**
   * Returns athlete's height as a Double
   *
   * @return Double height
   */
  public Double getHeight() {
    return height;
  }

  /**
   * Returns athlete's weight as a Double
   *
   * @return Double weight
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * Returns athlete's league as a String
   *
   * @return String league
   */
  public String getLeague() {
    return league;
  }

  /**
   * Compares this athlete to the specified object for equality.
   * The result is true if and only if the argument is not null and is an Athlete object that
   * has the same values for name, height, weight, and league as this object.
   *
   * @param o The object to compare this Athlete against.
   * @return true if the given object represents an Athlete equivalent to this athlete, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Athlete athlete)) {
      return false;
    }
    return Objects.equals(getAthletesName(), athlete.getAthletesName())
        && Objects.equals(getHeight(), athlete.getHeight()) && Objects.equals(
        getWeight(), athlete.getWeight()) && Objects.equals(getLeague(),
        athlete.getLeague());
  }

  /**
   * Returns a hash code value for the athlete.
   * This method is supported for the benefit of hash tables such as those provided by HashMap.
   *
   * @return A hash code value for this athlete.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getAthletesName(), getHeight(), getWeight(), getLeague());
  }
}