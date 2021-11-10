package sanctuary;

import primates.Primates;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Enclosures where the same species of monkeys live.
 */
public class Enclosure implements Housing {
  private final int enclosureID;
  private int enclosureSize;
  private String speciesType;
  private int enclosureCapacity;
  private ArrayList<Primates> troop = new ArrayList<>();

  /**
   * Constructor for Enclosure.
   * @param id - enclosureID
   * @param size - enclosure size
   * @param species - species housed
   */
  public Enclosure(int id, int size, String species) {
    this.enclosureID = id;
    this.enclosureSize = size;
    this.speciesType = species;
  }

  /**
   * Adds a monkey to the enclosure, if there is space.
   * @param p - monkey object.
   * @return - Boolean if monkey is added or not.
   * @throws IllegalStateException - exception if the capacity is full.
   */
  @Override
  public boolean addMonkey(Primates p) {
    if (Objects.equals(speciesType, "any")) {
      this.speciesType = p.getSpecies();
      this.enclosureCapacity = this.enclosureSize / p.getSpaceReq();
    }
    if (this.enclosureCapacity == 0) {
      return false;
    }
    troop.add(p);
    this.enclosureCapacity -= 1;
    return true;
  }

  static Enclosure createEnclosure(int id, int size, String species) {
    return new Enclosure(id, size, species);
  }

  /**
   * Removes monkeys from the Enclosure.
   * @param name - primate name.
   * @return - removed primate object.
   */
  @Override
  public Primates removeMonkey(String name) {
    Primates pTemp = null;
    for (Primates pp: troop) {
      if (Objects.equals(pp.getMonkeyName(), name)) {
        pTemp = pp;
        this.enclosureCapacity += 1;
        this.troop.remove(pp);
        if (troop.size() == 0) {
          reconfigure();
        }
        return pp;
      }
    }
    return null;
  }

  /**
   * Reconfigures the Enclosure to host new type of species.
   */
  private void reconfigure() {
    this.speciesType = "any";
  }

  /**
   * Returns the ID of the enclosure.
   * @return enclosure ID.
   */
  @Override
  public int getID() {
    return this.enclosureID;
  }

  /**
   * Returns the type of species housed.
   * @return species housed.
   */
  String getSpeciesType() {
    return this.speciesType;
  }

  /**
   * Returns the capacity of the Enclosure.
   * @return enclosure capacity.
   */
  int getEnclosureCapacity() {
    return this.enclosureCapacity;
  }

  /**
   * Returns the size of the enclosure at this time.
   * @return enclosure size
   */
  int getEnclosureSize() {
    return this.enclosureSize;
  }

  /**
   * Gives you the ability to expand the enclosure size.
   * @param size - size we want to expand to.
   */
  void setEnclosureSize(int size) {
    this.enclosureSize = size;
  }

  /**
   * Names of the monkeys in the enclosure.
   * @return names of the monkeys in the enclosure.
   */
  ArrayList<String> getMonkeyNames() {
    if (troop.size() == 0) {
      return new ArrayList<String>();
    }
    ArrayList<String> temp = new ArrayList<>();
    for (Primates p: troop) {
      temp.add(p.getMonkeyName());
    }
    return temp;
  }

  /**
   * Searches the troop for given primate and returns true/false.
   * @param p - primate object.
   * @return - boolean value.
   */
  boolean searchTroop(Primates p) {
    for (Primates pp: troop) {
      if (Objects.equals(pp.getMonkeyName(), p.getMonkeyName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a sign with information about enclosure housing.
   * @return information on where each species is housed.
   */
  ArrayList<ArrayList<String>> getSign() {
    ArrayList<ArrayList<String>> list = new ArrayList<>();
    for (Primates p: troop) {
      String name = p.getMonkeyName();
      ArrayList<String> row = new ArrayList<>();
      row.add(p.getMonkeyName());
      row.add(Character.toString(p.getSex()));
      row.add(p.getFavFood());
      list.add(row);
    }
    return list;
  }
}
