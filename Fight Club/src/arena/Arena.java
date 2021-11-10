package arena;

import armory.WeaponFactory;
import armory.Weapons;
import gear.Gear;
import gear.GearFactory;
import gear.ListOfGear;
import player.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Arena is where the majority of the thrilling stuff happens.
 * Players get into the arena with bare hands and no gear.
 * Before the battle commences, the players are given gear and a weapon randomly from a bag of gears
 * and armory of weapons respectively.
 * Then they fight and the one that stands alone at the end is declared the winner.
 */
public class Arena implements Games {
  static Random rand = new Random(0);

  private final Players player1;
  private final Players player2;
  private Players turn = null;

  private ArrayList<Weapons> weapons;

  private static final GearFactory gearFactory = new GearFactory();
  private static final WeaponFactory weaponFactory = new WeaponFactory();

  private ListOfGear bag = gearFactory.createGearEmptyNode();
  private ListOfGear avail1 = gearFactory.createGearEmptyNode();
  private ListOfGear avail2 = gearFactory.createGearEmptyNode();

  /**
   * Sets up the arena for a thrilling match up between two players.
   * @param p1 - player 1.
   * @param p2 - player 2.
   */
  public Arena(Players p1, Players p2) {
    if ( p1 == null || p2 == null ) {
      throw new IllegalArgumentException("One or more players are not present.");
    }
    if (!p1.getWeapon().isNoWeapon() || !p2.getWeapon().isNoWeapon()) {
      throw new IllegalStateException("Players should not have weapons before entering the arena.");
    }
    if (!p1.getActiveGear().equals(gearFactory.createGearEmptyNode())
            || !p2.getActiveGear().equals(gearFactory.createGearEmptyNode())) {
      throw new IllegalStateException("Players cannot have gear before entering the arena.");
    }
    player1 = p1;
    player2 = p2;
    availableWeapons();
    availableGear();
    getReadyForBattle();
  }

  // Fills the bag with gears.
  private void availableGear() {
    int headgears = rand.nextInt(5) + 4;
    for (int i = 0; i < headgears; i++) {
      bag = gearFactory.createGearElementNode(gearFactory.createHeadGear(), bag);
    }
    int potions = rand.nextInt(15) + 15;
    for (int i = 0; i < potions; i++) {
      bag = gearFactory.createGearElementNode(gearFactory.createPotions(), bag);
    }
    int belts = rand.nextInt(15) + 15;
    for (int i = 0; i < belts; i++) {
      bag = gearFactory.createGearElementNode(gearFactory.createBelts(rand.nextInt(3)), bag);
    }
    int footwear = rand.nextInt(5) + 5;
    for (int i = 0; i < footwear; i++) {
      bag = gearFactory.createGearElementNode(gearFactory.createFootWear(), bag);
    }
  }

  // Fills the armory with weapons.
  private void availableWeapons() {
    weapons = new ArrayList<>();

    int katanas = rand.nextInt(4);
    for (int i = 0; i < katanas + 1; i++) {
      weapons.add(weaponFactory.createKatana());
    }

    int broadSwords = rand.nextInt(4);
    for (int i = 0; i < broadSwords + 1; i++) {
      weapons.add(weaponFactory.createBroadSword());
    }

    int twoHandedSwords = rand.nextInt(4);
    for (int i = 0; i < twoHandedSwords + 1; i++) {
      weapons.add(weaponFactory.createTwoHandedSword());
    }

    int axes = rand.nextInt(4);
    for (int i = 0; i < axes + 1; i++) {
      weapons.add(weaponFactory.createAxe());
    }

    int flails = rand.nextInt(4);
    for (int i = 0; i < flails + 1; i++) {
      weapons.add(weaponFactory.createFlail());
    }
  }

  // Assigns weapons and gears for both the players.
  private void getReadyForBattle() {
    assignWeapon();
    assignGear();

    activateGear(player1, avail1);
    activateGear(player2, avail2);
  }

