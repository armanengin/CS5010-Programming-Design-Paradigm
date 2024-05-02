package problem1;
/**
 * This class represents a frequent flyer in a flight rewards program.
 * It includes details such as the flyer's ID, name, email, and miles balance.
 * The class provides functionality for managing the flyer's account and miles transactions.
 *
 * @author Arman Engin Sucu
 */
public class FrequentFlyer {
  private String id;
  private Name name;
  private String email;
  private MilesBalance miles;

  /**
   * Constructs a FrequentFlyer with a specified ID, name, and email.
   * The ID must be exactly 12 characters long; otherwise, an IllegalArgumentException is thrown.
   *
   * @param id    The frequent flyer’s unique account ID, must be 12 characters long.
   * @param name  The frequent flyer's name, including first, middle, and last name.
   * @param email The frequent flyer's email address.
   * @throws IllegalArgumentException if the given ID is not 12 characters long.
   */
  public FrequentFlyer(String id, Name name, String email) throws IllegalArgumentException{
    final int ID_SIZE = 12;
    if (id.length() == ID_SIZE){
      this.id = id;
      this.name = name;
      this.email = email;
      this.miles = new MilesBalance();
      //FrequentFlyerSystem.addFrequentFlyer(this);
    }else{
      throw new IllegalArgumentException("Id has to be " + ID_SIZE + " characters!");
    }
  }

  /**
   * Returns the frequent flyer's ID.
   *
   * @return the ID of the frequent flyer.
   */
  public String getId(){
    return id;
  }

  /**
   * Sets the frequent flyer's ID.
   *
   * @param id the new ID of the frequent flyer.
   */
  public void setId(String id){
    this.id = id;
  }

  /**
   * Returns the frequent flyer's name.
   *
   * @return the name of the frequent flyer.
   */
  public Name getName(){
    return name;
  }

  /**
   * Sets the frequent flyer's name.
   *
   * @param name the new name of the frequent flyer.
   */
  public void setName(Name name){
    this.name = name;
  }

  /**
   * Returns the frequent flyer's email address.
   *
   * @return the email of the frequent flyer.
   */
  public String getEmail(){
    return email;
  }

  /**
   * Sets the frequent flyer's email address.
   *
   * @param email the new email of the frequent flyer.
   */

  public void setEmail(String email){
    this.email = email;
  }

  /**
   * Returns the frequent flyer's miles balance.
   *
   * @return the miles balance of the frequent flyer.
   */
  public MilesBalance getMiles(){
    return miles;
  }

  /**
   * Sets the frequent flyer's miles balance.
   *
   * @param miles the new miles balance of the frequent flyer.
   */
  public void setMiles(MilesBalance miles){
    this.miles = miles;
  }

  /**
   * Transfers miles from this frequent flyer's account to another flyer as specified in the deposit.
   * Validates the recipient's existence, matches the recipient's ID and name, and checks if the amount
   * to be transferred is available in the account. If these conditions are met, the transfer is executed.
   *
   * @param deposit the deposit specifying the amount and recipient's information.
   * @throws IllegalArgumentException if the recipient doesn't exist, if the ID and name do not match,
   *                                  or if the amount to be transferred exceeds the available balance.
   */
  public void transferMiles(Deposit deposit){
    validateTransfer(deposit);
    // Perform the actual miles transfer
    transferMilesToRecipient(deposit.getRecipientId(), deposit.getAmount());
    subtractMilesFromSender(deposit.getAmount());
  }

