package problem3;

import static java.lang.Math.max;

/**
 * Represents an abstract individual filer for tax purposes. This class extends {@code TaxFiler} and
 * includes tax-related information and rules specific to individual filers, such as retirement and
 * health deduction ratios, and taxable income thresholds and ratios.
 * It provides methods to calculate taxes based on these parameters and specific deduction rules for
 * individual filers.
 *
 * @author Arman Engin Sucu
 */
public abstract class IndividualFiler extends TaxFiler {

  /**
   * retirement and health deduction ratio for individual filers
   */
  public static final Double RETIREMENT_AND_HEALTH_DEDUCTION_RATIO = 0.7;

  //variables to calculate basic tax for individual filer
  /**
   * taxable income threshold value for individual filers
   */
  public static final Double TAXABLE_INCOME_THRESHOLD = 55000.0;

  /**
   * taxable income ratio for individual filers whose income lower than the threshold
   */
  public static final Double TAXABLE_INCOME_LOWER_RATIO = 0.15;

  /**
   * taxable income ratio for individual filers whose income greater than the threshold
   */
  public static final Double TAXABLE_INCOME_UPPER_RATIO = 0.19;

  /**
   * Constructs a new IndividualFiler instance with specific details relevant to an individual tax filer.
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
   */
  public IndividualFiler(ContactInfo contactInfo, Double lastYearEarning,
      Double totalIncomeTaxPaid, Double mortgageInterestPaid, Double propertyTaxesPaid,
      Double studentLoanAndTuitionPaid, Double contrMadeToRetirementSavingsAccount,
      Double contrMadeToHealthSavingsAccount, Double charitableDonationsAndContributions) {
    super(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
  }

  /**
   * Calculates the total tax to be paid by the individual filer based on various factors including
   * income, deductions, and specific tax rules for individual filers.
   *
   * @return the calculated tax amount as a Double
   */
  @Override
  public Double calculateTaxes() {
    double basicTaxableIncome = super.getBasicTaxableIncome();
    double healthAndRetirementDeduction = calculateRetirementAndHealthDeduction();
    double deductionForMortgageAndProperty = super.getDeductionForMortgageAndProperty();
    //tax can not be smaller than 0
    double finalTaxableAmount = max(0,basicTaxableIncome -
        healthAndRetirementDeduction -
        deductionForMortgageAndProperty);

    if (finalTaxableAmount < TAXABLE_INCOME_THRESHOLD) {
      return finalTaxableAmount * TAXABLE_INCOME_LOWER_RATIO;
    }

    return finalTaxableAmount * TAXABLE_INCOME_UPPER_RATIO;
  }

  /**
   * Calculates the deduction amount for retirement and health savings.
   *
   * @return the calculated retirement and health savings deduction as a Double
   */
  private Double calculateRetirementAndHealthDeduction() {
    double deduction = super.getRetirementAndHealthSavingsDeduction() * RETIREMENT_AND_HEALTH_DEDUCTION_RATIO;
    //Set the difference to 0 if the amount of retirement and health deduction is more than the amount of basic taxable income
    return Math.min(deduction, super.getBasicTaxableIncome());
  }
}