  // Assign gears to each player.
  private void assignGear() {
    ArrayList<Gear> assigned = new ArrayList<>();
    int length = bag.length();

    for (int i = 0; i < 20; i++) {
      Gear g = bag.get(rand.nextInt(length));
      avail1 = gearFactory.createGearElementNode(g, avail1);
      assigned.add(g);
    }

    for (int i = 0; i < 20; i++) {
      Gear g = bag.get(rand.nextInt(length));
      while (assigned.contains(g)) {
        g = bag.get(rand.nextInt(length));
      }
      avail2 = gearFactory.createGearElementNode(g, avail2);
    }
  }

  // Assign weapon to each player.
  private void assignWeapon() {
    int p1Selection = rand.nextInt(weapons.size());
    Weapons w = weapons.get(p1Selection);
    player1.setWeapon(w);
    editWeaponCapabilities(player1, w);

    int p2Selection = rand.nextInt(weapons.size());
    while (p2Selection == p1Selection) {
      p2Selection = rand.nextInt(weapons.size());
    }
    w = weapons.get(p2Selection);
    player2.setWeapon(w);
    editWeaponCapabilities(player2, w);
  }

  // Activate each player's gear.
  private void activateGear(Players player, ListOfGear availableGear) {
    ArrayList<Gear> temp2 = new ArrayList<>();
    int length = availableGear.length();
    boolean neg = false;

    for (int i = 0; i < length; i++) {
      if ((i + 1) % 4 == 0) {
        neg = true;
      }
      Gear g = availableGear.get(i);
      int check = checkIfActive(temp2, g);
      if (check <= 0) { // headgear and footwear == 0 | potions < 0.
        temp2.add(g);
        editPlayerAbilities(g.getEffects(), player, neg);
      } else if (check > 1 && check + g.getLimit() <= 10) { // belts.
        temp2.add(g);
        editPlayerAbilities(g.getEffects(), player, neg);
      }
    }

    ListOfGear temp = gearFactory.createGearEmptyNode();
    for (Gear g: temp2) {
      temp = gearFactory.createGearElementNode(g, temp);
    }
    player.setActiveGear(temp.sort((g1, g2) -> g2.compareTo(g1)));
  }

  // Checks if the gear is already actively in use by the player.
  // Used for headGears(only 1 allowed), footWears(only 1 allowed), and belts(only 10 allowed).
  private int checkIfActive(ArrayList<Gear> active, Gear curr) {
    if (active.size() == 0) {
      return 0;
    }
    int count = 0;
    for (Gear g: active) {
      if (curr.compareTo(g) == 0) {
        count += g.getLimit();
      }
    }
    return count;
  }

  // Change the maximum and minimum capabilities of the weapon depending on the player.
  private void editWeaponCapabilities(Players p, Weapons w) {
    if (w.isTwoHandedSword() && p.getStrength() <= 14) {
      w.setAbilities();
    } else if (w.isFlail() && p.getDexterity() <= 14) {
      w.setAbilities();
    }
  }

  // Change the attributes of the players depending on the effects of the equipped gear.
  private void editPlayerAbilities(List<Integer> affects, Players p, boolean negativeEffect) {
    int effect;
    if (negativeEffect) {
      effect = -rand.nextInt(3);
    } else {
      effect = rand.nextInt(3);
    }

    for (Integer i: affects) {
      if (i == 0) {
        p.setStrength(p.getStrength() + effect);
        p.setDexterity(p.getDexterity() - rand.nextInt(3));
        p.setConstitution(p.getConstitution() - rand.nextInt(1) + 1);
      } else if (i == 1) {
        p.setConstitution(p.getConstitution() + effect);
        p.setCharisma(p.getCharisma() - rand.nextInt(1) + 1);
      } else if (i == 2) {
        p.setDexterity(p.getDexterity() + effect);
        p.setConstitution(p.getStrength() - rand.nextInt(3));
      } else if (i == 3) {
        p.setCharisma(p.getCharisma() + effect);
      }
    }
  }

  // Helper method for toString() to display active gear of the player.
  private StringBuilder displayGear(ListOfGear log) {
    StringBuilder gears = new StringBuilder();
    int len = log.length();

    gears.append("[ ");
    for (int i = 0; i < len; i++) {
      gears.append(log.get(i).getName()).append(", ");
    }
    gears.delete(gears.length() - 2, gears.length());
    gears.append("]");
    return gears;
  }

