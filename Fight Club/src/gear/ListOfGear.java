package gear;

import java.util.Comparator;

/**
 * List of gear equipment.
 */
public interface ListOfGear {
  /**
   * Returns the length of the gear list.
   * @return - length.
   */
  int length();

  /**
   * Returns the gear at that position.
   * @param index - position.
   * @return - gear.
   */
  Gear get(int index);

  /**
   * Return sorted gear list.
   * @param comp - comparator for gear.
   * @return - gears list.
   */
  ListOfGear sort(Comparator<Gear> comp);

  /**
   * Insert the gear into sorted position.
   * @param g - gear.
   * @param comp - comparator.
   * @return - list of gear.
   */
  ListOfGear insert(Gear g, Comparator<Gear> comp);

  /**
   * Checks if it the object is empty node or not.
   * @param emptyNode - Empty Node object.
   * @return - True or False.
   */
  default boolean equalsEmptyNode(GearEmptyNode emptyNode) {
    return false;
  }

  /**
   * Checks if it the object is gear element node or not.
   * @param elementNode - gear element Node object.
   * @return - True or False.
   */
  default boolean equalsElementNode(GearElementNode elementNode) {
    return false;
  }
}
