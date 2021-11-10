package gear;

import java.util.ArrayList;

/**
 * Headgear for the players. Affects constitution of the player.
 */
class HeadGear extends AbstractGear {
  /**
   * Constructor for the HeadGear class.
   */
  public HeadGear() {
    limit = 1;
    effects = new ArrayList<>() {
      {
        add(1);
      }
    };
    name = "Head Gear";
  }

  /**
   * Compares two gears.
   * @param g - gear.
   * @return - if less that, greater than, or equal to.
   */
  @Override
  public int compareTo(Gear g) {
    AbstractGear absG = (AbstractGear) g;
    return -absG.compare(this);
  }

  /**
   * Compares this object with the Headgear object.
   * @param hg - Headgear.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(HeadGear hg) {
    return 0;
  }

  /**
   * Compares this object with the Potions object.
   * @param p - Potions.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(Potions p) {
    return -1;
  }

  /**
   * Compares this object with the Belts object.
   * @param b - Belts.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(Belts b) {
    return -1;
  }

  /**
   * Compares this object with the FootWear object.
   * @param fw - Footwear.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(FootWear fw) {
    return -1;
  }

  // copy constructor for the headgear class.
  HeadGear(HeadGear hg) {
    this.limit = hg.limit;
    this.effects = hg.effects;
    this.name = hg.name;
  }

  @Override
  public Gear getCopy() {
    return new HeadGear(this);
  }
}
