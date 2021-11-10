package sanctuary;

import primates.Primates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Interface for all Sanctuaries.
 */
public interface ISanctuary {
  /**
   * Return name of the sanctuary.
   * @return Sanctuary Name.
   */
  public String getSanctuaryName();

  /**
   * Return number of enclosures in the sanctuary.
   * @return total number of Enclosures.
   */
  public int getTotalEnclosures();

  /**
   * Return number of cages in the sanctuary.
   * @return total number of Cages.
   */
  public int getTotalCages();

  /**
   * Returns food details of the Sanctuary.
   * @return Food Details.
   */
  public HashMap<String, Integer> getFoodDetails();

  /**
   * Increase the Enclosure count.
   * @param count Increase by count.
   */
  public void incTotalEnclosures(int count);

  /**
   * Increase the cage count.
   * @param count Increase by count.
   */
  public void incTotalCages(int count);

  /**
   * Ability to expand the enclosure.
   * @param encID - id of the enclosure to be updated.
   * @param size - size to which it should be updated.
   * @return - if the update is a success or failure.
   */
  public boolean incEnclosureSize(int encID, int size);

  /**
   * Returns the locations of particular species in the Sanctuary.
   * @param species - Species to search.
   * @return - locations where they are housed.
   */
  public ArrayList<String> searchSpecies(String species);

  /**
   * Moves monkey to Enclosure.
   * @param name - monkey name.
   */
  public void moveToEnclosure(String name);

  /**
   * Moves monkey to Isolation.
   * @param name - monkey name.
   */
  public void moveToIsolation(String name);

  /**
   * Adds a new monkey to Isolation for checkup.
   * @param p - Primate object.
   * @return - True if monkey is added, else False.
   */
  public boolean newMonkey(Primates p);

  /**
   * Returns the list of all species housed in the sanctuary.
   * @return set of all species.
   */
  public TreeMap<String, ArrayList<String>> getAllSpecies();

  /**
   * Returns the sign for a given enclosure.
   * @param encID - enclosure identifier.
   * @return - Sign of primate details of that enclosure.
   */
  public ArrayList<ArrayList<String>> produceSign(int encID);

  /**
   * Returns the shopping list for the sanctuary.
   * @return - shopping list.
   */
  public HashMap<String, Integer> getShoppingList();

  /**
   * Returns the list of Monkey Names alphabetically.
   * @return - list of monkeyNames.
   */
  public TreeMap<String, String> getAllMonkeys();

  /**
   * Returns the quantity available for a given food item.
   * @param item - food item to be searched.
   * @return - quantity of food available for that food item.
   * @throws IllegalArgumentException - for food item not present in the list.
   */
  public Integer searchFood(String item);
}
