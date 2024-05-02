package problem1;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FrequentFlyerTest {
  private FrequentFlyer testFlyer;
  private FrequentFlyer recipientFlyer;
  private String id;
  private String recipientId;
  private Name name;
  private Name recipientName;
  private String email;
  private String recipientEmail;
  private MilesBalance expectedMiles;
  private int totalMilesAmount;
  @BeforeEach
  void setUp() {
    id = "ahdgyu8749lo";
    name = new Name("Taylor", "J", "Roy");
    email = "taylor@gmail.com";
    testFlyer = new FrequentFlyer(id, name, email);
    totalMilesAmount = 3000;
    testFlyer.getMiles().setTotalMiles(totalMilesAmount);
    expectedMiles = testFlyer.getMiles();

    recipientId = "bahdgyu8749l";
    recipientName = new Name("Max", "T", "Roy");
    recipientEmail = "Max@gmail.com";
    recipientFlyer = new FrequentFlyer(recipientId, recipientName, recipientEmail);
    FrequentFlyerSystem.addFrequentFlyer(testFlyer);
    FrequentFlyerSystem.addFrequentFlyer(recipientFlyer);
  }

  @AfterEach
  void afterEachTest() {
    FrequentFlyerSystem.clearSystem(); // Assuming this method clears all flyers from the system
  }
  @Test
  void getId() {
    assertEquals(id, testFlyer.getId());
  }

  @Test
  void setId() {
    String newId = "bhdgyu8749lo";
    testFlyer.setId(newId);
    assertEquals(newId, testFlyer.getId());
    testFlyer.setId(id);
  }
  @Test
  void getName() {
    assertEquals(name, testFlyer.getName());
  }

  @Test
  void setName() {
    testFlyer.setName(recipientName);
    assertEquals(recipientName, testFlyer.getName());
    testFlyer.setName(name);
  }

  @Test
  void getEmail() {
    assertEquals(email, testFlyer.getEmail());
  }

  @Test
  void setEmail() {
    String newEmail = "taylorSecond@gmail.com";
    testFlyer.setEmail(newEmail);
    assertEquals(newEmail, testFlyer.getEmail());
  }

  @Test
  void getMiles() {
    assertEquals(expectedMiles, testFlyer.getMiles());
  }

  @Test
  void setMiles() {
    expectedMiles.setTotalMiles(5000);
    testFlyer.setMiles(expectedMiles);
    assertEquals(expectedMiles, testFlyer.getMiles());
  }

  @Test
  void transferMiles() {
    Deposit deposit = new Deposit(1700, recipientName, recipientId);
    testFlyer.transferMiles(deposit);
    assertTrue(testFlyer.getMiles().getTotalMiles() == 1300 &&
        recipientFlyer.getMiles().getTotalMiles() == 1700);
  }

  @Test
  void transferMilesRecipientCheckVersion() {
    Deposit deposit = new Deposit(1700, recipientName, "1");
    Exception e = assertThrows(IllegalArgumentException.class, () ->testFlyer.transferMiles(deposit));
    String expectedMessage = "The recipient for transfer doesn't exist!";
    String actualMessage = e.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void transferMilesIdNameCheckVersion() {
    Deposit deposit = new Deposit(1700, name, recipientId);
    Exception e = assertThrows(IllegalArgumentException.class, () ->testFlyer.transferMiles(deposit));
    String expectedMessage = "The ID and name of the recipient don't match!";
    String actualMessage = e.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void transferMilesEnoughAmountCheck() {
    Deposit deposit = new Deposit(6000, recipientName, recipientId);
    Exception e = assertThrows(IllegalArgumentException.class, () ->testFlyer.transferMiles(deposit));
    String expectedMessage = "There is not enough miles to transfer!";
    String actualMessage = e.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }

  @Test
  void mileAmountCheckForDeposit() {
    Deposit deposit = new Deposit(6000, recipientName, recipientId);
    boolean isTransferDoable = testFlyer.mileAmountCheckForDeposit(deposit);
    assertFalse(isTransferDoable);
  }


  @Test
  void transferMiles_SenderMilesAfterTransfer() {
    Deposit deposit = new Deposit(1000, recipientName, recipientId);
    int originalSenderMiles = testFlyer.getMiles().getTotalMiles();
    testFlyer.transferMiles(deposit);
    assertEquals(originalSenderMiles - 1000, testFlyer.getMiles().getTotalMiles());
  }

  @Test
  void transferMiles_RecipientMilesAfterTransfer() {
    Deposit deposit = new Deposit(1000, recipientName, recipientId);
    int originalRecipientMiles = recipientFlyer.getMiles().getTotalMiles();
    testFlyer.transferMiles(deposit);
    assertEquals(originalRecipientMiles + 1000, recipientFlyer.getMiles().getTotalMiles());
  }

  @Test
  void transferMilesWithInvalidDepositAmount() {
    Deposit deposit = new Deposit(4000, recipientName, recipientId); // Exceeds sender's balance

    assertThrows(IllegalArgumentException.class, () -> testFlyer.transferMiles(deposit));
  }

  @Test
  void constructorWithInvalidIdLength() {
    String shortId = "123";
    assertThrows(IllegalArgumentException.class, () -> new FrequentFlyer(shortId, name, email));
  }

  @Test
  void getAndSetTotalMiles() {
    int newMiles = 5000;
    testFlyer.getMiles().setTotalMiles(newMiles);
    assertEquals(newMiles, testFlyer.getMiles().getTotalMiles());
  }

  @Test
  void transferMilesToNonexistentRecipient() {
    Deposit deposit = new Deposit(1000, recipientName, "nonexistentId");
    assertThrows(IllegalArgumentException.class, () -> testFlyer.transferMiles(deposit));
  }

  @Test
  void mileAmountCheckForInsufficientDeposit() {
    Deposit largeDeposit = new Deposit(4000, recipientName, recipientId);
    assertFalse(testFlyer.mileAmountCheckForDeposit(largeDeposit));
  }

  @Test
  void toStringRepresentation() {
    String expectedString = "FrequentFlyer{id='" + id + "', name=" + name +
        ", email='" + email + "', miles=" + testFlyer.getMiles() + '}';
    assertEquals(expectedString, testFlyer.toString());
  }

  @Test
  void constructorException(){
    final int ID_SIZE = 12;
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      new FrequentFlyer("1234567890", name, email);
    });
    String expectedMessage = "Id has to be " + ID_SIZE + " characters!";
    String actualMessage = e.getMessage();
    assertEquals(actualMessage, expectedMessage);
  }
  @Test
  void testEquals() {
    FrequentFlyer newFlyer = new FrequentFlyer(id, name, email);
    newFlyer.setMiles(testFlyer.getMiles());
    assertEquals(testFlyer, newFlyer);
  }

  @Test
  void testHashCode() {
    FrequentFlyer newFlyer = new FrequentFlyer(id, name, email);
    newFlyer.setMiles(testFlyer.getMiles());
    assertEquals(testFlyer.hashCode(), newFlyer.hashCode());
  }
}
