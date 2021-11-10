package armory;

/**
 * A weapon is assigned to the player randomly. The capabilities of a weapon are changed depending
 * on the capabilities of the player.
 */
public interface Weapons {
  /**
   * Returns the minimum ability of a weapon.
   * @return - minAbility of that weapon.
   */
  int getMinAbility();

  /**
   * Returns the maximum ability of a weapon.
   * @return - maxAbility of that weapon.
   */
  int getMaxAbility();

  /**
   * Reduces the performance of the weapon if the wielder's capabilities are less than required.
   */
  void setAbilities();

  /**
   * Returns the name of the weapon.
   * @return - name of the weapon.
   */
  String getName();

  /**
   * Checks if the given weapon is Flail or not.
   * @return - true or false.
   */
  boolean isFlail();

  /**
   * Checks if the given weapon is Two Handed Sword or not.
   * @return - true or false.
   */
  boolean isTwoHandedSword();

  /**
   * Checks if there is a weapon or not.
   * @return - true or false.
   */
  boolean isNoWeapon();
}
