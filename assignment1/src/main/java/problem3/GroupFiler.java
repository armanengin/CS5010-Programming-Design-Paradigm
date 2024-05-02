package problem3;

import static java.lang.Math.max;

import java.util.Objects;

/**
 * Represents an abstract group filer for tax purposes. This class extends {@code TaxFiler} and
 * includes additional information and rules specific to group filers such as dependents, minor children,
 * childcare and dependent care expenses. It provides methods to calculate taxes based on these additional
 * parameters and specific deduction rules for group filers.
 *
 * @author Arman Engin Sucu
 */
public abstract class GroupFiler extends TaxFiler {
  //retirement and health variables

  /**
   * retirement and health deduction ratio for group filers
   */
  public static final Double RETIREMENT_AND_HEALTH_DEDUCTION_RATIO = 0.65;

  /**
   * maximum retirement and health deduction amount
   */
  public static final Double MAX_RETIREMENT_AND_HEALTH_DEDUCTION = 17500.0;

  //childcare variables
  /**
   * maximum income amount to be applicable to childcare deduction
   */
  public static final Double MAX_INCOME_FOR_CHILDCARE_DEDUCTION = 200000.0;

  /**
   * minimum childcare expense amount to be applicable for childcare deduction
   */
  public static final Double MIN_EXPENSES_FOR_CHILDCARE_DEDUCTION = 5000.0;

  /**
   * childcare deduction amount
   */
  public static final Double CHILDCARE_DEDUCTION_AMOUNT = 1250.0;

  //variables to calculate basic tax for group filer
  /**
   * taxable income threshold value for group filers
   */
  public static final Double TAXABLE_INCOME_THRESHOLD = 90000.0;

  /**
   * taxable income ratio for group filers whose income lower than the threshold
   */
  public static final Double TAXABLE_INCOME_LOWER_RATIO = 0.145;

  /**
   * taxable income ratio for group filers whose income greater than the threshold
   */
  public static final Double TAXABLE_INCOME_UPPER_RATIO = 0.185;


  private int numOfDependents;
  private int numOfMinorChildren;
  private Double childcareExpenses;
  private Double dependentCareExpenses;

  /**
   * Constructs a new GroupFiler instance with specific details relevant to group tax filers.
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
   * @param dependentCareExpenses dependent care expenses of the group filer
   */
  public GroupFiler(ContactInfo contactInfo, Double lastYearEarning,
      Double totalIncomeTaxPaid, Double mortgageInterestPaid, Double propertyTaxesPaid,
      Double studentLoanAndTuitionPaid, Double contrMadeToRetirementSavingsAccount,
      Double contrMadeToHealthSavingsAccount, Double charitableDonationsAndContributions,
      int numOfDependents, int numOfMinorChildren, Double childcareExpenses,
      Double dependentCareExpenses) {
    super(contactInfo, lastYearEarning, totalIncomeTaxPaid, mortgageInterestPaid,
        propertyTaxesPaid, studentLoanAndTuitionPaid, contrMadeToRetirementSavingsAccount,
        contrMadeToHealthSavingsAccount, charitableDonationsAndContributions);
    this.numOfDependents = numOfDependents;
    this.numOfMinorChildren = numOfMinorChildren;
    this.childcareExpenses = childcareExpenses;
    this.dependentCareExpenses = dependentCareExpenses;
  }

  /**
   * Retrieves the number of dependents for the group filer.
   *
   * @return the number of dependents
   */
  public int getNumOfDependents() {
    return numOfDependents;
  }

  /**
   * Sets the number of dependents for the group filer.
   *
   * @param numOfDependents the new number of dependents
   */
  public void setNumOfDependents(int numOfDependents) {
    this.numOfDependents = numOfDependents;
  }

  /**
   * Retrieves the number of minor children for the group filer.
   *
   * @return the number of minor children
   */
  public int getNumOfMinorChildren() {
    return numOfMinorChildren;
  }

  /**
   * Sets the number of minor children for the group filer.
   *
   * @param numOfMinorChildren the new number of minor children
   */
  public void setNumOfMinorChildren(int numOfMinorChildren) {
    this.numOfMinorChildren = numOfMinorChildren;
  }

