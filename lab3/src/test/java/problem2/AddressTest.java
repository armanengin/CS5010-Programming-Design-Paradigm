package problem2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressTest {

  private Address address;

  @BeforeEach
  void setUp() {
    address = new Address("123 Main St", "Springfield", "12345", "State", "Country");
  }

  @Test
  void getStreetAndNumber() {
    assertEquals("123 Main St", address.getStreetAndNumber());
  }

  @Test
  void setStreetAndNumber() {
    address.setStreetAndNumber("456 Elm St");
    assertEquals("456 Elm St", address.getStreetAndNumber());
  }

  @Test
  void getCity() {
    assertEquals("Springfield", address.getCity());
  }

  @Test
  void setCity() {
    address.setCity("Shelbyville");
    assertEquals("Shelbyville", address.getCity());
  }

  @Test
  void getZipCode() {
    assertEquals("12345", address.getZipCode());
  }

  @Test
  void setZipCode() {
    address.setZipCode("67890");
    assertEquals("67890", address.getZipCode());
  }

  @Test
  void getState() {
    assertEquals("State", address.getState());
  }

  @Test
  void setState() {
    address.setState("New State");
    assertEquals("New State", address.getState());
  }

  @Test
  void getCountry() {
    assertEquals("Country", address.getCountry());
  }

  @Test
  void setCountry() {
    address.setCountry("New Country");
    assertEquals("New Country", address.getCountry());
  }

  @Test
  void testEqualsReflexive() {
    assertEquals(address, address, "An address should be equal to itself.");
  }

  @Test
  void testEqualsNull() {
    assertNotEquals(address, null, "An address should not be equal to null.");
  }

  @Test
  void testEqualsDifferentClass() {
    Object otherObject = new Object();
    assertNotEquals(address, otherObject, "An address should not be equal to an object of a different type.");
  }

  @Test
  void testEqualsDifferentStreetAndNumber() {
    Address differentAddress = new Address("456 Elm St", "Springfield", "12345", "State", "Country");
    assertNotEquals(address, differentAddress, "Addresses with different street and number should not be equal.");
  }

  @Test
  void testEqualsDifferentCity() {
    Address differentAddress = new Address("123 Main St", "Shelbyville", "12345", "State", "Country");
    assertNotEquals(address, differentAddress, "Addresses with different cities should not be equal.");
  }

  @Test
  void testEqualsDifferentZipCode() {
    Address differentAddress = new Address("123 Main St", "Springfield", "67890", "State", "Country");
    assertNotEquals(address, differentAddress, "Addresses with different zip codes should not be equal.");
  }

  @Test
  void testEqualsDifferentState() {
    Address differentAddress = new Address("123 Main St", "Springfield", "12345", "New State", "Country");
    assertNotEquals(address, differentAddress, "Addresses with different states should not be equal.");
  }

  @Test
  void testEqualsDifferentCountry() {
    Address differentAddress = new Address("123 Main St", "Springfield", "12345", "State", "New Country");
    assertNotEquals(address, differentAddress, "Addresses with different countries should not be equal.");
  }

  @Test
  void testHashCode() {
    Address sameAddress = new Address("123 Main St", "Springfield", "12345", "State", "Country");
    assertEquals(address.hashCode(), sameAddress.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Address{streetAndNumber='123 Main St', city='Springfield', zipCode='12345', state='State', country='Country'}";
    assertEquals(expectedString, address.toString());
  }

}