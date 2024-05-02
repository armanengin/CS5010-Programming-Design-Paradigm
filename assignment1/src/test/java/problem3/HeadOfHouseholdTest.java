package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeadOfHouseholdTest {
  private HeadOfHousehold headOfHousehold;
  private ContactInfo contactInfo;

  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo(new Name("Jane", "Doe"), "456 Elm St", "987-654-3210", "jane.doe@example.com");
    headOfHousehold = new HeadOfHousehold(contactInfo, 75000.0, 5000.0, 2000.0, 3000.0,
        4000.0, 3500.0, 2000.0, 1000.0, 2, 1, 2500.0, 1500.0);
  }

  @Test
  void testContactInfo() {
    assertEquals(contactInfo, headOfHousehold.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    assertEquals(75000.0, headOfHousehold.getLastYearEarning());
  }

  @Test
  void testTotalIncomeTaxPaid() {
    assertEquals(5000.0, headOfHousehold.getTotalIncomeTaxPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    assertEquals(2000.0, headOfHousehold.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    assertEquals(3000.0, headOfHousehold.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanAndTuitionPaid() {
    assertEquals(4000.0, headOfHousehold.getStudentLoanAndTuitionPaid());
  }

  @Test
  void testContributionsToRetirementSavingsAccount() {
    assertEquals(3500.0, headOfHousehold.getContrMadeToRetirementSavingsAccount());
  }

  @Test
  void testContributionsToHealthSavingsAccount() {
    assertEquals(2000.0, headOfHousehold.getContrMadeToHealthSavingsAccount());
  }

  @Test
  void testCharitableDonationsAndContributions() {
    assertEquals(1000.0, headOfHousehold.getCharitableDonationsAndContributions());
  }

  @Test
  void testNumberOfDependents() {
    assertEquals(2, headOfHousehold.getNumOfDependents());
  }

  @Test
  void testNumberOfMinorChildren() {
    assertEquals(1, headOfHousehold.getNumOfMinorChildren());
  }

  @Test
  void testChildcareExpenses() {
    assertEquals(2500.0, headOfHousehold.getChildcareExpenses());
  }

  @Test
  void testDependentCarExpenses() {
    assertEquals(1500.0, headOfHousehold.getDependentCareExpenses());
  }
}