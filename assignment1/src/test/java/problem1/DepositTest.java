package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DepositTest {
  private Deposit deposit;
  private Name recipientName;

  @BeforeEach
  void setUp() {
    recipientName = new Name("John", "Middle", "Doe");
    deposit = new Deposit(5000, recipientName, "123456ID");
  }

  @Test
  void constructorValidAmount() {
    assertEquals(5000, deposit.getAmount());
  }

  @Test
  void constructorInvalidAmount() {
    assertThrows(IllegalArgumentException.class, () -> new Deposit(15000, recipientName, "123456ID"));
  }

  @Test
  void getAmount() {
    assertEquals(5000, deposit.getAmount());
  }

  @Test
  void setAmount() {
    deposit.setAmount(3000);
    assertEquals(3000, deposit.getAmount());
  }

  @Test
  void getRecipientName() {
    assertEquals(recipientName, deposit.getRecipientName());
  }

  @Test
  void setRecipientName() {
    Name newName = new Name("Alice", "Middle", "Smith");
    deposit.setRecipientName(newName);
    assertEquals(newName, deposit.getRecipientName());
  }

  @Test
  void getRecipientId() {
    assertEquals("123456ID", deposit.getRecipientId());
  }

  @Test
  void setRecipientId() {
    deposit.setRecipientId("654321ID");
    assertEquals("654321ID", deposit.getRecipientId());
  }

  @Test
  void testEquals_SameInstance() {
    assertTrue(deposit.equals(deposit));
  }

  @Test
  void testEquals_NullObject() {
    assertFalse(deposit.equals(null));
  }

  @Test
  void testEquals_DifferentClass() {
    assertFalse(deposit.equals(new Object()));
  }

  @Test
  void testEquals_EqualDeposits() {
    Deposit anotherDeposit = new Deposit(5000, recipientName, "123456ID");
    assertTrue(deposit.equals(anotherDeposit));
  }

  @Test
  void testEquals_DifferentAmount() {
    Deposit differentAmountDeposit = new Deposit(6000, recipientName, "123456789");
    assertFalse(deposit.equals(differentAmountDeposit));
  }

  @Test
  void testEquals_DifferentRecipientName() {
    Name differentName = new Name("Jane", "Middle","Doe");
    Deposit differentNameDeposit = new Deposit(5000, differentName, "123456789");
    assertFalse(deposit.equals(differentNameDeposit));
  }

  @Test
  void testEquals_DifferentRecipientId() {
    Deposit differentIdDeposit = new Deposit(5000, recipientName, "987654321");
    assertFalse(deposit.equals(differentIdDeposit));
  }

  @Test
  void testHashCode() {
    Deposit otherDeposit = new Deposit(5000, recipientName, "123456ID");
    assertEquals(deposit.hashCode(), otherDeposit.hashCode());
  }

  @Test
  void testToString() {
    String expected = "Deposit{" +
        "amount=5000, " +
        "recipientName=" + recipientName.toString() +
        ", recipientId='123456ID'" +
        '}';
    assertEquals(expected, deposit.toString());
  }
}
