package problem2;

import java.util.Objects;

/**
 * Represents a restaurant with a physical address, a menu of items, and an operational status.
 * Provides methods to get and set the restaurant's address, menu, and open status.
 *
 * @author Arman Engin Sucu
 */
public class Restaurant {
  private Address address;
  private Menu menu;
  private boolean isOpen;

  /**
   * Constructs a new Restaurant with the specified address, menu, and open status.
   *
   * @param address The physical address of the restaurant.
   * @param menu    The menu of food items offered by the restaurant.
   * @param isOpen  The open status of the restaurant.
   */
  public Restaurant(Address address, Menu menu, boolean isOpen) {
    this.address = address;
    this.menu = menu;
    this.isOpen = isOpen;
  }

  /**
   * Gets the address of the restaurant.
   *
   * @return The Address of the restaurant.
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Sets the address of the restaurant.
   *
   * @param address The new Address of the restaurant.
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Gets the menu of the restaurant.
   *
   * @return The Menu of the restaurant.
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Sets the menu of the restaurant.
   *
   * @param menu The new Menu of the restaurant.
   */
  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  /**
   * Checks whether the restaurant is open.
   *
   * @return The open status of the restaurant.
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * Sets the open status of the restaurant.
   *
   * @param open The new open status of the restaurant.
   */
  public void setOpen(boolean open) {
    isOpen = open;
  }

  /**
   * Compares this restaurant to the specified object for equality.
   * The result is true if and only if the argument is not null and is a Restaurant object that
   * has the same address, menu, and open status as this object.
   *
   * @param o The object to compare this Restaurant against.
   * @return true if the given object represents a Restaurant equivalent to this restaurant, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Restaurant that)) {
      return false;
    }
    return isOpen() == that.isOpen() && Objects.equals(getAddress(), that.getAddress())
        && Objects.equals(getMenu(), that.getMenu());
  }

  /**
   * Returns a hash code value for the restaurant.
   * This method is supported for the benefit of hash tables such as those provided by HashMap.
   *
   * @return A hash code value for this restaurant.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getAddress(), getMenu(), isOpen());
  }

  /**
   * Returns a string representation of the Restaurant object.
   * This string includes the address, menu, and open status of the restaurant.
   *
   * @return A string representation of this Restaurant.
   */
  @Override
  public String toString() {
    return "Restaurant{" +
        "address=" + address +
        ", menu=" + menu +
        ", isOpen=" + isOpen +
        '}';
  }
}

