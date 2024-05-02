package problem1;

import java.util.Objects;

/**
 * Represents a person's name, including first name, middle name, and last name.
 * Provides methods to get and set these name components.
 *
 * @author Arman Engin Sucu
 */
public class Name {
  private String firstName;
  private String middleName;
  private String lastName;

  /**
   * Constructs a new Name instance with specified first name, middle name, and last name.
   *
   * @param firstName The first name of the person.
   * @param middleName The middle name of the person.
   * @param lastName The last name of the person.
   */
  public Name(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  /**
   * Gets the first name of the person.
   *
   * @return The first name.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the person.
   *
   * @param firstName The first name to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the middle name of the person.
   *
   * @return The middle name.
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Sets the middle name of the person.
   *
   * @param middleName The middle name to set.
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * Gets the last name of the person.
   *
   * @return The last name.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the person.
   *
   * @param lastName The last name to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Compares this Name object with another object for equality.
   * The result is true if and only if the argument is not null and is a Name object that
   * has the same first name, middle name, and last name as this object.
   *
   * @param o The object to compare this Name against.
   * @return true if the given object represents a Name equivalent to this name, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Name name)) {
      return false;
    }
    return Objects.equals(firstName, name.firstName) && Objects.equals(middleName,
        name.middleName) && Objects.equals(lastName, name.lastName);
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit of hash tables.
   *
   * @return A hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, middleName, lastName);
  }

  /**
   * Returns a string representation of the Name object.
   * This string includes the first name, middle name, and last name.
   *
   * @return A string representation of this Name.
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

