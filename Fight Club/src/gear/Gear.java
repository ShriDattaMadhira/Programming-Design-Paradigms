package gear;

import java.util.List;

/**
 * Gear worn by the player. Has Headgear, belts, footwear, and magic potions.
 */
public interface Gear extends Comparable<Gear> {

  /**
   * Number of items of that gear can be active at any given time.
   * @return - limit of the gear.
   */
  int getLimit();

  /**
   * What abilities does the gear affect.
   * @return - list of abilities it affects.
   */
  List<Integer> getEffects();

  /**
   * Return name of the gear.
   * @return - name of the gear.
   */
  String getName();

  /**
   * Return copy of the gear object.
   * @return - copy of the gear object.
   */
  Gear getCopy();
}
