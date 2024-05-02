package problem;

import java.util.Objects;

/**
 * Represents a name, typically used for individuals, consisting of first, middle, and last names.
 * This class provides methods to get and set individual parts of the name.
 *
 * @author Arman Engin Sucu
 */
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;

  /**
   * Constructs a new Name object with the given first name, middle name, and last name.
   *
   * @param firstName  The first name.
   * @param middleName The middle name.
   * @param lastName   The last name.
   */
  public Name(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * Gets the first name.
   *
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName The new first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the middle name.
   *
   * @return The middle name.
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Sets the middle name.
   *
   * @param middleName The new middle name.
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * Gets the last name.
   *
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName The new last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * The Name objects are considered equal if they have the same first name, middle name, and last name.
   *
   * @param o The reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Name)) return false;
    Name name = (Name) o;
    return Objects.equals(firstName, name.firstName) &&
        Objects.equals(middleName, name.middleName) &&
        Objects.equals(lastName, name.lastName);
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   * The hash code is generated based on the first name, middle name, and last name.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
  }

  /**
   * Returns a string representation of the Name object.
   * This typically includes the first, middle, and last names.
   *
   * @return A string representation of the name.
   */
  @Override
  public String toString() {
    return "Name{" +
        "firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}

