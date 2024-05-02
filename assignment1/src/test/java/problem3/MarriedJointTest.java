package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarriedJointTest {
  private MarriedJoint marriedJoint;
  private ContactInfo contactInfo;

  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo(new Name("John", "Doe"), "789 Pine St", "111-222-3333", "john.doe@example.com");
    marriedJoint = new MarriedJoint(contactInfo, 85000.0, 7000.0, 2000.0, 3000.0,
        4000.0, 3500.0, 2500.0, 1500.0, 3, 2, 3000.0, 2000.0);
  }

  @Test
  void testContactInfo() {
    assertEquals(contactInfo, marriedJoint.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    assertEquals(85000.0, marriedJoint.getLastYearEarning());
  }

  @Test
  void testTotalIncomeTaxPaid() {
    assertEquals(7000.0, marriedJoint.getTotalIncomeTaxPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    assertEquals(2000.0, marriedJoint.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    assertEquals(3000.0, marriedJoint.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanAndTuitionPaid() {
    assertEquals(4000.0, marriedJoint.getStudentLoanAndTuitionPaid());
  }

  @Test
  void testContributionsToRetirementSavingsAccount() {
    assertEquals(3500.0, marriedJoint.getContrMadeToRetirementSavingsAccount());
  }

  @Test
  void testContributionsToHealthSavingsAccount() {
    assertEquals(2500.0, marriedJoint.getContrMadeToHealthSavingsAccount());
  }

  @Test
  void testCharitableDonationsAndContributions() {
    assertEquals(1500.0, marriedJoint.getCharitableDonationsAndContributions());
  }

  @Test
  void testNumberOfDependents() {
    assertEquals(3, marriedJoint.getNumOfDependents());
  }

  @Test
  void testNumberOfMinorChildren() {
    assertEquals(2, marriedJoint.getNumOfMinorChildren());
  }

  @Test
  void testChildcareExpenses() {
    assertEquals(3000.0, marriedJoint.getChildcareExpenses());
  }

  @Test
  void testDependentCarExpenses() {
    assertEquals(2000.0, marriedJoint.getDependentCareExpenses());
  }
}