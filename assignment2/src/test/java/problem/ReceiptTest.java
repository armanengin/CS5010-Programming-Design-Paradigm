package problem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReceiptTest {
  private Receipt receipt;
  private Product product1;
  private Product product2;
  private Product product3;
  @BeforeEach
  void setUp() {
    product1 = new Cheese("BrandA", "Cheddar", 5.0, 0, 10.0);
    product2 = new PaperTowel("BrandB", "Premium Towel", 6.0, 2);
    product3 = new Beer("BrandC", "Lager", 3.5, 21, 12.0);

    List<Product> receivedProducts = Arrays.asList(product1, product2);
    List<Product> outOfStockProducts = Arrays.asList(product3);
    List<Product> ageRestrictedProducts = Arrays.asList();

    receipt = new Receipt(11.0, receivedProducts, outOfStockProducts, ageRestrictedProducts);  }

  @Test
  void getTotalPricePaid() {
    assertEquals(11.0, receipt.getTotalPricePaid(), "Total price paid should match the expected value.");
  }

  @Test
  void getProductsCustomerReceived() {
    List<Product> expectedProducts = Arrays.asList(product1, product2);
    assertEquals(expectedProducts, receipt.getProductsCustomerReceived(), "Received products should match the expected list.");
  }

  @Test
  void getProductsOutOfStockAndNotSubstituted() {
    List<Product> expectedProducts = Arrays.asList(product3);
    assertEquals(expectedProducts, receipt.getProductsOutOfStockAndNotSubstituted(), "Out of stock products should match the expected list.");
  }

  @Test
  void getProductsRemovedByAgeReq() {
    List<Product> expectedProducts = Arrays.asList();
    assertEquals(expectedProducts, receipt.getProductsRemovedByAgeReq(), "Age restricted products should match the expected list.");
  }

  @Test
  void setTotalPricePaid() {
    Receipt receipt = new Receipt(0.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    receipt.setTotalPricePaid(15.0);
    assertEquals(15.0, receipt.getTotalPricePaid(), "Total price paid should be updated to the new value.");
  }

  @Test
  void setProductsCustomerReceived() {
    Receipt receipt = new Receipt(0.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    List<Product> newReceivedProducts = Arrays.asList(new Cheese("BrandX", "Blue Cheese", 7.0, 0, 5.0));
    receipt.setProductsCustomerReceived(newReceivedProducts);
    assertEquals(newReceivedProducts, receipt.getProductsCustomerReceived(), "Received products should be updated to the new list.");
  }

  @Test
  void setProductsOutOfStockAndNotSubstituted() {
    Receipt receipt = new Receipt(0.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    List<Product> newOutOfStockProducts = Arrays.asList(new PaperTowel("BrandY", "Eco Towel", 4.0, 2));
    receipt.setProductsOutOfStockAndNotSubstituted(newOutOfStockProducts);
    assertEquals(newOutOfStockProducts, receipt.getProductsOutOfStockAndNotSubstituted(), "Out of stock products should be updated to the new list.");
  }

  @Test
  void setProductsRemovedByAgeReq() {
    Receipt receipt = new Receipt(0.0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    List<Product> newAgeRestrictedProducts = Arrays.asList(new Beer("BrandZ", "Stout", 8.0, 18, 12.0));
    receipt.setProductsRemovedByAgeReq(newAgeRestrictedProducts);
    assertEquals(newAgeRestrictedProducts, receipt.getProductsRemovedByAgeReq(), "Age restricted products should be updated to the new list.");
  }

  @Test
  void testEqualsWithSameObject() {
    Receipt receipt = createSampleReceipt();
    assertEquals(receipt, receipt, "Receipt should be equal to itself");
  }

  @Test
  void testEqualsWithNull() {
    Receipt receipt = createSampleReceipt();
    assertNotEquals(null, receipt, "Receipt should not be equal to null");
  }

  @Test
  void testEqualsWithDifferentClassObject() {
    Receipt receipt = createSampleReceipt();
    Object differentClassObject = new Object();
    assertNotEquals(receipt, differentClassObject, "Receipt should not be equal to an object of a different class");
  }

  @Test
  void testEqualsWithDifferentTotalPricePaid() {
    Receipt receipt1 = createSampleReceipt();
    Receipt receipt2 = new Receipt(20.0, receipt1.getProductsCustomerReceived(),
        receipt1.getProductsOutOfStockAndNotSubstituted(),
        receipt1.getProductsRemovedByAgeReq());
    assertNotEquals(receipt1, receipt2, "Receipts with different total prices should not be equal");
  }

  @Test
  void testEqualsWithDifferentProductsCustomerReceived() {
    Receipt receipt1 = createSampleReceipt();
    List<Product> differentProducts = new ArrayList<>();
    // Add different products or modify the list
    Receipt receipt2 = new Receipt(receipt1.getTotalPricePaid(), differentProducts,
        receipt1.getProductsOutOfStockAndNotSubstituted(),
        receipt1.getProductsRemovedByAgeReq());
    assertNotEquals(receipt1, receipt2, "Receipts with different received products should not be equal");
  }

  @Test
  void testEqualsWithDifferentProductsOutOfStockAndNotSubstituted() {
    Receipt receipt1 = createSampleReceipt();
    List<Product> differentProducts = new ArrayList<>();
    // Add different products or modify the list
    Receipt receipt2 = new Receipt(receipt1.getTotalPricePaid(), receipt1.getProductsCustomerReceived(),
        differentProducts, receipt1.getProductsRemovedByAgeReq());
    assertNotEquals(receipt1, receipt2, "Receipts with different out-of-stock products should not be equal");
  }


  // Utility method to create a sample receipt
  private Receipt createSampleReceipt() {
    // Create sample products
    Product product1 = new Cheese("BrandA", "Cheddar", 5.0, 0, 10.0);
    Product product2 = new PaperTowel("BrandB", "Premium Towel", 6.0, 4);

    // Create lists for different categories in the receipt
    List<Product> productsCustomerReceived = new ArrayList<>();
    productsCustomerReceived.add(product1);

    List<Product> productsOutOfStockAndNotSubstituted = new ArrayList<>();
    productsOutOfStockAndNotSubstituted.add(product2);

    List<Product> productsRemovedByAgeReq = new ArrayList<>();
    // Add any age-restricted product if needed

    // Set a total price for the receipt
    double totalPrice = 5.0; // Assuming only product1 was bought

    // Create and return the Receipt object
    return new Receipt(totalPrice, productsCustomerReceived,
        productsOutOfStockAndNotSubstituted, productsRemovedByAgeReq);
  }

  @Test
  void testHashCode() {
    Receipt anotherReceipt = new Receipt(11.0, Arrays.asList(product1, product2), Arrays.asList(product3), Arrays.asList());
    assertEquals(receipt.hashCode(), anotherReceipt.hashCode(), "Hash code should match for identical receipts.");
  }

  @Test
  void testToString() {
    String expectedString = "Receipt{totalPricePaid=11.0," +""
        + " productsCustomerReceived=[Grocery{manufacturer='BrandA'," +
        " productName='Cheddar', price=5.0, minAge=0, weight=10.0}," +
        " Household{manufacturer='BrandB', productName='Premium Towel'," +
        " price=6.0, minAge=0, numOfUnits=2}]," +
        " productsOutOfStockAndNotSubstituted=[Grocery{manufacturer='BrandC'," +
        " productName='Lager', price=3.5, minAge=21, weight=12.0}]," +
        " productsRemovedByAgeReq=[]}";
    assertEquals(expectedString, receipt.toString(), "String representation should match the expected format.");
  }
}