package problem2;

import java.util.Objects;

/**
 * The {@code MakeModel} class represents the make and model of a vehicle.
 * It encapsulates the properties of a vehicle's brand (make) and its specific model.
 * This class provides methods to get and set these properties and overrides
 * {@code equals}, {@code hashCode}, and {@code toString} methods from the Object class.
 *
 * @author Arman Engin Sucu
 */
public class MakeModel {
  private String make;
  private String model;

  /**
   * Constructs a new {@code MakeModel} instance with specified make and model.
   *
   * @param make  the make of the vehicle, cannot be {@code null}
   * @param model the model of the vehicle, cannot be {@code null}
   */
  public MakeModel(String make, String model) {
    this.make = make;
    this.model = model;
  }

  /**
   * Retrieves the make of the vehicle.
   *
   * @return the make of the vehicle
   */
  public String getMake() {
    return make;
  }

  /**
   * Sets the make of the vehicle.
   *
   * @param make the make to set, cannot be {@code null}
   */

  public void setMake(String make) {
    this.make = make;
  }

  /**
   * Retrieves the model of the vehicle.
   *
   * @return the model of the vehicle
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model of the vehicle.
   *
   * @param model the model to set, cannot be {@code null}
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   * The {@code equals} method implements an equivalence relation
   * on non-null object references.
   *
   * @param o the reference object with which to compare
   * @return {@code true} if this object is the same as the obj
   *         argument; {@code false} otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MakeModel makeModel)) {
      return false;
    }
    return Objects.equals(getMake(), makeModel.getMake()) && Objects.equals(
        getModel(), makeModel.getModel());
  }

  /**
   * Returns a hash code value for the object. This method is
   * supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getMake(), getModel());
  }

  /**
   * Returns a string representation of the object. In general, the
   * {@code toString} method returns a string that
   * "textually represents" this object. The result should
   * be a concise but informative representation that is easy for a person to read.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "MakeModel{" +
        "make='" + make + '\'' +
        ", model='" + model + '\'' +
        '}';
  }
}
