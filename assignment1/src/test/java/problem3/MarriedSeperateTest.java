package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarriedSeperateTest {
  private MarriedSeperate marriedSeparate;
  private ContactInfo contactInfo;

  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo(new Name("Alice", "Smith"), "101 Maple St", "555-123-4567", "alice.smith@example.com");
    marriedSeparate = new MarriedSeperate(contactInfo, 60000.0, 4000.0, 1000.0, 1500.0,
        2000.0, 2500.0, 3000.0, 500.0, 1, 0, 1500.0, 500.0);
  }

  @Test
  void testContactInfo() {
    assertEquals(contactInfo, marriedSeparate.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    assertEquals(60000.0, marriedSeparate.getLastYearEarning());
  }

  @Test
  void testTotalIncomeTaxPaid() {
    assertEquals(4000.0, marriedSeparate.getTotalIncomeTaxPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    assertEquals(1000.0, marriedSeparate.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    assertEquals(1500.0, marriedSeparate.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanAndTuitionPaid() {
    assertEquals(2000.0, marriedSeparate.getStudentLoanAndTuitionPaid());
  }

  @Test
  void testContributionsToRetirementSavingsAccount() {
    assertEquals(2500.0, marriedSeparate.getContrMadeToRetirementSavingsAccount());
  }

  @Test
  void testContributionsToHealthSavingsAccount() {
    assertEquals(3000.0, marriedSeparate.getContrMadeToHealthSavingsAccount());
  }

  @Test
  void testCharitableDonationsAndContributions() {
    assertEquals(500.0, marriedSeparate.getCharitableDonationsAndContributions());
  }

  @Test
  void testNumberOfDependents() {
    assertEquals(1, marriedSeparate.getNumOfDependents());
  }

  @Test
  void testNumberOfMinorChildren() {
    assertEquals(0, marriedSeparate.getNumOfMinorChildren());
  }

  @Test
  void testChildcareExpenses() {
    assertEquals(1500.0, marriedSeparate.getChildcareExpenses());
  }

  @Test
  void testDependentCarExpenses() {
    assertEquals(500.0, marriedSeparate.getDependentCareExpenses());
  }
}