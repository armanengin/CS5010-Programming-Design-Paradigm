package problem2;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a general vehicle. This abstract class provides a foundation for vehicle objects
 * with common properties such as ID, manufacturing year, make and model, and MSRP (Manufacturer's
 * Suggested Retail Price). The ID is uniquely generated for each vehicle.
 *
 * @author Arman Engin Sucu
 */
public abstract class Vehicle implements IVehicle {
  private final String id;
  private Integer manufacturingYear;
  private MakeModel makeAndModel;
  private Double msrp;

  /**
   * Constructs a new Vehicle with the specified manufacturing year, make and model, and MSRP.
   * A unique ID is automatically generated for the vehicle.
   *
   * @param manufacturingYear the year the vehicle was manufactured
   * @param makeAndModel the make and model of the vehicle
   * @param msrp the Manufacturer's Suggested Retail Price of the vehicle
   */
  public Vehicle(Integer manufacturingYear, MakeModel makeAndModel, Double msrp){
    this.id = UUID.randomUUID().toString(); // generate random unique Id
    this.manufacturingYear = manufacturingYear;
    this.makeAndModel = makeAndModel;
    this.msrp = msrp;
  }

  /**
   * Retrieves the unique ID of the vehicle.
   *
   * @return the unique ID of the vehicle
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Retrieves the manufacturing year of the vehicle.
   *
   * @return the manufacturing year of the vehicle
   */
  @Override
  public Integer getManufacturingYear() {
    return manufacturingYear;
  }

  /**
   * Sets the manufacturing year of the vehicle.
   *
   * @param manufacturingYear the new manufacturing year of the vehicle
   */
  @Override
  public void setManufacturingYear(Integer manufacturingYear) {
    this.manufacturingYear = manufacturingYear;
  }

  /**
   * Retrieves the make and model of the vehicle.
   *
   * @return the make and model of the vehicle
   */
  @Override
  public MakeModel getMakeAndModel() {
    return makeAndModel;
  }

  /**
   * Sets the make and model of the vehicle.
   *
   * @param makeAndModel the new make and model of the vehicle
   */
  @Override
  public void setMakeAndModel(MakeModel makeAndModel) {
    this.makeAndModel = makeAndModel;
  }

  /**
   * Retrieves the Manufacturer's Suggested Retail Price (MSRP) of the vehicle.
   *
   * @return the MSRP of the vehicle
   */
  @Override
  public Double getMSRP() {
    return msrp;
  }

  /**
   * Sets the Manufacturer's Suggested Retail Price (MSRP) of the vehicle.
   *
   * @param msrp the new MSRP of the vehicle
   */
  @Override
  public void setMSRP(Double msrp) {
    this.msrp = msrp;
  }

  /**
   * Compares this vehicle to the specified object. The result is {@code true} if and only if
   * the argument is not {@code null} and is a {@code Vehicle} object that has the same
   * manufacturing year, make and model, and MSRP values.
   *
   * @param o the object to compare with
   * @return {@code true} if this vehicle is equal to the argument; {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Vehicle vehicle)) {
      return false;
    }
    return Objects.equals(getManufacturingYear(), vehicle.getManufacturingYear()) &&
        Objects.equals(getMakeAndModel(), vehicle.getMakeAndModel()) &&
        Objects.equals(msrp, vehicle.msrp);
  }

  /**
   * Returns a hash code value for the vehicle. This method is supported for the benefit of hash tables.
   *
   * @return a hash code value for this vehicle
   */
  @Override
  public int hashCode() {
    return Objects.hash(getManufacturingYear(), getMakeAndModel(), msrp);
  }

  /**
   * Returns a string representation of the vehicle. This representation includes the unique ID,
   * manufacturing year, make and model, and MSRP.
   *
   * @return a string representation of the vehicle
   */
  @Override
  public String toString() {
    return "Vehicle{" +
        "id='" + id + '\'' +
        ", manufacturingYear=" + manufacturingYear +
        ", makeAndModel=" + makeAndModel +
        ", msrp=" + msrp +
        '}';
  }
}
