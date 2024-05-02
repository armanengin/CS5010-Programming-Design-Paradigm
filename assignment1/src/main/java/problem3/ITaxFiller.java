package problem3;
/**
 * Represents the interface class for the tax filler object
 * @author Arman Engin Sucu
 */
public interface ITaxFiller {
  /**
   * gets id of the tax filler
   * @return String id
   */
  String getId();

  /**
   * gets contact information of tax filler
   * @return ContactInfo object
   */
  ContactInfo getContactInfo();

  /**
   * gets last year earnings
   * @return Double value to represent last year earnings
   */
  Double getLastYearEarning();

  /**
   * gets income tax paid
   * @return Double income tax paid value
   */
  Double getTotalIncomeTaxPaid();

  /**
   * gets mortgage interest paid
   * @return Double mortgage interest paid value
   */
  Double getMortgageInterestPaid();

  /**
   * gets property taxes paid value
   * @return Double property taxes paid value
   */
  Double getPropertyTaxesPaid();

  /**
   * gets student loan and tuition paid
   * @return Double student loan and tuition paid value
   */
  Double getStudentLoanAndTuitionPaid();

  /**
   * gets contributions made to retirement savings account
   * @return Double value represents contributions made to retirement savings account
   */
  Double getContrMadeToRetirementSavingsAccount();

  /**
   * gets contributions made to health savings account
   * @return Double value represents contributions made to health savings account
   */
  Double getContrMadeToHealthSavingsAccount();

  /**
   * gets charitable donations and contributions
   * @return Double value represents charitable donations and contributions
   */
  Double getCharitableDonationsAndContributions();

  /**
   * Calculates the tax amount to be paid for tax filer
   * @return the Double tax amount
   */
  Double calculateTaxes();

  /**
   * finds tax filer's basic taxable income
   * @return Double value to represent tax filer's basic taxable income
   */
  Double getBasicTaxableIncome();

  /**
   * finds the deduction based on the contributions made to the health and retirement saving accounts
   * @return Double value
   */
  Double getRetirementAndHealthSavingsDeduction();

  /**
   * finds deduction based on the taxes paid on mortgage and property
   * @return Double value
   */
  Double getDeductionForMortgageAndProperty();
}
