package problem1;

import java.util.Objects;

/**
 * Represents the name of a person, specifically a frequent flyer in this context.
 * It includes the first name, middle name, and last name of the person.
 * <p>
 * The class provides methods to access and modify these name components, as well as
 * overridden methods for equality, hash code generation, and string representation.
 * </p>
 *
 * @author Arman Engin Sucu
 */
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;

  /**
   * Constructs a new Name instance with specified first, middle, and last names.
   *
   * @param firstName  The first name of the person.
   * @param middleName The middle name of the person.
   * @param lastName   The last name of the person.
   */
  public Name(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * Returns the first name of the person.
   *
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the person.
   *
   * @param firstName The new first name.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Returns the middle name of the person.
   *
   * @return The middle name.
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Sets the middle name of the person.
   *
   * @param middleName The new middle name.
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * Returns the last name of the person.
   *
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the person.
   *
   * @param lastName The new last name.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Compares this Name with the specified object for equality.
   * Two Name objects are considered equal if they have the same first, middle, and last names.
   *
   * @param o The reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Name name)) {
      return false;
    }
    return Objects.equals(getFirstName(), name.getFirstName()) && Objects.equals(
        getMiddleName(), name.getMiddleName()) && Objects.equals(getLastName(),
        name.getLastName());
  }

  /**
   * Returns a hash code value for the object.
   * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getMiddleName(), getLastName());
  }

  /**
   * Returns a string representation of the Name object.
   * The string includes the first, middle, and last names.
   *
   * @return a string representation of the Name object.
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

