package armory;

/**
 * Axe weapon. One of the most powerful weapons to have. Relatively easier to master.
 */
class Axe extends AbstractWeapon {
  /**
   * Constructor for Axe Weapon.
   */
  public Axe() {
    minAbility = 6;
    maxAbility = 10;
    name = "Axe";
  }

  /**
   * Sets the abilities if the player's capabilities are less.
   */
  @Override
  public void setAbilities() {
    minAbility = 6;
    maxAbility = 10;
  }
}
