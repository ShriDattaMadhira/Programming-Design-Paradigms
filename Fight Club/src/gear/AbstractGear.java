package gear;

import java.util.List;

/**
 * Abstract class for gears.
 */
abstract class AbstractGear implements Gear {
  int limit;
  // what ability does it effect. (0-strength, 1-constitution, 2-dexterity, 3-charisma)
  List<Integer> effects;
  String name;

  @Override
  public int getLimit() {
    return limit;
  }

  @Override
  public List<Integer> getEffects() {
    return effects;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Compares this object with the Headgear object.
   * @param hg - Headgear.
   * @return - equal, less than, or greater than.
   */
  protected abstract int compare(HeadGear hg);

  /**
   * Compares this object with the Potions object.
   * @param p - Potions.
   * @return - equal, less than, or greater than.
   */
  protected abstract int compare(Potions p);

  /**
   * Compares this object with the Belts object.
   * @param b - Belts.
   * @return - equal, less than, or greater than.
   */
  protected abstract int compare(Belts b);

  /**
   * Compares this object with the FootWear object.
   * @param fw - Footwear.
   * @return - equal, less than, or greater than.
   */
  protected abstract int compare(FootWear fw);
}
