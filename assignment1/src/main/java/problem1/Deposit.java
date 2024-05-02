package problem1;

import java.util.Objects;

/**
 * This class represents a deposit for a transfer, encapsulating details such as the amount to be deposited,
 * the recipient's name, and their identification number.
 * <p>
 * The class enforces constraints on the deposit amount and provides methods to access and modify
 * the deposit details.
 * </p>
 *
 * @author Arman Engin Sucu
 */

public class Deposit {
  private int amount;
  private Name recipientName;
  private String recipientId;

  /**
   * Constructs a new Deposit instance with specified amount, recipient's name, and recipient's ID.
   * The deposit amount must be within a specified range, otherwise an IllegalArgumentException is thrown.
   *
   * @param amount        the deposit amount, must be within the range [1000, 10000]
   * @param recipientName the recipient's name
   * @param recipientId   the recipient's identification number
   * @throws IllegalArgumentException if the deposit amount is outside the valid range
   */
  public Deposit(int amount, Name recipientName, String recipientId) throws IllegalArgumentException{
    final int DEPOSIT_LOWER_BOUND = 1000;
    final int DEPOSIT_UPPER_BOUND = 10000;
    if (amount >= DEPOSIT_LOWER_BOUND && amount <= DEPOSIT_UPPER_BOUND){
      this.amount = amount;
      this.recipientName = recipientName;
      this.recipientId = recipientId;
    } else{
      throw new IllegalArgumentException("The deposit amount has to be in the range of [" + DEPOSIT_LOWER_BOUND + ", " + DEPOSIT_UPPER_BOUND + "]!");
    }
  }

  /**
   * Returns the deposit amount.
   *
   * @return the amount to be transferred
   */
  public int getAmount(){
    return amount;
  }

  /**
   * Sets the deposit amount.
   *
   * @param amount the amount to be transferred
   */
  public void setAmount(int amount){
    this.amount = amount;
  }

  /**
   * Returns the recipient's name.
   *
   * @return the recipient's Name object
   */
  public Name getRecipientName(){
    return recipientName;
  }

  /**
   * Sets the recipient's name.
   *
   * @param recipientName the recipient's name
   */
  public void setRecipientName(Name recipientName){
    this.recipientName = recipientName;
  }

  /**
   * Returns the recipient's ID.
   *
   * @return the recipient's identification number as a string
   */
  public String getRecipientId(){
    return recipientId;
  }

  /**
   * Sets the recipient's ID.
   *
   * @param recipientId the recipient's identification number
   */
  public void setRecipientId(String recipientId){
    this.recipientId = recipientId;
  }

  /**
   * Compares this deposit with the specified object for equality.
   * Two deposits are considered equal if they have the same amount, recipient's name, and recipient's ID.
   *
   * @param o the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Deposit deposit)) {
      return false;
    }
    return getAmount() == deposit.getAmount() && Objects.equals(getRecipientName(),
        deposit.getRecipientName()) && Objects.equals(getRecipientId(),
        deposit.getRecipientId());
  }

  /**
   * Returns a hash code value for the deposit.
   * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
   *
   * @return a hash code value for this deposit
   */
  @Override
  public int hashCode() {
    return Objects.hash(getAmount(), getRecipientName(), getRecipientId());
  }

  /**
   * Returns a string representation of the deposit, including the amount, recipient's name, and recipient's ID.
   *
   * @return a string representation of the deposit
   */
  @Override
  public String toString() {
    return "Deposit{" +
        "amount=" + amount +
        ", recipientName=" + recipientName +
        ", recipientId='" + recipientId + '\'' +
        '}';
  }
}
