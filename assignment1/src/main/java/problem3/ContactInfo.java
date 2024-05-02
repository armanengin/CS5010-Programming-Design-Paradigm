package problem3;

import java.util.Objects;

/**
 * Represents contact information for a tax filer. This class includes details such as the person's name,
 * address, phone number, and email address.
 * It is assumed that another class named {@code Name} exists and is used to represent the name of the person.
 *
 * @author Arman Engin Sucu
 */
public class ContactInfo {
  private Name name;
  private String address;
  private String phone;
  private String email;

  /**
   * Constructs a new ContactInfo instance with specified name, address, phone number, and email.
   *
   * @param name the name of the person as a {@code Name} object
   * @param address the address of the person as a String
   * @param phone the phone number of the person as a String
   * @param email the email address of the person as a String
   */
  public ContactInfo(Name name, String address, String phone, String email) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  /**
   * Retrieves the name of the person.
   *
   * @return the name of the person as a {@code Name} object
   */
  public Name getName() {
    return name;
  }

  /**
   * Sets the name of the person to a new {@code Name} object.
   *
   * @param name the new name to set
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * Retrieves the address of the person.
   *
   * @return the address of the person as a String
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the address of the person to a new value.
   *
   * @param address the new address to set
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Retrieves the phone number of the person.
   *
   * @return the phone number of the person as a String
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the phone number of the person to a new value.
   *
   * @param phone the new phone number to set
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Retrieves the email address of the person.
   *
   * @return the email address of the person as a String
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the person to a new value.
   *
   * @param email the new email address to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Compares this ContactInfo object to another object for equality. The comparison is based on the
   * name, address, phone number, and email address.
   *
   * @param o the object to be compared with this ContactInfo
   * @return true if the specified object is equal to this ContactInfo; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ContactInfo)) return false;
    ContactInfo that = (ContactInfo) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(address, that.address) &&
        Objects.equals(phone, that.phone) &&
        Objects.equals(email, that.email);
  }

  /**
   * Returns a hash code value for this ContactInfo object.
   *
   * @return a hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, address, phone, email);
  }

  /**
   * Returns a string representation of this ContactInfo object. The string representation
   * includes the name, address, phone number, and email address.
   *
   * @return a string representation of this object
   */
  @Override
  public String toString() {
    return "ContactInfo{" +
        "name=" + name +
        ", address='" + address + '\'' +
        ", phone='" + phone + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}