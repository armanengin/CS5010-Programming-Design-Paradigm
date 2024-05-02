package problem3;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents an abstract tax filer. This class serves as the base for different types of tax filers,
 * encapsulating common attributes and functionalities such as contact information, financial details,
 * and methods for tax calculations. It implements the ITaxFiller interface and provides a foundational
 * structure for specialized tax filer types.
 *
 * @author Arman Engin Sucu
 */
public abstract class TaxFiler implements ITaxFiller{
  //Thresholds for mortgage interest and property tax deduction calculation
  /**
   * maximum earning amount to be applicable for  mortgage deduction
   */
  public static final Double MAX_EARNING_FOR_MORTGAGE_DEDUCTION = 250000.0;

  /**
   * minimum mortgage interest and property tax expense amount to be applicable for mortgage deduction
   */
  public static final Double MIN_MORTGAGE_PROPERTY_TAXES_EXPENSES_FOR_MORTGAGE_DEDUCTION = 12500.0;

  /**
   * mortgage deduction amount
   */
  public static final Double MORTGAGE_DEDUCTION_AMOUNT = 2500.0;

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

  /**
   * Constructor for the TaxFiler class. Initializes the tax filer with provided details.
   * A unique UUID is generated for the ID.
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
  public TaxFiler(ContactInfo contactInfo, Double lastYearEarning,
      Double totalIncomeTaxPaid, Double mortgageInterestPaid, Double propertyTaxesPaid,
      Double studentLoanAndTuitionPaid, Double contrMadeToRetirementSavingsAccount,
      Double contrMadeToHealthSavingsAccount, Double charitableDonationsAndContributions) {
    this.id = UUID.randomUUID().toString();;
    this.contactInfo = contactInfo;
    this.lastYearEarning = lastYearEarning;
    this.totalIncomeTaxPaid = totalIncomeTaxPaid;
    this.mortgageInterestPaid = mortgageInterestPaid;
    this.propertyTaxesPaid = propertyTaxesPaid;
    this.studentLoanAndTuitionPaid = studentLoanAndTuitionPaid;
    this.contrMadeToRetirementSavingsAccount = contrMadeToRetirementSavingsAccount;
    this.contrMadeToHealthSavingsAccount = contrMadeToHealthSavingsAccount;
    this.charitableDonationsAndContributions = charitableDonationsAndContributions;
  }

  /**
   * gets id of the tax filler
   * @return String id
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * gets contact information of tax filler
   * @return ContactInfo object
   */
  @Override
  public ContactInfo getContactInfo() {
    return contactInfo;
  }

  /**
   * gets last year earnings
   * @return Double value to represent last year earnings
   */
  @Override
  public Double getLastYearEarning() {
    return lastYearEarning;
  }

  /**
   * gets income tax paid
   * @return Double income tax paid value
   */
  @Override
  public Double getTotalIncomeTaxPaid() {
    return totalIncomeTaxPaid;
  }

  /**
   * gets mortgage interest paid
   * @return Double mortgage interest paid value
   */
  @Override
  public Double getMortgageInterestPaid() {
    return mortgageInterestPaid;
  }

  /**
   * gets property taxes paid value
   * @return Double property taxes paid value
   */
  @Override
  public Double getPropertyTaxesPaid() {
    return propertyTaxesPaid;
  }

  /**
   * gets student loan and tuition paid
   * @return Double student loan and tuition paid value
   */
  @Override
  public Double getStudentLoanAndTuitionPaid() {
    return studentLoanAndTuitionPaid;
  }

  /**
   * gets contributions made to retirement savings account
   * @return Double value represents contributions made to retirement savings account
   */
  @Override
  public Double getContrMadeToRetirementSavingsAccount() {
    return contrMadeToRetirementSavingsAccount;
  }

  /**
   * gets contributions made to health savings account
   * @return Double value represents contributions made to health savings account
   */
  @Override
  public Double getContrMadeToHealthSavingsAccount() {
    return contrMadeToHealthSavingsAccount;
  }

  /**
   * gets charitable donations and contributions
   * @return Double value represents charitable donations and contributions
   */
  @Override
  public Double getCharitableDonationsAndContributions() {
    return charitableDonationsAndContributions;
  }


  /**
   * Abstract method to calculate taxes for tax filers. This method must be implemented
   * by subclasses to provide specific tax calculation logic.
   *
   * @return Double representing the calculated tax value
   */
  @Override
  public abstract Double calculateTaxes();

  /**
   * basic taxable income calculation which is valid for every tax filer
   * @return Double basic taxable income value
   */
  @Override
  public Double getBasicTaxableIncome(){
    return lastYearEarning - totalIncomeTaxPaid;
  }

  /**
   * calculates the tax deduction amount from retirement and health savings
   * @return Double deduction amount
   */
  @Override
  public Double getRetirementAndHealthSavingsDeduction(){
    return contrMadeToRetirementSavingsAccount + contrMadeToHealthSavingsAccount;
  }

  /**
   * Calculates the tax deduction amount from mortgage and property taxes.
   *
   * @return a Double representing the deduction amount
   */
  @Override
  public Double getDeductionForMortgageAndProperty(){
    double mortgageAndPropertyTaxes = mortgageInterestPaid + propertyTaxesPaid;
    if (lastYearEarning < MAX_EARNING_FOR_MORTGAGE_DEDUCTION
        && mortgageAndPropertyTaxes > MIN_MORTGAGE_PROPERTY_TAXES_EXPENSES_FOR_MORTGAGE_DEDUCTION) {
      return MORTGAGE_DEDUCTION_AMOUNT;
    }
    return 0.0;
  }

  /**
   * Compares this TaxFiler object to another object for equality.
   *
   * @param o the object to be compared with this TaxFiler
   * @return true if the specified object is equal to this TaxFiler; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TaxFiler taxFiler)) {
      return false;
    }
    return Objects.equals(
        getContactInfo(), taxFiler.getContactInfo()) && Objects.equals(getLastYearEarning(),
        taxFiler.getLastYearEarning()) && Objects.equals(getTotalIncomeTaxPaid(),
        taxFiler.getTotalIncomeTaxPaid()) && Objects.equals(getMortgageInterestPaid(),
        taxFiler.getMortgageInterestPaid()) && Objects.equals(getPropertyTaxesPaid(),
        taxFiler.getPropertyTaxesPaid()) && Objects.equals(getStudentLoanAndTuitionPaid(),
        taxFiler.getStudentLoanAndTuitionPaid()) && Objects.equals(
        getContrMadeToRetirementSavingsAccount(), taxFiler.getContrMadeToRetirementSavingsAccount())
        && Objects.equals(getContrMadeToHealthSavingsAccount(),
        taxFiler.getContrMadeToHealthSavingsAccount()) && Objects.equals(
        getCharitableDonationsAndContributions(),
        taxFiler.getCharitableDonationsAndContributions());
  }

  /**
   * Returns a hash code value for this TaxFiler object.
   *
   * @return an int representing the hash code value
   */
  @Override
  public int hashCode() {
    return Objects.hash(getContactInfo(), getLastYearEarning(), getTotalIncomeTaxPaid(),
        getMortgageInterestPaid(), getPropertyTaxesPaid(), getStudentLoanAndTuitionPaid(),
        getContrMadeToRetirementSavingsAccount(), getContrMadeToHealthSavingsAccount(),
        getCharitableDonationsAndContributions());
  }

  /**
   * Returns a string representation of this TaxFiler object.
   *
   * @return a String representing this TaxFiler
   */
  @Override
  public String toString() {
    return "TaxFiler{" +
        "id='" + id + '\'' +
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
  }
}