  // Helper method used by startBattle() to determine initial turn depending on players' Charisma,
  // and also subsequent turns.
  private Players getTurn() {
    if (turn == null) {
      if (player1.getCharisma() > player2.getCharisma()) {
        return player1;
      } else {
        return player2;
      }
    } else {
      if (turn == player1) {
        return player2;
      } else {
        return player1;
      }
    }
  }

  // Does all the calculations for the current round of the fight.
  private int fight(Players attacker, Players defender) {
    int strikingPower;
    int avoidanceAbility;
    int potentialDamage;
    int actualDamage = 0;

    strikingPower = attacker.getStrength() + rand.nextInt( 10) + 1;
    avoidanceAbility = defender.getDexterity() + rand.nextInt(10) + 1;
    if (strikingPower > avoidanceAbility) {
      int weaponMin = attacker.getWeapon().getMinAbility();
      int weaponMax = attacker.getWeapon().getMaxAbility();
      potentialDamage = attacker.getStrength()
              + rand.nextInt((weaponMax - weaponMin) + 1) + weaponMin;
      actualDamage = potentialDamage - defender.getConstitution();
    }
    return actualDamage;
  }

  // Strips the players of all their gear and clears the garbage.
  private void gameOver() {
    player1.setWeapon(weaponFactory.createDefaultWeapon());
    player2.setWeapon(weaponFactory.createDefaultWeapon());

    player1.setActiveGear(gearFactory.createGearEmptyNode());
    player2.setActiveGear(gearFactory.createGearEmptyNode());

    System.gc();
  }

  /**
   * Players start the battle and fight till one of the player dies or number moves go beyond 1000.
   * The assumption is that the players get tired after 1000 rounds of non-stop fighting.
   */
  @Override
  public void startBattle() {
    int h1 = player1.getStrength() + player1.getCharisma() + player1.getDexterity()
            + player1.getConstitution();
    int h2 = player2.getStrength() + player2.getConstitution() + player2.getDexterity()
            + player2.getCharisma();
    if (h1 <= 0 || h2 <= 10) {
      throw new IllegalStateException("Battle stopped due to ill health for one of the players");
    }
    int damage;
    int success;
    int health;

    int count = 0;
    while (h1 > 0 && h2 > 0 && count < 1000) {
      turn = getTurn();
      count += 1;
      if (turn == player1) {
        damage = fight(player1, player2);
        if (damage > 0) {
          h2 -= damage;
          success = 1;
        } else {
          success = 0;
        }
        health = h2;
      } else {
        damage = fight(player2, player1);
        if (damage > 0) {
          h1 -= damage;
          success = 1;
        } else {
          success = 0;
        }
        health = h1;
      }
      new LiveStream(turn.getPlayerID(), success, damage, health);
    }

    if (h1 <= 0) {
      new LiveStream(player2.getPlayerID());
    } else {
      new LiveStream(player1.getPlayerID());
    }
    gameOver();
  }

  /**
   * Just before the match commences, the details about the players is revealed to the spectators.
   * @return - String of player details.
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Player ID = ").append(player1.getPlayerID()).append("\nStrength = ")
            .append(player1.getStrength()).append("\nConstitution = ")
            .append(player1.getConstitution()).append("\nDexterity = ")
            .append(player1.getDexterity()).append("\nCharisma = ").append(player1.getCharisma())
            .append("\nHealth = ").append(player1.getStrength() + player1.getCharisma()
                    + player1.getDexterity() + player1.getConstitution()).append("\nWeapon = ")
            .append(player1.getWeapon().getName()).append("\nGear = ")
            .append(displayGear(player1.getActiveGear()));

    sb.append("\n\n");

    sb.append("Player ID = ").append(player2.getPlayerID()).append("\nStrength = ")
            .append(player2.getStrength()).append("\nConstitution = ")
            .append(player2.getConstitution()).append("\nDexterity = ")
            .append(player2.getDexterity()).append("\nCharisma = ").append(player2.getCharisma())
            .append("\nHealth = ").append(player2.getStrength() + player2.getCharisma()
                    + player2.getDexterity() + player2.getConstitution()).append("\nWeapon = ")
            .append(player2.getWeapon().getName()).append("\nGear = ")
            .append(displayGear(player2.getActiveGear()));
    return sb.toString();
  }
}
