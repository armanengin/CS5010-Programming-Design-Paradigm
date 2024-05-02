package problem2;

import java.util.Objects;

/**
 * Represents a physical address, with details such as street and number, city, zip code, state, and country.
 * This class provides methods to get and set these address components.
 *
 * @author Arman Engin Sucu
 */
public class Address {
  private String streetAndNumber;
  private String city;
  private String zipCode;
  private String state;
  private String country;

  /**
   * Constructs a new Address with the specified details.
   *
   * @param streetAndNumber The street and number of the address.
   * @param city            The city of the address.
   * @param zipCode         The postal code of the address.
   * @param state           The state of the address.
   * @param country         The country of the address.
   */
  public Address(String streetAndNumber, String city, String zipCode, String state,
      String country) {
    this.streetAndNumber = streetAndNumber;
    this.city = city;
    this.zipCode = zipCode;
    this.state = state;
    this.country = country;
  }

  /**
   * Gets the street and number of this address.
   *
   * @return The street and number.
   */
  public String getStreetAndNumber() {
    return streetAndNumber;
  }

  /**
   * Sets the street and number of this address.
   *
   * @param streetAndNumber The new street and number.
   */
  public void setStreetAndNumber(String streetAndNumber) {
    this.streetAndNumber = streetAndNumber;
  }

  /**
   * Gets the city of this address.
   *
   * @return The city.
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city of this address.
   *
   * @param city The new city.
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * Gets the zip code of this address.
   *
   * @return The zip code.
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Sets the zip code of this address.
   *
   * @param zipCode The new zip code.
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets the state of this address.
   *
   * @return The state.
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state of this address.
   *
   * @param state The new state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the country of this address.
   *
   * @return The country.
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the country of this address.
   *
   * @param country The new country.
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * Compares this address to the specified object for equality.
   * The result is true if and only if the argument is not null and is an Address object that
   * has the same street, city, zip code, state, and country as this object.
   *
   * @param o The object to compare this Address against.
   * @return true if the given object represents an Address equivalent to this address, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Address address)) {
      return false;
    }
    return Objects.equals(getStreetAndNumber(), address.getStreetAndNumber())
        && Objects.equals(getCity(), address.getCity())
        && Objects.equals(getZipCode(), address.getZipCode())
        && Objects.equals(getState(), address.getState())
        && Objects.equals(getCountry(), address.getCountry());
  }

  /**
   * Returns a hash code value for the address.
   * This method is supported for the benefit of hash tables such as those provided by HashMap.
   *
   * @return A hash code value for this address.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getStreetAndNumber(), getCity(), getZipCode(), getState(), getCountry());
  }

  /**
   * Returns a string representation of the Address object.
   * This string includes the street, city, zip code, state, and country.
   *
   * @return A string representation of this Address.
   */
  @Override
  public String toString() {
    return "Address{" +
        "streetAndNumber='" + streetAndNumber + '\'' +
        ", city='" + city + '\'' +
        ", zipCode='" + zipCode + '\'' +
        ", state='" + state + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}
