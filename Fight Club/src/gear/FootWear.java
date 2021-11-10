package gear;

import java.util.ArrayList;

/**
 * Footwear for the player. Affects dexterity of the player.
 */
class FootWear extends AbstractGear {
  /**
   * Constructor for Footwear.
   */
  public FootWear() {
    limit = 1;
    effects = new ArrayList<>() {
      {
        add(2);
      }
    };
    name = "Foot Wear";
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
    return 1;
  }

  /**
   * Compares this object with the Belts object.
   * @param b - Belts.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(Belts b) {
    return 1;
  }

  /**
   * Compares this object with the FootWear object.
   * @param fw - Footwear.
   * @return - equal, less than, or greater than.
   */
  @Override
  protected int compare(FootWear fw) {
    return 0;
  }

  // copy constructor for footwear.
  FootWear(FootWear fw) {
    this.limit = fw.limit;
    this.effects = fw.effects;
    this.name = fw.name;
  }

  @Override
  public Gear getCopy() {
    return new FootWear(this);
  }
}
