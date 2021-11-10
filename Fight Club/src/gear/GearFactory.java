package gear;

/**
 * Factory that creates different types of gear for the characters to use during the battle.
 */
public class GearFactory {
  /**
   * Create gear element node.
   * @return - element gear node.
   */
  public ListOfGear createGearElementNode(Gear gear, ListOfGear rest) {
    return new GearElementNode(gear, rest);
  }

  /**
   * Create empty gear node.
   * @return - empty gear node.
   */
  public ListOfGear createGearEmptyNode() {
    return new GearEmptyNode();
  }

  /**
   * Create Headgear.
   * @return - Head gear.
   */
  public HeadGear createHeadGear() {
    return new HeadGear();
  }

  /**
   * Create potions.
   * @return - potion.
   */
  public Potions createPotions() {
    return new Potions();
  }

  /**
   * Create belts.
   * @return - belt.
   */
  public Belts createBelts(int size) {
    return new Belts(size);
  }

  /**
   * Create Footwear.
   * @return - Footwear.
   */
  public FootWear createFootWear() {
    return new FootWear();
  }
}
