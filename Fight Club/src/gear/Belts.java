package gear;

import java.util.ArrayList;

/**
 * Belts are a part of the gear worn by player.
 */
class Belts extends AbstractGear {
  private final int beltSize;

  /**
   * Constructor for belts.
   */
  public Belts(int size) {
    if (size < 0) {
      throw new IllegalArgumentException("Belt size cannot be less than zero.");
    }
    limit = 10;
    effects = new ArrayList<>() {
      {
        add(1);
        add(3);
      }
    };
    if (size == 0) {
      beltSize = BeltSize.SMALL.getBeltUnits();
      name = "Belt-Small";
    } else if (size == 1) {
      beltSize = BeltSize.MEDIUM.getBeltUnits();
      name = "Belt-Medium";
    } else {
      beltSize = BeltSize.LARGE.getBeltUnits();
      name = "Belt-Large";
    }
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
    return 0;
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

  /**
   * Number of items of that gear can be active at any given time.
   *
   * @return - limit of the gear.
   */
  @Override
  public int getLimit() {
    return beltSize;
  }

  // copy constructor for belts.
  Belts(Belts b) {
    this.limit = b.limit;
    this.beltSize = b.beltSize;
    this.effects = b.effects;
    this.name = b.name;
  }

  @Override
  public Gear getCopy() {
    return new Belts(this);
  }
}