  /**
   * Validates the transfer of miles based on the deposit details.
   * Checks if the recipient exists, if the recipient's ID and name match, and if there is a valid amount of miles to transfer.
   *
   * @param deposit the deposit specifying the amount and recipient's information.
   * @throws IllegalArgumentException if the recipient doesn't exist, if the ID and name do not match,
   *                                  or if the amount to be transferred exceeds the available balance.
   */
  private void validateTransfer(Deposit deposit) {
    String recipientId = deposit.getRecipientId();
    Name recipientName = deposit.getRecipientName();

    // Check if the recipient frequent flyer exists
    if (!FrequentFlyerSystem.isFlyerExist(recipientId)) {
      throw new IllegalArgumentException("The recipient for transfer doesn't exist!");
    }

    // Check if the ID and name correspond to the same person
    if (!FrequentFlyerSystem.idNameCheck(recipientId, recipientName)) {
      throw new IllegalArgumentException("The ID and name of the recipient don't match!");
    }

    // Check if there is a valid amount to transfer
    if (!mileAmountCheckForDeposit(deposit)) {
      throw new IllegalArgumentException("There is not enough miles to transfer!");
    }
  }

  /**
   * Handles the actual transfer of miles from this frequent flyer's account to the recipient's account.
   * Updates the total miles, miles earned yearly, and miles expiring yearly for both the sender and the recipient.
   *
   * @param recipientId the ID of the recipient frequent flyer.
   * @param amountToDeposit the amount of miles to be transferred.
   */

  private void transferMilesToRecipient(String recipientId, int amountToDeposit) {
    FrequentFlyer recipient = FrequentFlyerSystem.getFrequentFlyer(recipientId);
    MilesBalance recipientMile = recipient.getMiles();

    recipient.getMiles().setTotalMiles(
        recipient.getMiles().getTotalMiles() + amountToDeposit
    );

    recipient.getMiles().setMilesEarnedYearly(
        recipientMile.getMilesEarnedYearly() + amountToDeposit
    );

    recipient.getMiles().setMilesExpiringYearly(
        recipientMile.getMilesExpiringYearly() + amountToDeposit
    );
  }

  /**
   * Subtracts miles from the sender's (this frequent flyer's) account.
   * Updates the total miles and the miles expiring yearly based on the specified amount.
   *
   * @param amountToSubtract the amount of miles to be subtracted from the sender's account.
   */
  private void subtractMilesFromSender(int amountToSubtract) {
    this.miles.setTotalMiles(this.miles.getTotalMiles() - amountToSubtract);
    this.miles.setMilesExpiringYearly(this.miles.getMilesExpiringYearly() - amountToSubtract);
  }

  /**
   * Checks if the miles amount is sufficient for the transfer specified in the deposit.
   *
   * @param deposit the deposit representing the amount and recipient information.
   * @return true if there are enough miles for the transfer, false otherwise.
   */
  public boolean mileAmountCheckForDeposit(Deposit deposit){
    int totalMiles = this.miles.getTotalMiles();
    int depositedAmount = deposit.getAmount();
    return totalMiles >= depositedAmount;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param obj the reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  public boolean equals(Object obj){
    if (this == obj)
      return true;
    else if (obj == null)
      return false;
    else if (getClass() != obj.getClass())
      return false;
    else{
      FrequentFlyer other = (FrequentFlyer) obj;
      return other.getId().equals(id) &&
          other.getName().equals(name) &&
          other.getEmail().equals(email) &&
          other.getMiles().equals(miles);
    }
  }

  /**
   * Returns a hash code value for the object. This method is supported for the benefit
   * of hash tables such as those provided by {@link java.util.HashMap}.
   * The hash code is calculated using a prime multiplier and the hash codes of the id, name, email, and miles.
   *
   * @return an integer hash code value for this object.
   */
  @Override
  public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + id.hashCode();
    result = prime * result + name.hashCode();
    result = prime * result + email.hashCode();
    result = prime * result + miles.hashCode();
    return result;
  }

  /**
   * Returns a string representation of the FrequentFlyer object.
   * The string includes the frequent flyer's ID, name, email, and miles balance.
   *
   * @return a string representation of the FrequentFlyer object.
   */
  @Override
  public String toString() {
    return "FrequentFlyer{" +
        "id='" + id + '\'' +
        ", name=" + name +
        ", email='" + email + '\'' +
        ", miles=" + miles +
        '}';
  }
}


