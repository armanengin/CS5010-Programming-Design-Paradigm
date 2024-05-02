package problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class RestaurantTest {

  private Restaurant restaurant;
  private Address address;
  private Menu menu;

  @BeforeEach
  void setUp() {
    address = new Address("123 Main St", "Springfield", "12345", "State", "Country");
    menu = new Menu(
        Arrays.asList("Meal 1", "Meal 2"),
        Arrays.asList("Dessert 1", "Dessert 2"),
        Arrays.asList("Beverage 1", "Beverage 2"),
        Arrays.asList("Drink 1", "Drink 2")
    );
    restaurant = new Restaurant(address, menu, true);
  }

  @Test
  void getAddress() {
    assertEquals(address, restaurant.getAddress());
  }

  @Test
  void setAddress() {
    Address newAddress = new Address("456 Main St", "Springfield", "67890", "State", "Country");
    restaurant.setAddress(newAddress);
    assertEquals(newAddress, restaurant.getAddress());
  }

  @Test
  void getMenu() {
    assertEquals(menu, restaurant.getMenu());
  }

  @Test
  void setMenu() {
    Menu newMenu = new Menu(
        Arrays.asList("New Meal 1", "New Meal 2"),
        Arrays.asList("New Dessert 1", "New Dessert 2"),
        Arrays.asList("New Beverage 1", "New Beverage 2"),
        Arrays.asList("New Drink 1", "New Drink 2")
    );
    restaurant.setMenu(newMenu);
    assertEquals(newMenu, restaurant.getMenu());
  }

  @Test
  void isOpen() {
    assertTrue(restaurant.isOpen());
  }

  @Test
  void setOpen() {
    restaurant.setOpen(false);
    assertFalse(restaurant.isOpen());
  }

  @Test
  void testEquals() {
    Restaurant anotherRestaurant = new Restaurant(address, menu, true);
    assertEquals(restaurant, anotherRestaurant);
  }

  @Test
  void testNotEquals() {
    Restaurant anotherRestaurant = new Restaurant(
        new Address("456 Main St", "Springfield", "67890", "State", "Country"),
        menu, true
    );
    assertNotEquals(restaurant, anotherRestaurant);
  }

  @Test
  void testHashCode() {
    Restaurant anotherRestaurant = new Restaurant(address, menu, true);
    assertEquals(restaurant.hashCode(), anotherRestaurant.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Restaurant{" +
        "address=" + address +
        ", menu=" + menu +
        ", isOpen=" + true +
        '}';
    assertEquals(expectedString, restaurant.toString());
  }
}