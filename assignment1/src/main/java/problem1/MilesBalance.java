package problem1;

/**
 * Represents the balance of miles in a frequent flyer account.
 * This class includes properties such as total miles, miles earned yearly,
 * and miles expiring yearly.
 * <p>
 * It provides methods to access and modify these properties, along with
 * overridden methods for object equality, hashcode, and string representation.
 * </p>
 *
 * @author Arman Engin Sucu
 */
public class MilesBalance {
  private int totalMiles;
  private int milesEarnedYearly;
  private int milesExpiringYearly;

  /**
   * Retrieves the total miles in the balance.
   *
   * @return the total miles.
   */
  public int getTotalMiles() {
    return totalMiles;
  }

  /**
   * Sets the total miles in the balance.
   *
   * @param totalMiles the total miles to set.
   */
  public void setTotalMiles(int totalMiles) {
    this.totalMiles = totalMiles;
  }

  /**
   * Retrieves the miles earned yearly.
   *
   * @return the miles earned in the current year.
   */
  public int getMilesEarnedYearly() {
    return milesEarnedYearly;
  }

  /**
   * Sets the miles earned yearly.
   *
   * @param milesEarnedYearly the miles earned in the current year.
   */
  public void setMilesEarnedYearly(int milesEarnedYearly) {
    this.milesEarnedYearly = milesEarnedYearly;
  }

  /**
   * Retrieves the miles expiring yearly.
   *
   * @return the miles that will expire by the end of the current year.
   */
  public int getMilesExpiringYearly() {
    return milesExpiringYearly;
  }

  /**
   * Sets the miles expiring yearly.
   *
   * @param milesExpiringYearly the miles that will expire by the end of the current year.
   */
  public void setMilesExpiringYearly(int milesExpiringYearly) {
    this.milesExpiringYearly = milesExpiringYearly;
  }

  /**
   * Compares this MilesBalance with the specified object for equality.
   * Two MilesBalance objects are considered equal if they have the same total miles,
   * miles earned yearly, and miles expiring yearly.
   *
   * @param obj the reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    MilesBalance other = (MilesBalance) obj;
    return totalMiles == other.totalMiles &&
        milesEarnedYearly == other.milesEarnedYearly &&
        milesExpiringYearly == other.milesExpiringYearly;
  }

  /**
   * Returns a hash code value for the object.
   * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + totalMiles;
    result = prime * result + milesEarnedYearly;
    result = prime * result + milesExpiringYearly;
    return result;
  }

  /**
   * Returns a string representation of the MilesBalance object.
   * The string includes the total miles, miles earned yearly, and miles expiring yearly.
   *
   * @return a string representation of the MilesBalance object.
   */
  @Override
  public String toString() {
    return "MilesBalance{" +
        "totalMiles=" + totalMiles +
        ", milesEarnedYearly=" + milesEarnedYearly +
        ", milesExpiringYearly=" + milesExpiringYearly +
        '}';
  }
}
