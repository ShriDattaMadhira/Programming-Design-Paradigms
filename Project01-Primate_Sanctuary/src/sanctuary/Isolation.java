package sanctuary;

import primates.Primates;

/**
 * ISOLATION CAGES for SICK and NEW ARRIVAL primates.
 */
public class Isolation implements Housing {
  private final int cageID;
  private Primates primate;
  private boolean isEmpty;

  /**
   * Constructor for Isolation class.
   * @param id - cageID.
   */
  public Isolation(int id) {
    this.cageID = id;
    this.isEmpty = true;
  }

  static Isolation createIsolation(int id) {
    return new Isolation(id);
  }

  /**
   * Adds the monkey to a cage.
   * @param p - monkey object.
   * @return - boolean value that the monkey has a place.
   */
  @Override
  public boolean addMonkey(Primates p) {
    if (!this.isEmpty) {
      return false;
    }
    this.isEmpty = false;
    this.primate = p;
    return true;
  }

  /**
   * Removes the primate from Isolation.
   * @return removed primate.
   */
  @Override
  public Primates removeMonkey(String name) {
    Primates temp = primate;
    this.isEmpty = true;
    this.primate = null;
    return temp;
  }

  /**
   * Returns cage is empty or not.
   * @return - cage is empty or not.
   */
  boolean getIsEmpty() {
    return this.isEmpty;
  }

  /**
   * Returns the primate in the cage.
   * @return the primate in the cage.
   */
  Primates getPrimate() {
    return this.primate;
  }

  /**
   * Returns the ID of the cage.
   * @return the ID of the cage.
   */
  @Override
  public int getID() {
    return this.cageID;
  }
}
