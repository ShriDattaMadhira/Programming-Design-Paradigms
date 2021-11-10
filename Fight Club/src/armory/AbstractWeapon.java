package armory;

/**
 * AbstractWeapon is an abstract class for all weapon types.
 */
abstract class AbstractWeapon implements Weapons {
  int minAbility;
  int maxAbility;
  String name;

  @Override
  public int getMinAbility() {
    return minAbility;
  }

  @Override
  public int getMaxAbility() {
    return maxAbility;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Checks if the current weapon is a flail or not.
   * @return - true or false.
   */
  public boolean isFlail() {
    return false;
  }

  /**
   * Checks if the current weapon is a two handed sword or not.
   * @return - true or false.
   */
  public boolean isTwoHandedSword() {
    return false;
  }

  /**
   * Checks if the current weapon is a default weapon or not.
   * @return - true or false.
   */
  public boolean isNoWeapon() {
    return false;
  }

  @Override
  public abstract void setAbilities();
}
