package problem3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeTest {
  private Employee employee;
  private ContactInfo contactInfo;

  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo(new Name("John", "Doe"), "123 Main St", "123-456-7890", "john.doe@example.com");
    employee = new Employee(contactInfo, 50000.0, 5000.0, 1000.0, 1500.0, 2000.0, 3000.0, 2500.0, 1000.0);
  }

  @Test
  void testContactInfo() {
    assertEquals(contactInfo, employee.getContactInfo());
  }

  @Test
  void testLastYearEarning() {
    assertEquals(50000.0, employee.getLastYearEarning());
  }

  @Test
  void testTotalIncomeTaxPaid() {
    assertEquals(5000.0, employee.getTotalIncomeTaxPaid());
  }

  @Test
  void testMortgageInterestPaid() {
    assertEquals(1000.0, employee.getMortgageInterestPaid());
  }

  @Test
  void testPropertyTaxesPaid() {
    assertEquals(1500.0, employee.getPropertyTaxesPaid());
  }

  @Test
  void testStudentLoanAndTuitionPaid() {
    assertEquals(2000.0, employee.getStudentLoanAndTuitionPaid());
  }

  @Test
  void testContributionsToRetirementSavingsAccount() {
    assertEquals(3000.0, employee.getContrMadeToRetirementSavingsAccount());
  }

  @Test
  void testContributionsToHealthSavingsAccount() {
    assertEquals(2500.0, employee.getContrMadeToHealthSavingsAccount());
  }

  @Test
  void testCharitableDonationsAndContributions() {
    assertEquals(1000.0, employee.getCharitableDonationsAndContributions());
  }
}