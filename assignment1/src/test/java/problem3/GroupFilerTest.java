package problem3;

import static java.lang.Math.max;
import static org.junit.jupiter.api.Assertions.*;
import static problem3.GroupFiler.CHILDCARE_DEDUCTION_AMOUNT;
import static problem3.GroupFiler.MAX_INCOME_FOR_CHILDCARE_DEDUCTION;
import static problem3.GroupFiler.MAX_RETIREMENT_AND_HEALTH_DEDUCTION;
import static problem3.GroupFiler.MIN_EXPENSES_FOR_CHILDCARE_DEDUCTION;
import static problem3.GroupFiler.RETIREMENT_AND_HEALTH_DEDUCTION_RATIO;
import static problem3.GroupFiler.TAXABLE_INCOME_LOWER_RATIO;
import static problem3.GroupFiler.TAXABLE_INCOME_THRESHOLD;
import static problem3.GroupFiler.TAXABLE_INCOME_UPPER_RATIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GroupFilerTest {
  private HeadOfHousehold headOfHousehold;
  private MarriedJoint marriedJoint;
  private MarriedSeperate marriedSeparate;

  @BeforeEach
  void setUp() {
    // Initialize HeadOfHousehold object before each test
    headOfHousehold = new HeadOfHousehold(new ContactInfo(new Name("John", "Doe"), "123 Main St", "123-456-7890", "john.doe@example.com"),
        100000.0, 15000.0, 8000.0, 5000.0, 2000.0, 10000.0, 5000.0, 3000.0, 2,
        1, 5000.0, 2000.0);

    // Initialize MarriedJoint object before each test
    marriedJoint = new MarriedJoint(new ContactInfo(new Name("Jane", "Smith"), "456 Elm St", "987-654-3210", "jane.smith@example.com"),
        120000.0, 18000.0, 9000.0, 6000.0, 3000.0, 12000.0, 6000.0, 4000.0, 2,
        2, 6000.0, 2500.0);

    // Initialize MarriedSeparate object before each test
    marriedSeparate = new MarriedSeperate(new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com"),
        80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1,
        0, 3000.0, 1000.0);
  }

  @Test
  void testHeadOfHouseholdSetNumOfDependents() {
    int newNumOfDependents = 3;
    headOfHousehold.setNumOfDependents(newNumOfDependents);
    assertEquals(newNumOfDependents, headOfHousehold.getNumOfDependents());
  }

  @Test
  void testHeadOfHouseholdGetNumOfDependents() {
    assertEquals(2, headOfHousehold.getNumOfDependents());
  }

  @Test
  void testMarriedJointSetNumOfMinorChildren() {
    int newNumOfMinorChildren = 3;
    marriedJoint.setNumOfMinorChildren(newNumOfMinorChildren);
    assertEquals(newNumOfMinorChildren, marriedJoint.getNumOfMinorChildren());
  }

  @Test
  void testMarriedJointGetNumOfMinorChildren() {
    assertEquals(2, marriedJoint.getNumOfMinorChildren());
  }

  @Test
  void testMarriedSeparateSetChildcareExpenses() {
    Double newChildcareExpenses = 4000.0;
    marriedSeparate.setChildcareExpenses(newChildcareExpenses);
    assertEquals(newChildcareExpenses, marriedSeparate.getChildcareExpenses());
  }

  @Test
  void testMarriedSeparateGetChildcareExpenses() {
    assertEquals(3000.0, marriedSeparate.getChildcareExpenses());
  }

  @Test
  void testHeadOfHousehold_Equals_IdenticalInstance() {

    // Create an identical HeadOfHousehold object for comparison
    HeadOfHousehold anotherHeadOfHousehold = new HeadOfHousehold(new ContactInfo(new Name("John", "Doe"), "123 Main St", "123-456-7890", "john.doe@example.com"),
        100000.0, 15000.0, 8000.0, 5000.0, 2000.0, 10000.0, 5000.0, 3000.0, 2,
        1, 5000.0, 2000.0);

    // Test equals method with an identical HeadOfHousehold object
    assertTrue(headOfHousehold.equals(anotherHeadOfHousehold));
  }

  @Test
  void calculateTaxes() {
    // Create a GroupFiler object for testing
    ContactInfo contactInfo = new ContactInfo(new Name("John", "Doe"), "123 Main St", "123-456-7890", "john.doe@example.com");
    GroupFiler groupFiler = new MarriedJoint(contactInfo, 100000.0, 15000.0, 8000.0, 5000.0, 2000.0, 10000.0, 5000.0, 3000.0, 2, 1, 5000.0, 2000.0);

    // Calculate taxes and verify the result
    Double expectedTax = 10548.75; // Calculated value based on provided data
    assertEquals(expectedTax, groupFiler.calculateTaxes());
  }

  @Test
  void calculateRetirementAndHealthDeduction() {
    // Create a GroupFiler object for testing
    ContactInfo contactInfo = new ContactInfo(new Name("Jane", "Smith"), "456 Elm St", "987-654-3210", "jane.smith@example.com");
    GroupFiler groupFiler = new MarriedJoint(contactInfo, 120000.0, 18000.0, 9000.0, 6000.0, 3000.0, 12000.0, 6000.0, 4000.0, 2, 2, 6000.0, 2500.0);

    // Calculate retirement and health deduction and verify the result
    Double expectedDeduction = 11700.0; // Calculated value based on provided data
    assertEquals(expectedDeduction, groupFiler.calculateRetirementAndHealthDeduction());
  }

  @Test
  void getChildcareDeduction() {
    // Create a GroupFiler object for testing with the basic taxable income set indirectly through the constructor
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler = new MarriedSeperate(contactInfo, 85000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    // Calculate childcare deduction and verify the result
    Double expectedDeduction = 0.0; // Expected childcare deduction amount
    assertEquals(expectedDeduction, groupFiler.getChildcareDeduction());
  }

  @Test
  void testMarriedSeparateSetDependentCareExpenses() {
    Double newDependentCareExpenses = 2000.0;
    marriedSeparate.setDependentCareExpenses(newDependentCareExpenses);
    assertEquals(newDependentCareExpenses, marriedSeparate.getDependentCareExpenses());
  }

  @Test
  void testEquals_SameInstance() {
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    assertTrue(groupFiler.equals(groupFiler));
  }

  @Test
  void testEqualsNullObject() {
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    assertFalse(groupFiler.equals(null));
  }

  @Test
  void testEqualsDifferentClass() {
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    assertFalse(groupFiler.equals(new Object()));
  }

  @Test
  void testEqualsEqualObjects() {
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    assertTrue(groupFiler1.equals(groupFiler2));
  }

  @Test
  void testEqualsDifferentObjects() {
    ContactInfo contactInfo1 = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    ContactInfo contactInfo2 = new ContactInfo(new Name("Bob", "Smith"), "123 Main St", "444-555-6666", "bob.smith@example.com");

    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo1, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo2, 90000.0, 13000.0, 8000.0, 5000.0, 1600.0, 9000.0, 5000.0, 2500.0, 2, 1, 4000.0, 1200.0);

    assertFalse(groupFiler1.equals(groupFiler2));
  }

  @Test
  void testEqualsDifferentNumOfDependents() {
    // Create two GroupFiler objects with different numbers of dependents for testing
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 2, 0, 3000.0, 1000.0);

    assertFalse(groupFiler1.equals(groupFiler2));
  }

  @Test
  void testEqualsDifferentChildcareExpenses() {
    // Create two GroupFiler objects with different childcare expenses for testing
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 4000.0, 1000.0);

    assertFalse(groupFiler1.equals(groupFiler2));
  }

  @Test
  void testEqualsDifferentDependentCareExpenses() {
    // Create two GroupFiler objects with different dependent care expenses for testing
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 2000.0);

    assertFalse(groupFiler1.equals(groupFiler2));
  }

  @Test
  void testEqualsSameChildcareExpenses() {
    // Create two GroupFiler objects with the same childcare expenses for testing
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    assertTrue(groupFiler1.equals(groupFiler2));
  }

  @Test
  void testEqualsSameDependentCareExpenses() {
    // Create two GroupFiler objects with the same dependent care expenses for testing
    ContactInfo contactInfo = new ContactInfo(new Name("Alice", "Johnson"), "789 Oak St", "111-222-3333", "alice.johnson@example.com");
    GroupFiler groupFiler1 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);
    GroupFiler groupFiler2 = new MarriedSeperate(contactInfo, 80000.0, 12000.0, 7000.0, 4000.0, 1500.0, 8000.0, 4000.0, 2000.0, 1, 0, 3000.0, 1000.0);

    assertTrue(groupFiler1.equals(groupFiler2));
  }
  @Test
  void testMarriedJointHashCode() {
    // Create an identical MarriedJoint object for comparison
    MarriedJoint anotherMarriedJoint = new MarriedJoint(new ContactInfo(new Name("Jane", "Smith"), "456 Elm St", "987-654-3210", "jane.smith@example.com"),
        120000.0, 18000.0, 9000.0, 6000.0, 3000.0, 12000.0, 6000.0, 4000.0, 2,
        2, 6000.0, 2500.0);

    // Test hash code method in MarriedJoint
    assertEquals(marriedJoint.hashCode(), anotherMarriedJoint.hashCode());
  }

  @Test
  void testMarriedSeparateToString() {
    // Test toString method in MarriedSeparate
    String expectedString = "GroupFiler{" +
        "numOfDependents=" + 1 +
        ", numOfMinorChildren=" + 0 +
        ", childcareExpenses=" + 3000.0 +
        ", dependentCareExpenses=" + 1000.0 +
        '}';
    assertEquals(expectedString, marriedSeparate.toString());
  }
}