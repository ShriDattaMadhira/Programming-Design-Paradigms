package armory;

/**
 * Broad Sword weapon. Name comes from its appearance. Quite heavy and powerful weapon to master.
 */
class BroadSword extends AbstractWeapon {
  /**
   * Constructor for Broad Sword.
   */
  public BroadSword() {
    minAbility = 6;
    maxAbility = 10;
    name = "Broad Sword";
  }

  @Override
  public void setAbilities() {
    minAbility = 6;
    maxAbility = 10;
  }
}
