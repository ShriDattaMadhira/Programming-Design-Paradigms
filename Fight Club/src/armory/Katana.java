package armory;

/**
 * Katana Sword - One of the deadliest swords made by mankind.
 * Used primarily by Japanese samurais and assassins in the past.
 */
class Katana extends AbstractWeapon {
  /**
   * Constructor for Katana Sword.
   */
  public Katana() {
    minAbility = 8;
    maxAbility = 12;
    name = "Katana";
  }

  @Override
  public void setAbilities() {
    minAbility = 8;
    maxAbility = 12;
  }
}
