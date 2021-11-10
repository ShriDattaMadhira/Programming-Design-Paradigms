package sanctuary;

import primates.Primates;

/**
 * Housing for primates.
 */
public interface Housing {

  /**
   * adds the monkey to enclosure or isolation.
   * @return true if monkey is added.
   */
  public boolean addMonkey(Primates primate);

  /**
   * Removes monkeys from the Enclosure.
   * @return - removed primate object.
   */
  public Primates removeMonkey(String name);

  /**
   * Returns the ID of the enclosure.
   * @return enclosure ID.
   */
  public int getID();
}
