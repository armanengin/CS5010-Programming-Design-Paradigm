package problem1;

import java.util.HashMap;

/**
 * This class represents a system for managing frequent flyers. It uses a HashMap to store
 * FrequentFlyer objects, allowing for various operations such as adding, removing, and retrieving flyers.
 * The system also provides utility methods to check flyer existence and validate flyer details.
 *
 * @author Arman Engin Sucu
 */
public class FrequentFlyerSystem{
  //hashmap as database, it maps ids with the corresponding frequent flyers
  private static HashMap<String, FrequentFlyer> frequentFlyers = new HashMap<>();

  /**
   * Adds a new frequent flyer to the system. If a flyer with the same ID already exists,
   * an exception is thrown.
   *
   * @param flyer the FrequentFlyer object to add to the system.
   * @throws IllegalArgumentException if a flyer with the same ID already exists in the system.
   */
  public static void addFrequentFlyer(FrequentFlyer flyer){
    String newFlyersId = flyer.getId();
    if (!isFlyerExist(newFlyersId)){
      frequentFlyers.put(newFlyersId, flyer);
    } else{
      throw new IllegalArgumentException("The flyer with id: " + newFlyersId + " already exist!");
    }
  }

  /**
   * Removes a frequent flyer from the system based on their ID. If no flyer with the given ID exists,
   * an exception is thrown.
   *
   * @param id the ID of the frequent flyer to be removed.
   * @throws IllegalArgumentException if no flyer with the specified ID exists in the system.
   */
  public static void removeFrequentFlyer(String id){
    if (isFlyerExist(id)){
      frequentFlyers.remove(id);
    } else{
      throw new IllegalArgumentException("The flyer with id: " + id + " is not exist!");
    }
  }

  /**
   * Checks if a frequent flyer exists in the system based on their ID.
   *
   * @param id the ID of the frequent flyer to be checked.
   * @return true if the flyer exists, false otherwise.
   */
  public static boolean isFlyerExist(String id){
    return frequentFlyers.containsKey(id);
  }

  /**
   * Validates if the provided ID and name correspond to the same frequent flyer in the system.
   *
   * @param id   the ID of the frequent flyer.
   * @param name the Name object representing the flyer's name.
   * @return true if the ID and name match a flyer in the system, false otherwise.
   */
  public static boolean idNameCheck(String id, Name name){
    FrequentFlyer flyerToBeChecked = frequentFlyers.get(id);
    return name.equals(flyerToBeChecked.getName());
  }

  /**
   * Retrieves a FrequentFlyer object from the system based on the given ID. If no flyer with the
   * specified ID exists, an exception is thrown.
   *
   * @param id the ID of the frequent flyer to be retrieved.
   * @return the FrequentFlyer object corresponding to the given ID.
   * @throws IllegalArgumentException if no flyer with the specified ID exists in the system.
   */
  public static FrequentFlyer getFrequentFlyer(String id){
    if (isFlyerExist(id)){
      return frequentFlyers.get(id);
    } else{
      throw new IllegalArgumentException("The id: " + id +  ", to get Frequent Flyer doesn't exist!");
    }
  }

  /**
   * Clears all frequent flyers from the system. Intended for use in testing.
   */
  public static void clearSystem() {
    frequentFlyers.clear();
  }
}
