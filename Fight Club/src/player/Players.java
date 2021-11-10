package player;

import armory.Weapons;
import gear.ListOfGear;

/**
 * Creates your player. Every player has strength, constitution, dexterity, and charisma.
 * These attributes combined with the gear and weapon he will be assigned during the battle
 * determines if he gets the first turn to land the blow or even win.
 */
public interface Players {
  /**
   * Choose weapons for the player whenever he enters the arena.
   */
  void setWeapon(Weapons weapon);

  /**
   * Return the weapon of the player.
   * @return - weapon of the player.
   */
  Weapons getWeapon();

  /**
   * Gears that are being used by the player.
   * @param active - Gear.
   */
  void setActiveGear(ListOfGear active);

  /**
   * Return the gears of the player.
   * @return - gear of the player.
   */
  ListOfGear getActiveGear();

  /**
   * Returns the strength of the player.
   * @return - strength of the player.
   */
  int getStrength();

  /**
   * sets the strength of the player.
   * @param strength - new strength of the player.
   */
  void setStrength(int strength);

  /**
   * Returns the constitution of the player.
   * @return - constitution of the player.
   */
  int getConstitution();

  /**
   * sets the constitution of the player.
   * @param constitution - new constitution of the player.
   */
  void setConstitution(int constitution);

  /**
   * Returns the Dexterity of the player.
   * @return - dexterity of the player.
   */
  int getDexterity();

  /**
   * sets the dexterity of the player.
   * @param dexterity - new dexterity of the player.
   */
  void setDexterity(int dexterity);

  /**
   * Returns the Charisma of the player.
   * @return - charisma of the player.
   */
  int getCharisma();

  /**
   * sets the charisma of the player.
   * @param charisma - new charisma of the player.
   */
  void setCharisma(int charisma);

  /**
   * Returns the ID of the player.
   * @return - ID of the player.
   */
  int getPlayerID();
}
