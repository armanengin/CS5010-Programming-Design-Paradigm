package problem3;

import static org.junit.jupiter.api.Assertions.*;
import static problem3.TaxFiler.MAX_EARNING_FOR_MORTGAGE_DEDUCTION;
import static problem3.TaxFiler.MIN_MORTGAGE_PROPERTY_TAXES_EXPENSES_FOR_MORTGAGE_DEDUCTION;
import static problem3.TaxFiler.MORTGAGE_DEDUCTION_AMOUNT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaxFilerTest {
  private String id;
  private ContactInfo contactInfo;
  private Double lastYearEarning;
  private Double totalIncomeTaxPaid;
  private Double mortgageInterestPaid;
  private Double propertyTaxesPaid;
  private Double studentLoanAndTuitionPaid;
  private Double contrMadeToRetirementSavingsAccount;
  private Double contrMadeToHealthSavingsAccount;
  private Double charitableDonationsAndContributions;

  private TaxFiler taxFiler;
  private TaxFiler testTaxFiler;

  private TaxFiler differentTaxFiler;

  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo(new Name("Katya", "Maria"), "1239 on 2nd Ave", "3128796735", "katya@gmail.com");
    lastYearEarning = 100000.0;
    totalIncomeTaxPaid = 2000.0;
    mortgageInterestPaid = 1500.0;
    propertyTaxesPaid = 400.0;
    studentLoanAndTuitionPaid = 500.0;
    contrMadeToRetirementSavingsAccount = 1200.0;
    contrMadeToHealthSavingsAccount = 1100.0;
    charitableDonationsAndContributions = 300.0;
    taxFiler = new Employee(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount, contrMadeToHealthSavingsAccount,
        charitableDonationsAndContributions);
    id = taxFiler.getId();

    testTaxFiler = new Employee(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount, contrMadeToHealthSavingsAccount,
        charitableDonationsAndContributions);

    differentTaxFiler = new Employee(new ContactInfo(new Name("Different", "Name"), "Different Address", "Different Phone", "different@email.com"),
        90000.0, 3000.0, 2000.0, 500.0, 600.0, 1500.0, 1400.0, 500.0);
  }

  @Test
  void getId() {
    assertEquals(id, taxFiler.getId());
  }

  @Test
  void getContactInfo() {
    assertEquals(contactInfo, taxFiler.getContactInfo());
  }

  @Test
  void getLastYearEarning() {
    assertEquals(lastYearEarning, taxFiler.getLastYearEarning());
  }

  @Test
  void getTotalIncomeTaxPaid() {
    assertEquals(totalIncomeTaxPaid, taxFiler.getTotalIncomeTaxPaid());
  }

  @Test
  void getMortgageInterestPaid() {
    assertEquals(mortgageInterestPaid, taxFiler.getMortgageInterestPaid());
  }

  @Test
  void getPropertyTaxesPaid() {
    assertEquals(propertyTaxesPaid, taxFiler.getPropertyTaxesPaid());
  }

  @Test
  void getStudentLoanAndTuitionPaid() {
    assertEquals(studentLoanAndTuitionPaid, taxFiler.getStudentLoanAndTuitionPaid());
  }

  @Test
  void getContrMadeToRetirementSavingsAccount() {
    assertEquals(contrMadeToRetirementSavingsAccount, taxFiler.getContrMadeToRetirementSavingsAccount());
  }

  @Test
  void getContrMadeToHealthSavingsAccount() {
    assertEquals(contrMadeToHealthSavingsAccount, taxFiler.getContrMadeToHealthSavingsAccount());
  }

  @Test
  void getCharitableDonationsAndContributions() {
    assertEquals(charitableDonationsAndContributions, taxFiler.getCharitableDonationsAndContributions());
  }

  @Test
  void testEqualsWithSelf() {
    assertTrue(taxFiler.equals(taxFiler));
  }

  @Test
  void testEqualsWithNull() {
    assertFalse(taxFiler.equals(null));
  }

  @Test
  void testEqualsWithDifferentClass() {
    assertFalse(taxFiler.equals(new String("Not a TaxFiler")));
  }

  @Test
  void testEqualsWithDifferentTaxFiler() {
    assertFalse(taxFiler.equals(differentTaxFiler));
  }

  @Test
  void testEqualsWithDifferentLastYearEarning() {
    TaxFiler modifiedTaxFiler = new Employee(contactInfo, 90000.0, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
    assertFalse(taxFiler.equals(modifiedTaxFiler));
  }

  @Test
  void testEqualsWithDifferentTotalIncomeTaxPaid() {
    TaxFiler modifiedTaxFiler = new Employee(contactInfo, lastYearEarning, 3000.0, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
    assertFalse(taxFiler.equals(modifiedTaxFiler));
  }

  // Test for different mortgageInterestPaid
  @Test
  void testEqualsWithDifferentMortgageInterestPaid() {
    TaxFiler modifiedTaxFiler = new Employee(contactInfo, lastYearEarning, totalIncomeTaxPaid, 2500.0,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
    assertFalse(taxFiler.equals(modifiedTaxFiler));
  }

  // Test for different propertyTaxesPaid
  @Test
  void testEqualsWithDifferentPropertyTaxesPaid() {
    TaxFiler modifiedTaxFiler = new Employee(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        500.0, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
    assertFalse(taxFiler.equals(modifiedTaxFiler));
  }

  // Test for different studentLoanAndTuitionPaid
  @Test
  void testEqualsWithDifferentStudentLoanAndTuitionPaid() {
    TaxFiler modifiedTaxFiler = new Employee(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, 1000.0, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
    assertFalse(taxFiler.equals(modifiedTaxFiler));
  }

  @Test
  void testHashCode() {
    assertEquals(taxFiler.hashCode(), testTaxFiler.hashCode());
  }

  @Test
  void getBasicTaxableIncome() {
    Double expectedTaxableIncome = lastYearEarning - totalIncomeTaxPaid;
    assertEquals(expectedTaxableIncome, taxFiler.getBasicTaxableIncome());
  }

  @Test
  void getRetirementAndHealthSavingsDeduction() {
    Double expectedRetirementAndHealthSavingsDeduction = contrMadeToRetirementSavingsAccount + contrMadeToHealthSavingsAccount;
    assertEquals(expectedRetirementAndHealthSavingsDeduction, taxFiler.getRetirementAndHealthSavingsDeduction());
  }

  @Test
  void getDeductionForMortgageAndProperty() {
    Double expectedDeduction = 0.0;
    double mortgageAndPropertyTaxes = mortgageInterestPaid + propertyTaxesPaid;
    if (lastYearEarning < MAX_EARNING_FOR_MORTGAGE_DEDUCTION
        && mortgageAndPropertyTaxes > MIN_MORTGAGE_PROPERTY_TAXES_EXPENSES_FOR_MORTGAGE_DEDUCTION) {
        expectedDeduction = MORTGAGE_DEDUCTION_AMOUNT;
    }
    assertEquals(expectedDeduction, taxFiler.getDeductionForMortgageAndProperty());
  }

  @Test
  void testToString() {
    String expectedString = "TaxFiler{" +
        "id='" + taxFiler.getId() + '\'' +
        ", contactInfo=" + contactInfo +
        ", lastYearEarning=" + lastYearEarning +
        ", totalIncomeTaxPaid=" + totalIncomeTaxPaid +
        ", mortgageInterestPaid=" + mortgageInterestPaid +
        ", propertyTaxesPaid=" + propertyTaxesPaid +
        ", studentLoanAndTuitionPaid=" + studentLoanAndTuitionPaid +
        ", contrMadeToRetirementSavingsAccount=" + contrMadeToRetirementSavingsAccount +
        ", contrMadeToHealthSavingsAccount=" + contrMadeToHealthSavingsAccount +
        ", charitableDonationsAndContributions=" + charitableDonationsAndContributions +
        '}';
    assertEquals(expectedString, taxFiler.toString());
  }
}