  /**
   * Retrieves the childcare expenses for the group filer.
   *
   * @return the childcare expenses
   */
  public Double getChildcareExpenses() {
    return childcareExpenses;
  }

  /**
   * Sets the childcare expenses for the group filer.
   *
   * @param childcareExpenses the new childcare expenses
   */
  public void setChildcareExpenses(Double childcareExpenses) {
    this.childcareExpenses = childcareExpenses;
  }

  /**
   * Retrieves the dependent care expenses for the group filer.
   *
   * @return the dependent care expenses
   */
  public Double getDependentCareExpenses() {
    return dependentCareExpenses;
  }

  /**
   * Sets the dependent care expenses for the group filer.
   *
   * @param dependentCareExpenses the new dependent care expenses
   */
  public void setDependentCareExpenses(Double dependentCareExpenses) {
    this.dependentCareExpenses = dependentCareExpenses;
  }

  /**
   * Calculates the total tax to be paid by the group filer based on various factors including
   * income, deductions, and specific tax rules for group filers.
   *
   * @return the calculated tax amount as a Double
   */
  @Override
  public Double calculateTaxes() {
    Double basicTaxableIncome = super.getBasicTaxableIncome();
    Double healthAndRetirementDeduction = calculateRetirementAndHealthDeduction();
    Double deductionForMortgageAndProperty = super.getDeductionForMortgageAndProperty();
    Double childcareDeduction = getChildcareDeduction();

    //tax can not be smaller than 0
    Double finalTaxableAmount = max(0,basicTaxableIncome - healthAndRetirementDeduction -
        deductionForMortgageAndProperty -
        childcareDeduction);

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
  public Double calculateRetirementAndHealthDeduction() {
    Double healthAndRetirementDeduction = super.getRetirementAndHealthSavingsDeduction() *
        RETIREMENT_AND_HEALTH_DEDUCTION_RATIO;

    //Floor tax deduction for healthcare and retirement savings to the specified value
    if (healthAndRetirementDeduction > MAX_RETIREMENT_AND_HEALTH_DEDUCTION) {
      healthAndRetirementDeduction = MAX_RETIREMENT_AND_HEALTH_DEDUCTION;
    }

    //Set the difference to 0 if the amount of retirement and health deduction is more than the amount of basic taxable income
    if (healthAndRetirementDeduction > super.getBasicTaxableIncome()) {
      healthAndRetirementDeduction = super.getBasicTaxableIncome();
    }

    return healthAndRetirementDeduction;
  }

  /**
   * Computes the childcare deduction based on the filer's income and childcare expenses.
   *
   * @return the calculated childcare deduction as a Double
   */
  public Double getChildcareDeduction() {
    if (super.getBasicTaxableIncome() < MAX_INCOME_FOR_CHILDCARE_DEDUCTION &&
        childcareExpenses > MIN_EXPENSES_FOR_CHILDCARE_DEDUCTION) {
      return CHILDCARE_DEDUCTION_AMOUNT;
    }
    return 0.0;
  }

  /**
   * Compares this GroupFiler object to another object for equality. The comparison is based on
   * number of dependents, number of minor children, childcare expenses, and dependent care expenses.
   *
   * @param o the object to be compared with this GroupFiler
   * @return true if the specified object is equal to this GroupFiler; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GroupFiler that)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return getNumOfDependents() == that.getNumOfDependents()
        && getNumOfMinorChildren() == that.getNumOfMinorChildren() && Objects.equals(
        getChildcareExpenses(), that.getChildcareExpenses()) && Objects.equals(
        getDependentCareExpenses(), that.getDependentCareExpenses());
  }

  /**
   * Returns a hash code value for this GroupFiler object.
   *
   * @return a hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getNumOfDependents(), getNumOfMinorChildren(),
        getChildcareExpenses(), getDependentCareExpenses());
  }

  /**
   * Returns a string representation of this TaxFiler object.
   *
   * @return a String representing this TaxFiler
   */
  @Override
  public String toString() {
    return "GroupFiler{" +
        "numOfDependents=" + numOfDependents +
        ", numOfMinorChildren=" + numOfMinorChildren +
        ", childcareExpenses=" + childcareExpenses +
        ", dependentCareExpenses=" + dependentCareExpenses +
        '}';
  }
}
