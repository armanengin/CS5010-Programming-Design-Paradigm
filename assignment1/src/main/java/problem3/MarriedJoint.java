package problem3;

/**
 * Represents a married couple filing jointly, categorized as a group filer for tax purposes.
 * This class extends the {@code GroupFiler} class, inheriting its properties and methods,
 * and specifically represents a married couple who files their taxes jointly.
 * It includes relevant information for such filers, such as contact information, financial details,
 * and information about dependents and childcare expenses.
 *
 * @author Arman Engin Sucu
 */
public class MarriedJoint extends GroupFiler {

  /**
   * Constructs a new MarriedJoint instance with specific details relevant to a married couple filing jointly.
   *
   * @param contactInfo contact information of the tax filer
   * @param lastYearEarning last year's earnings of the tax filer
   * @param totalIncomeTaxPaid total income tax paid by the tax filer
   * @param mortgageInterestPaid mortgage interest paid by the tax filer
   * @param propertyTaxesPaid property taxes paid by the tax filer
   * @param studentLoanAndTuitionPaid student loan and tuition expenses paid by the tax filer
   * @param contrMadeToRetirementSavingsAccount contributions made to the retirement savings account by the tax filer
   * @param contrMadeToHealthSavingsAccount contributions made to the health savings account by the tax filer
   * @param charitableDonationsAndContributions charitable donations and contributions made by the tax filer
   * @param numOfDependents the number of dependents of the group filer
   * @param numOfMinorChildren the number of minor children of the group filer
   * @param childcareExpenses childcare expenses of the group filer
   * @param dependentCarExpenses dependent car expenses of the group filer
   */
  public MarriedJoint(ContactInfo contactInfo, Double lastYearEarning,
      Double totalIncomeTaxPaid, Double mortgageInterestPaid, Double propertyTaxesPaid,
      Double studentLoanAndTuitionPaid, Double contrMadeToRetirementSavingsAccount,
      Double contrMadeToHealthSavingsAccount, Double charitableDonationsAndContributions,
      int numOfDependents, int numOfMinorChildren, Double childcareExpenses,
      Double dependentCarExpenses) {
    super(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions, numOfDependents,
        numOfMinorChildren, childcareExpenses, dependentCarExpenses);
  }
}
