package armory;

/**
 * Factory that creates different weapons for the characters to use during the battle.
 */
public class WeaponFactory {
  /**
   * Creates a Katana Sword.
   * @return - Katana.
   */
  public Katana createKatana() {
    return new Katana();
  }

  /**
   * Create a Two Handed Sword.
   * @return - Two Handed Sword.
   */
  public TwoHandedSword createTwoHandedSword() {
    return new TwoHandedSword();
  }

  /**
   * Create a Broad Sword.
   * @return - Broad Sword.
   */
  public BroadSword createBroadSword() {
    return new BroadSword();
  }

  /**
   * Create a Axe Sword.
   * @return - Axe.
   */
  public Axe createAxe() {
    return new Axe();
  }

  /**
   * Create a Flail.
   * @return - Flail.
   */
  public Flail createFlail() {
    return new Flail();
  }

  /**
   * creates a default weapon, which is no weapon. All the abilities are set to zero.
   * @return - empty node.
   */
  public NoWeapon createDefaultWeapon() {
    return new NoWeapon();
  }
}
