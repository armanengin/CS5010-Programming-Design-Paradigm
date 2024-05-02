package problem2;

import java.util.List;
import java.util.Objects;

/**
 * Represents a menu in a restaurant with various categories of items including meals, desserts, beverages, and drinks.
 * Provides methods to get and set items in each category.
 *
 * @author Arman Engin Sucu
 */
public class Menu {
  private List<String> meals;
  private List<String> desserts;
  private List<String> beverages;
  private List<String> drinks;

  /**
   * Constructs a new Menu with lists of meals, desserts, beverages, and drinks.
   *
   * @param meals     A list of meal items.
   * @param desserts  A list of dessert items.
   * @param beverages A list of beverage items.
   * @param drinks    A list of drink items.
   */
  public Menu(List<String> meals, List<String> desserts, List<String> beverages,
      List<String> drinks) {
    this.meals = meals;
    this.desserts = desserts;
    this.beverages = beverages;
    this.drinks = drinks;
  }

  /**
   * Gets the list of meal items.
   *
   * @return The list of meals.
   */
  public List<String> getMeals() {
    return meals;
  }

  /**
   * Sets the list of meal items.
   *
   * @param meals The new list of meals.
   */
  public void setMeals(List<String> meals) {
    this.meals = meals;
  }

  /**
   * Gets the list of dessert items.
   *
   * @return The list of desserts.
   */
  public List<String> getDesserts() {
    return desserts;
  }

  /**
   * Sets the list of dessert items.
   *
   * @param desserts The new list of desserts.
   */
  public void setDesserts(List<String> desserts) {
    this.desserts = desserts;
  }

  /**
   * Gets the list of beverage items.
   *
   * @return The list of beverages.
   */
  public List<String> getBeverages() {
    return beverages;
  }

  /**
   * Sets the list of beverage items.
   *
   * @param beverages The new list of beverages.
   */
  public void setBeverages(List<String> beverages) {
    this.beverages = beverages;
  }

  /**
   * Gets the list of drink items.
   *
   * @return The list of drinks.
   */
  public List<String> getDrinks() {
    return drinks;
  }

  /**
   * Sets the list of drink items.
   *
   * @param drinks The new list of drinks.
   */
  public void setDrinks(List<String> drinks) {
    this.drinks = drinks;
  }

  /**
   * Compares this menu to the specified object for equality.
   * The result is true if and only if the argument is not null and is a Menu object that
   * has the same lists of meals, desserts, beverages, and drinks as this object.
   *
   * @param o The object to compare this Menu against.
   * @return true if the given object represents a Menu equivalent to this menu, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Menu menu)) {
      return false;
    }
    return Objects.equals(getMeals(), menu.getMeals()) && Objects.equals(getDesserts(), menu.getDesserts()) &&
        Objects.equals(getBeverages(), menu.getBeverages()) && Objects.equals(getDrinks(), menu.getDrinks());
  }

  /**
   * Returns a hash code value for the menu.
   * This method is supported for the benefit of hash tables such as those provided by HashMap.
   *
   * @return A hash code value for this menu.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getMeals(), getDesserts(), getBeverages(), getDrinks());
  }

  /**
   * Returns a string representation of the Menu object.
   * This string includes lists of meals, desserts, beverages, and drinks.
   *
   * @return A string representation of this Menu.
   */
  @Override
  public String toString() {
    return "Menu{" +
        "meals=" + meals +
        ", desserts=" + desserts +
        ", beverages=" + beverages +
        ", drinks=" + drinks +
        '}';
  }
}

