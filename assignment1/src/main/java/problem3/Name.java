package problem3;

import java.util.Objects;

/**
 * Represents a person's name with separate fields for the first name and last name.
 * This class is designed to encapsulate a person's full name in a structured manner,
 * providing methods to access the individual components (first name and last name) of the full name.
 *
 * @author Arman Engin Sucu
 */
public class Name {
  private String firstName;
  private String lastName;

  /**
   * Constructs a new Name instance with a given first name and last name.
   *
   * @param firstName the first name of the person
   * @param lastName the last name of the person
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Retrieves the first name of the person.
   *
   * @return the first name as a String
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Retrieves the last name of the person.
   *
   * @return the last name as a String
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Compares this Name object to another object for equality. The comparison is based on
   * the first name and last name.
   *
   * @param o the object to be compared with this Name
   * @return true if the specified object is equal to this Name; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Name)) return false;
    Name name = (Name) o;
    return Objects.equals(getFirstName(), name.getFirstName()) &&
        Objects.equals(getLastName(), name.getLastName());
  }

  /**
   * Returns a hash code value for this Name object.
   *
   * @return a hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName());
  }

  /**
   * Returns a string representation of this Name object. The string representation
   * includes the first name and last name.
   *
   * @return a string representation of this object
   */
  @Override
  public String toString() {
    return "Name{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
