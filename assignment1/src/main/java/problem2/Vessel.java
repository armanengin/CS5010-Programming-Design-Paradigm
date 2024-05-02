package problem2;

/**
 * Represents an abstract vessel class
 * @author Arman Engin Sucu
 */
public abstract class Vessel extends Vehicle {

  /**
   * constructor for vehicle class which has three parameters for initialization id is initialized
   * automatically with a random UUID
   *
   * @param manufacturingYear Integer value represents manufacturing year
   * @param makeAndModel      object to represent make and model
   * @param msrp              Double value to represent MSRP of the vehicle
   */
  public Vessel(Integer manufacturingYear, MakeModel makeAndModel, Double msrp) {
    super(manufacturingYear, makeAndModel, msrp);
  }
}
