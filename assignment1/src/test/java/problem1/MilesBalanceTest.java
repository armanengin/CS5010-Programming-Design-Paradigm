package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MilesBalanceTest {
  private MilesBalance milesBalance;

  @BeforeEach
  void setUp() {
    milesBalance = new MilesBalance();
  }

  @Test
  void testGetTotalMilesInitialValue() {
    assertEquals(0, milesBalance.getTotalMiles());
  }

  @Test
  void testSetAndGetTotalMiles() {
    int totalMiles = 10000;
    milesBalance.setTotalMiles(totalMiles);
    assertEquals(totalMiles, milesBalance.getTotalMiles());
  }

  @Test
  void testGetMilesEarnedYearlyInitialValue() {
    assertEquals(0, milesBalance.getMilesEarnedYearly());
  }

  @Test
  void testSetAndGetMilesEarnedYearly() {
    int earnedMiles = 5000;
    milesBalance.setMilesEarnedYearly(earnedMiles);
    assertEquals(earnedMiles, milesBalance.getMilesEarnedYearly());
  }

  @Test
  void testGetMilesExpiringYearlyInitialValue() {
    assertEquals(0, milesBalance.getMilesExpiringYearly());
  }

  @Test
  void testSetAndGetMilesExpiringYearly() {
    int expiringMiles = 3000;
    milesBalance.setMilesExpiringYearly(expiringMiles);
    assertEquals(expiringMiles, milesBalance.getMilesExpiringYearly());
  }

  @Test
  void testEqualsWithSameObject() {
    assertEquals(milesBalance, milesBalance);
  }

  @Test
  void testEqualsWithDifferentObjectSameValues() {
    MilesBalance anotherMilesBalance = new MilesBalance();
    assertEquals(milesBalance, anotherMilesBalance);
  }

  @Test
  void testEqualsWithDifferentObjectDifferentValues() {
    MilesBalance anotherMilesBalance = new MilesBalance();
    anotherMilesBalance.setTotalMiles(10000);
    assertNotEquals(milesBalance, anotherMilesBalance);
  }

  @Test
  void testHashCodeConsistency() {
    int initialHashCode = milesBalance.hashCode();
    assertEquals(initialHashCode, milesBalance.hashCode());
  }

  @Test
  void testHashCodeEqualityWithSameValues() {
    MilesBalance anotherMilesBalance = new MilesBalance();
    assertEquals(milesBalance.hashCode(), anotherMilesBalance.hashCode());
  }

  @Test
  void testToString() {
    String expectedToString = "MilesBalance{" +
        "totalMiles=0" +
        ", milesEarnedYearly=0" +
        ", milesExpiringYearly=0" +
        '}';
    assertEquals(expectedToString, milesBalance.toString());
  }
}