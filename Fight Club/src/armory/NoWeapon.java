package armory;

/**
 * No weapon. Just the players' hands.
 */
class NoWeapon extends AbstractWeapon {

  NoWeapon() {
    minAbility = 0;
    maxAbility = 0;
    name = "Hands";
  }

  @Override
  public void setAbilities() {
    minAbility = 0;
    maxAbility = 0;
  }

  @Override
  public boolean isNoWeapon() {
    return true;
  }
}
