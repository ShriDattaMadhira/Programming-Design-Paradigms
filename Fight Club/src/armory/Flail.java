package armory;

/**
 * Flail weapon. Abilities depend on the Dexterity of the wielder.
 */
class Flail extends AbstractWeapon {
  /**
   * Constructor for Flail Weapon.
   */
  public Flail() {
    minAbility = 8;
    maxAbility = 12;
    name = "Flail";
  }

  /**
   * Sets the abilities if the player's capabilities are less.
   */
  @Override
  public void setAbilities() {
    minAbility = 4;
    maxAbility = 6;
  }

  @Override
  public boolean isFlail() {
    return true;
  }
}
