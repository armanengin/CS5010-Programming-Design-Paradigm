package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrequentFlyerSystemTest {
  private FrequentFlyer flyer;
  private String id;
  private Name name;
  private String email;

  @BeforeEach
  void setUp() {
    id = "123456789asd";
    name = new Name("John", "Middle", "Doe");
    email = "john.doe@example.com";
    flyer = new FrequentFlyer(id, name, email);
  }

  @AfterEach
  void tearDown() {
    FrequentFlyerSystem.clearSystem(); // Clear the frequentFlyers HashMap here
  }

  @Test
  void addFrequentFlyerSuccessfully() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    assertTrue(FrequentFlyerSystem.isFlyerExist(id));
  }

  @Test
  void getFrequentFlyerReturnsCorrectFlyer() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    assertEquals(flyer, FrequentFlyerSystem.getFrequentFlyer(id));
  }

  @Test
  void addFrequentFlyerThrowsExceptionForDuplicateId() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    assertThrows(IllegalArgumentException.class, () -> FrequentFlyerSystem.addFrequentFlyer(flyer));
  }

  @Test
  void removeFrequentFlyerSuccessfully() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    FrequentFlyerSystem.removeFrequentFlyer(id);
    assertFalse(FrequentFlyerSystem.isFlyerExist(id));
  }

  @Test
  void removeFrequentFlyerThrowsExceptionForNonExistentId() {
    assertThrows(IllegalArgumentException.class, () -> FrequentFlyerSystem.removeFrequentFlyer("nonexistentid"));
  }

  @Test
  void isFlyerExistReturnsFalseForNonExistentFlyer() {
    assertFalse(FrequentFlyerSystem.isFlyerExist(id));
  }

  @Test
  void isFlyerExistReturnsTrueForExistingFlyer() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    assertTrue(FrequentFlyerSystem.isFlyerExist(id));
  }

  @Test
  void idNameCheckReturnsTrueForMatchingIdAndName() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    assertTrue(FrequentFlyerSystem.idNameCheck(id, name));
  }

  @Test
  void idNameCheckReturnsFalseForNonMatchingName() {
    Name differentName = new Name("Jane", "Middle", "Doe");
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    assertFalse(FrequentFlyerSystem.idNameCheck(id, differentName));
  }

  @Test
  void getFrequentFlyerThrowsExceptionForNonExistentId() {
    assertThrows(IllegalArgumentException.class, () -> FrequentFlyerSystem.getFrequentFlyer("nonexistentid"));
  }

  @Test
  void clearSystemRemovesAllFlyersFromSystem() {
    FrequentFlyerSystem.addFrequentFlyer(flyer);
    FrequentFlyerSystem.clearSystem();
    assertFalse(FrequentFlyerSystem.isFlyerExist(id));
  }
}