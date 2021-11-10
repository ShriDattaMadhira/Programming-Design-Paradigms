package gear;

import java.util.ArrayList;
import java.util.Random;

/**
 * Magic potions that enchance player's abilities.
 */
class Potions extends AbstractGear {
  static Random rand = new Random(0);

  /**
   * Constructor for potions.
   */
  Potions() {
    limit = -1;
    effects = new ArrayList<>() {
      {
        add(rand.nextInt(2));
        add(rand.nextInt(2) + 2);
      }
    };
    name = "Potion";
  }

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
    return 1;
  }

  /**
   * Compares this object with the Potions object.
   * @param p - Potions.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(Potions p) {
    return 0;
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

  // copy constructor for Potions class.
  Potions(Potions potion) {
    this.limit = potion.getLimit();
    this.effects = potion.getEffects();
    this.name = potion.name;
  }

  @Override
  public Gear getCopy() {
    return new Potions(this);
  }
}
