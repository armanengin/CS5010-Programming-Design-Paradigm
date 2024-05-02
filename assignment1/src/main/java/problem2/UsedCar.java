package problem2;

import java.util.Objects;

/**
 * Represents a used car, extending the functionalities of the Car class.
 * This class includes additional information specific to used cars such as mileage,
 * number of previous owners, and number of minor traffic accidents.
 *
 * @author Arman Engin Sucu
 */
public class UsedCar extends Car{
  private Integer mileage;
  private Integer numOfPrevOwner;
  private Integer numOfMinorTrafficAcc;

  /**
   * Constructs a new instance of UsedCar with specified details.
   *
   * @param manufacturingYear the manufacturing year of the car
   * @param makeAndModel the make and model of the car
   * @param msrp the Manufacturer's Suggested Retail Price of the car
   * @param mileage the mileage of the used car
   * @param numOfPrevOwner the number of previous owners of the used car
   * @param numOfMinorTrafficAcc the number of minor traffic accidents the used car has been involved in
   */
  public UsedCar(Integer manufacturingYear, MakeModel makeAndModel, Double msrp, Integer mileage,
      Integer numOfPrevOwner, Integer numOfMinorTrafficAcc) {
    super(manufacturingYear, makeAndModel, msrp);
    this.mileage = mileage;
    this.numOfPrevOwner = numOfPrevOwner;
    this.numOfMinorTrafficAcc = numOfMinorTrafficAcc;
  }

  /**
   * Gets the mileage of the used car.
   *
   * @return the mileage of the car
   */
  public Integer getMileage() {
    return mileage;
  }

  /**
   * Sets the mileage of the used car.
   *
   * @param mileage the mileage value to set
   */
  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  /**
   * Gets the number of previous owners of the used car.
   *
   * @return the number of previous owners
   */
  public Integer getNumOfPrevOwner() {
    return numOfPrevOwner;
  }

  /**
   * Sets the number of previous owners of the used car.
   *
   * @param numOfPrevOwner the number of previous owners to set
   */
  public void setNumOfPrevOwner(Integer numOfPrevOwner) {
    this.numOfPrevOwner = numOfPrevOwner;
  }

  /**
   * Gets the number of minor traffic accidents the used car has been involved in.
   *
   * @return the number of minor traffic accidents
   */
  public Integer getNumOfMinorTrafficAcc() {
    return numOfMinorTrafficAcc;
  }

  /**
   * Sets the number of minor traffic accidents the used car has been involved in.
   *
   * @param numOfMinorTrafficAcc the number of minor traffic accidents to set
   */
  public void setNumOfMinorTrafficAcc(Integer numOfMinorTrafficAcc) {
    this.numOfMinorTrafficAcc = numOfMinorTrafficAcc;
  }

  /**
   * Compares this UsedCar with the specified object for equality.
   *
   * @param o the object to be compared for equality with this UsedCar
   * @return true if the specified object is equal to this UsedCar
   */

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UsedCar usedCar)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(mileage, usedCar.mileage) && Objects.equals(
        numOfPrevOwner, usedCar.numOfPrevOwner) && Objects.equals(numOfMinorTrafficAcc,
        usedCar.numOfMinorTrafficAcc);
  }

  /**
   * Returns the hash code value for this UsedCar.
   *
   * @return the hash code value for this UsedCar
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), mileage, numOfPrevOwner, numOfMinorTrafficAcc);
  }

  /**
   * Returns a string representation of this UsedCar.
   *
   * @return a string representation of this UsedCar
   */
  @Override
  public String toString() {
    return "UsedCar{" +
        "mileage=" + mileage +
        ", numOfPrevOwner=" + numOfPrevOwner +
        ", numOfMinorTrafficAcc=" + numOfMinorTrafficAcc +
        '}';
  }
}
