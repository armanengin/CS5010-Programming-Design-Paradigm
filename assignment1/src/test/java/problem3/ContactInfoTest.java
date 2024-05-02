package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContactInfoTest {
  private ContactInfo contactInfo;
  private Name name;
  private String address;
  private String phone;
  private String email;

  @BeforeEach
  void setUp() {
    name = new Name("John", "Doe");
    address = "123 Main St, Anytown, USA";
    phone = "123-456-7890";
    email = "john.doe@example.com";
    contactInfo = new ContactInfo(name, address, phone, email);
  }

  @Test
  void constructorShouldSetCorrectName() {
    assertEquals(name, contactInfo.getName());
  }

  @Test
  void constructorShouldSetCorrectAddress() {
    assertEquals(address, contactInfo.getAddress());
  }

  @Test
  void constructorShouldSetCorrectPhone() {
    assertEquals(phone, contactInfo.getPhone());
  }

  @Test
  void constructorShouldSetCorrectEmail() {
    assertEquals(email, contactInfo.getEmail());
  }

  @Test
  void setNameShouldUpdateName() {
    Name newName = new Name("Jane", "Smith");
    contactInfo.setName(newName);
    assertEquals(newName, contactInfo.getName());
  }

  @Test
  void setAddressShouldUpdateAddress() {
    String newAddress = "456 Elm St, Anothertown, USA";
    contactInfo.setAddress(newAddress);
    assertEquals(newAddress, contactInfo.getAddress());
  }

  @Test
  void setPhoneShouldUpdatePhone() {
    String newPhone = "098-765-4321";
    contactInfo.setPhone(newPhone);
    assertEquals(newPhone, contactInfo.getPhone());
  }

  @Test
  void setEmailShouldUpdateEmail() {
    String newEmail = "jane.smith@example.com";
    contactInfo.setEmail(newEmail);
    assertEquals(newEmail, contactInfo.getEmail());
  }

  @Test
  void equalsShouldBeReflexive() {
    assertTrue(contactInfo.equals(contactInfo));
  }

  @Test
  void equalsWithIdenticalContactInfo() {
    ContactInfo identicalContactInfo = new ContactInfo(name, address, phone, email);
    assertTrue(contactInfo.equals(identicalContactInfo));
  }

  @Test
  void equalsWithDifferentContactInfo() {
    ContactInfo differentContactInfo = new ContactInfo(name, "Different Address", phone, email);
    assertFalse(contactInfo.equals(differentContactInfo));
  }

  @Test
  void hashCodeConsistencyCheck() {
    int expectedHashCode = contactInfo.hashCode();
    assertEquals(expectedHashCode, contactInfo.hashCode());
  }

  @Test
  void toStringContainsName() {
    assertTrue(contactInfo.toString().contains(name.toString()));
  }

  @Test
  void toStringContainsAddress() {
    assertTrue(contactInfo.toString().contains(address));
  }

  @Test
  void toStringContainsPhone() {
    assertTrue(contactInfo.toString().contains(phone));
  }

  @Test
  void toStringContainsEmail() {
    assertTrue(contactInfo.toString().contains(email));
  }
}