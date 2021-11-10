package armory;

/**
 * Two handed sword weapon. Abilities depend on Strength of the wielder.
 * Requires extreme level of strength to master the weapon.
 */
class TwoHandedSword extends AbstractWeapon {
  /**
   * Constructor for Two handed sword.
   */
  public TwoHandedSword() {
    minAbility = 8;
    maxAbility = 12;
    name = "Two Handed Sword";
  }

  @Override
  public void setAbilities() {
    minAbility = 4;
    maxAbility = 6;
  }

  @Override
  public boolean isTwoHandedSword() {
    return true;
  }
}
