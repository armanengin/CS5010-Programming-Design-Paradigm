package problem2;

import java.util.Objects;

/**
 * Represents a boat with specific properties such as length, number of passengers,
 * and propulsion type, in addition to the properties inherited from the Vessel class.
 * <p>
 * This class extends the Vessel class and adds boat-specific features, providing
 * methods to access and modify these properties. It also includes overridden methods
 * for equality, hash code generation, and string representation.
 * </p>
 *
 * @author Arman Engin Sucu
 */
public class Boat extends Vessel {
  private Float length;
  private Integer numOfPassengers;
  private PropulsionType propulsionType;

  /**
   * Constructs a new Boat instance with specified manufacturing year, make and model,
   * MSRP, length, number of passengers, and propulsion type.
   *
   * @param manufacturingYear the manufacturing year of the boat
   * @param makeAndModel the make and model of the boat
   * @param msrp the manufacturer suggested retail price of the boat
   * @param length the length of the boat
   * @param numOfPassengers the number of passengers the boat can accommodate
   * @param propulsionType the boat's propulsion type
   */
  public Boat(Integer manufacturingYear, MakeModel makeAndModel, Double msrp, Float length,
      Integer numOfPassengers, PropulsionType propulsionType) {
    super(manufacturingYear, makeAndModel, msrp);
    this.length = length;
    this.numOfPassengers = numOfPassengers;
    this.propulsionType = propulsionType;
  }

  /**
   * Returns the length of the boat.
   *
   * @return the length of the boat as a Float.
   */
  public Float getLength() {
    return length;
  }

  /**
   * Sets the length of the boat.
   *
   * @param length the new length of the boat as a Float.
   */
  public void setLength(Float length) {
    this.length = length;
  }

  /**
   * Returns the number of passengers that the boat can accommodate.
   *
   * @return the number of passengers as an Integer.
   */
  public Integer getNumOfPassengers() {
    return numOfPassengers;
  }

  /**
   * Sets the number of passengers that the boat can accommodate.
   *
   * @param numOfPassengers the new number of passengers as an Integer.
   */
  public void setNumOfPassengers(Integer numOfPassengers) {
    this.numOfPassengers = numOfPassengers;
  }

  /**
   * Returns the propulsion type of the boat.
   *
   * @return the propulsion type as a PropulsionType.
   */
  public PropulsionType getPropulsionType() {
    return propulsionType;
  }

  /**
   * Sets the propulsion type of the boat.
   *
   * @param propulsionType the new propulsion type as a PropulsionType.
   */
  public void setPropulsionType(PropulsionType propulsionType) {
    this.propulsionType = propulsionType;
  }

  /**
   * Compares this Boat with the specified object for equality.
   * The comparison is based on the properties of the Boat and its superclass.
   *
   * @param o the object to compare with.
   * @return true if this object is the same as the o argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Boat boat)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(getLength(), boat.getLength()) && Objects.equals(
        numOfPassengers, boat.numOfPassengers) && propulsionType == boat.propulsionType;
  }

  /**
   * Returns a hash code value for the Boat.
   * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
   *
   * @return a hash code value for this Boat.
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getLength(), numOfPassengers, propulsionType);
  }

  /**
   * Returns a string representation of the Boat, including its properties and those of its superclass.
   *
   * @return a string representation of the Boat.
   */
  @Override
  public String toString() {
    return "Boat{" +
        "length=" + length +
        ", numOfPassengers=" + numOfPassengers +
        ", propulsionType=" + propulsionType +
        '}';
  }
}
