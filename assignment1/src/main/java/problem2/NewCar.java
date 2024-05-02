package problem2;

import java.util.Objects;

/**
 * Represents a new car, extending the functionality of the Car class.
 * It includes additional information such as the number of available cars within a 50-mile radius.
 * This class inherits fields and methods from the Car class and adds a field to track the availability of similar cars nearby.
 *
 * @author Arman Engin Sucu
 */
public class NewCar extends Car{
  private Integer numOfAvailableCarsWithinFiftyMiles;

  /**
   * Constructs a new instance of NewCar with specified details.
   *
   * @param manufacturingYear the manufacturing year of the new car
   * @param makeAndModel the make and model of the new car
   * @param msrp the Manufacturer's Suggested Retail Price of the new car
   * @param numOfAvailableCarsWithinFiftyMiles the number of available cars of the same make and model within a 50-mile radius
   */
  public NewCar(Integer manufacturingYear, MakeModel makeAndModel, Double msrp,
      Integer numOfAvailableCarsWithinFiftyMiles) {
    super(manufacturingYear, makeAndModel, msrp);
    this.numOfAvailableCarsWithinFiftyMiles = numOfAvailableCarsWithinFiftyMiles;
  }

  /**
   * Gets the number of available cars of this make and model within a 50-mile radius.
   *
   * @return the number of available cars within 50 miles
   */
  public Integer getNumOfAvailableCarsWithinFiftyMiles() {
    return numOfAvailableCarsWithinFiftyMiles;
  }

  /**
   * Sets the number of available cars of this make and model within a 50-mile radius.
   *
   * @param numOfAvailableCarsWithinFiftyMiles the number of available cars within 50 miles
   */
  public void setNumOfAvailableCarsWithinFiftyMiles(Integer numOfAvailableCarsWithinFiftyMiles) {
    this.numOfAvailableCarsWithinFiftyMiles = numOfAvailableCarsWithinFiftyMiles;
  }

  /**
   * Compares this NewCar with the specified object for equality.
   *
   * @param o the object to be compared for equality with this NewCar
   * @return true if the specified object is equal to this NewCar
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NewCar newCar)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(getNumOfAvailableCarsWithinFiftyMiles(),
        newCar.getNumOfAvailableCarsWithinFiftyMiles());
  }

  /**
   * Returns the hash code value for this NewCar.
   *
   * @return the hash code value for this NewCar
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getNumOfAvailableCarsWithinFiftyMiles());
  }

  /**
   * Returns a string representation of this NewCar.
   *
   * @return a string representation of this NewCar
   */
  @Override
  public String toString() {
    return "NewCar{" +
        "numOfAvailableCarsWithinFiftyMiles=" + numOfAvailableCarsWithinFiftyMiles +
        '}';
  }
}
