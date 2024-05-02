package problem3;

import static java.lang.Math.max;
import static org.junit.jupiter.api.Assertions.*;
import static problem3.IndividualFiler.RETIREMENT_AND_HEALTH_DEDUCTION_RATIO;
import static problem3.IndividualFiler.TAXABLE_INCOME_LOWER_RATIO;
import static problem3.IndividualFiler.TAXABLE_INCOME_THRESHOLD;
import static problem3.IndividualFiler.TAXABLE_INCOME_UPPER_RATIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndividualFilerTest {
  private Employee employee;
  private ContactInfo contactInfo;
  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo(new Name("Test", "User"), "1234 Test St", "1234567890", "test@example.com");
    employee = new Employee(contactInfo, 60000.0, 5000.0, 2000.0, 1000.0, 3000.0, 4000.0, 2000.0, 1000.0);
  }

  @Test
  void testCalculateTaxesBelowThreshold() {
    assertEquals(7620.0, employee.calculateTaxes(), 0.01);
  }

  @Test
  void testCalculateTaxesAboveThreshold() {
    Employee employeeWithHigherEarning = new Employee(contactInfo, 80000.0, 7000.0, 3000.0, 2000.0, 4000.0, 5000.0, 2500.0, 1500.0);
    double expectedTax = 12872.5;
    assertEquals(expectedTax, employeeWithHigherEarning.calculateTaxes(), 0.01);
  }
}