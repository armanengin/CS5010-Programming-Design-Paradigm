package problem2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {

  private Menu menu;
  private List<String> meals;
  private List<String> desserts;
  private List<String> beverages;
  private List<String> drinks;

  @BeforeEach
  void setUp() {
    meals = Arrays.asList("Meal1", "Meal2");
    desserts = Arrays.asList("Dessert1", "Dessert2");
    beverages = Arrays.asList("Beverage1", "Beverage2");
    drinks = Arrays.asList("Drink1", "Drink2");
    menu = new Menu(meals, desserts, beverages, drinks);
  }

  @Test
  void getMeals() {
    assertEquals(meals, menu.getMeals());
  }

  @Test
  void setMeals() {
    List<String> newMeals = Arrays.asList("Meal3", "Meal4");
    menu.setMeals(newMeals);
    assertEquals(newMeals, menu.getMeals());
  }

  @Test
  void getDesserts() {
    assertEquals(desserts, menu.getDesserts());
  }

  @Test
  void setDesserts() {
    List<String> newDesserts = Arrays.asList("Dessert3", "Dessert4");
    menu.setDesserts(newDesserts);
    assertEquals(newDesserts, menu.getDesserts());
  }

  @Test
  void getBeverages() {
    assertEquals(beverages, menu.getBeverages());
  }

  @Test
  void setBeverages() {
    List<String> newBeverages = Arrays.asList("Beverage3", "Beverage4");
    menu.setBeverages(newBeverages);
    assertEquals(newBeverages, menu.getBeverages());
  }

  @Test
  void getDrinks() {
    assertEquals(drinks, menu.getDrinks());
  }

  @Test
  void setDrinks() {
    List<String> newDrinks = Arrays.asList("Drink3", "Drink4");
    menu.setDrinks(newDrinks);
    assertEquals(newDrinks, menu.getDrinks());
  }

  @Test
  void testEquals() {
    Menu sameMenu = new Menu(meals, desserts, beverages, drinks);
    assertEquals(menu, sameMenu);
  }

  @Test
  void testNotEquals() {
    Menu differentMenu = new Menu(
        Arrays.asList("Meal3", "Meal4"),
        Arrays.asList("Dessert3", "Dessert4"),
        Arrays.asList("Beverage3", "Beverage4"),
        Arrays.asList("Drink3", "Drink4")
    );
    assertNotEquals(menu, differentMenu);
  }

  @Test
  void testHashCode() {
    Menu sameMenu = new Menu(meals, desserts, beverages, drinks);
    assertEquals(menu.hashCode(), sameMenu.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "Menu{" +
        "meals=" + meals +
        ", desserts=" + desserts +
        ", beverages=" + beverages +
        ", drinks=" + drinks +
        '}';
    assertEquals(expectedString, menu.toString());
  }
}