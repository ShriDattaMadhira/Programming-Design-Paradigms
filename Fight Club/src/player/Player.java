package player;

import armory.WeaponFactory;
import armory.Weapons;
import gear.GearFactory;
import gear.ListOfGear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This represents an individual player. It tells you the attributes the player have.
 */
public class Player implements Players {
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private final int playerID;
  private ListOfGear activeGear;
  private Weapons weapon;
  private static ArrayList<Integer> ids = new ArrayList<>();

  static Random rand = new Random(0);

  /**
   * Constructor for Player class.
   * @param playerID - ID of the player.
   */
  public Player(int playerID) {
    if (!checkUniqueness(playerID)) {
      throw new IllegalArgumentException("Player already exists.");
    }
    this.playerID = playerID;
    ids.add(playerID);
    this.strength = setAttribute();
    this.constitution = setAttribute();
    this.dexterity = setAttribute();
    this.charisma = setAttribute();
    this.weapon = new WeaponFactory().createDefaultWeapon();
    this.activeGear = new GearFactory().createGearEmptyNode();
  }

  private boolean checkUniqueness(int playerID) {
    return !ids.contains(playerID);
  }

  /**
   * Constructor to deep copy player object.
   * @param player - player object to be copied.
   */
  public Player(Players player) {
    // Player objects to copy constructor cannot be null.
    if (player == null) {
      throw new IllegalArgumentException("No player provided.");
    }
    this.playerID = player.getPlayerID();
    this.strength = player.getStrength();
    this.constitution = player.getConstitution();
    this.dexterity = player.getDexterity();
    this.charisma = player.getCharisma();
    this.weapon = player.getWeapon();
    this.activeGear = player.getActiveGear();
  }

  private int setAttribute() {
    int[] attr = new int[4];
    int result;
    for (int i = 0; i < 4; i++) {
      result = rollDice();
      while (result == 1) {
        result = rollDice();
      }
      attr[i] = result + 1;
    }
    Arrays.sort(attr);
    return attr[3] + attr[2] + attr[1];
  }

  private int rollDice() {
    return rand.nextInt(6);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Player{");
    sb.append("strength=").append(strength);
    sb.append(", constitution=").append(constitution);
    sb.append(", dexterity=").append(dexterity);
    sb.append(", charisma=").append(charisma);
    sb.append(", playerID=").append(playerID);
    sb.append(", activeGear=").append(activeGear);
    sb.append(", weapon=").append(weapon.getName());
    sb.append('}');
    return sb.toString();
  }

  /**
   * Choose weapons for the player whenever he enters the arena.
   */
  @Override
  public void setWeapon(Weapons weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("Weapon is null");
    }
    this.weapon = weapon;
  }

  /**
   * Return the weapon of the player.
   * @return - weapon of the player.
   */
  @Override
  public Weapons getWeapon() {
    return this.weapon;
  }

  /**
   * Gears that are being used by the player.
   *
   * @param active - active Gear.
   */
  @Override
  public void setActiveGear(ListOfGear active) {
    if (active == null) {
      throw new IllegalArgumentException("List of gears is/are null");
    }
    this.activeGear = active;
  }

  /**
   * Return the gears of the player.
   *
   * @return - gear of the player.
   */
  @Override
  public ListOfGear getActiveGear() {
    return activeGear;
  }

  /**
   * Returns the strength of the player.
   * @return - strength of the player.
   */
  @Override
  public int getStrength() {
    return strength;
  }

  /**
   * sets the strength of the player.
   * @param strength - new strength of the player.
   */
  @Override
  public void setStrength(int strength) {
    this.strength = strength;
  }

  /**
   * Returns the constitution of the player.
   * @return - constitution of the player.
   */
  @Override
  public int getConstitution() {
    return constitution;
  }

  /**
   * sets the constitution of the player.
   * @param constitution - new constitution of the player.
   */
  @Override
  public void setConstitution(int constitution) {
    this.constitution = constitution;
  }

  /**
   * Returns the Dexterity of the player.
   * @return - dexterity of the player.
   */
  @Override
  public int getDexterity() {
    return dexterity;
  }

  /**
   * sets the dexterity of the player.
   * @param dexterity - new dexterity of the player.
   */
  @Override
  public void setDexterity(int dexterity) {
    this.dexterity = dexterity;
  }

  /**
   * Returns the Charisma of the player.
   * @return - charisma of the player.
   */
  @Override
  public int getCharisma() {
    return charisma;
  }


  /**
   * sets the charisma of the player.
   * @param charisma - new charisma of the player.
   */
  @Override
  public void setCharisma(int charisma) {
    this.charisma = charisma;
  }

  /**
   * Returns the ID of the player.
   * @return - ID of the player.
   */
  @Override
  public int getPlayerID() {
    return playerID;
  }
}
