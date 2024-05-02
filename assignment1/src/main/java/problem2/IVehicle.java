package problem2;

/**
 * Represents interface class for the vehicle object
 * @author Arman Engin Sucu
 */
public interface IVehicle {

  /**
   * gets the vehicle id
   * @return returns the vehicle id
   */
  String getId();

  /**
   * gets the manufacturing year of the vehicle
   * @return returns the manufacturing year as an Integer
   */
  Integer getManufacturingYear();

  /**
   * sets the manufacturing year of the vehicle
   * @param manufacturingYear new manufacturing year to set
   */
  void setManufacturingYear(Integer manufacturingYear);

  /**
   * sets make and mdoel of the vehicle
   * @return the make and model of the vehicle
   */
  MakeModel getMakeAndModel();

  /**
   * sets make and model of the vehicle to new value
   * @param makeAndModel make and model of the vehicle
   */
  void setMakeAndModel(MakeModel makeAndModel);

  /**
   * gets Manufacturer Suggested Retail Price of the vehicle
   * @return Double Manufacturer Suggested Retail Price
   */
  Double getMSRP();

  /**
   * sets Manufacturer Suggested Retail Price of the vehicle to a new value
   * @param msrp Double value of new msrp value
   */
  void setMSRP(Double msrp);

}
