package sanctuary;

import primates.Primates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * WELCOME TO THE SANCTUARY.
 */
public class Sanctuary implements ISanctuary {
  private final String sanctuaryName;
  private int totalEnclosures = 0;
  private int totalCages = 0;
  private ArrayList<Enclosure> enclosureList = new ArrayList<>();
  private ArrayList<Isolation> isolationList = new ArrayList<>();
  private ArrayList<Primates> primateList = new ArrayList<>();
  private HashMap<String, Integer> foodDetails = new HashMap<>();

  /**
   * Constructs the Sanctuary.
   * @param name - name of the sanctuary.
   * @param numEnclosures - number of enclosures initially.
   * @param numCages - number of cages initially.
   */
  public Sanctuary(String name, int numEnclosures, int numCages) {
    if (numEnclosures <= 0 || numCages <= 0) {
      throw new IllegalArgumentException(
              "total number of Enclosures and total number of Cages must be > 0");
    }
    this.sanctuaryName = name;
    totalEnclosures = numEnclosures;
    totalCages = numCages;
    createHousing(numEnclosures, numCages);
  }

  /**
   * Adds to the list of Enclosures of the Sanctuary.
   * @param enclosure Enclosure information.
   */
  private void addToEnclosureList(Enclosure enclosure) {
    enclosureList.add(enclosure);
  }

  /**
   * Adds to the list of Isolation cages of the Sanctuary.
   * @param isolation Cage information.
   */
  private void addToIsolationList(Isolation isolation) {
    isolationList.add(isolation);
  }

  /**
   * Creates enclosures and isolation cages and adds them to respective lists.
   * @param enc - number of enclosures.
   * @param cage - number of cages.
   */
  private void createHousing(int enc, int cage) {
    for (int i = 0; i < enc; i++) {
      addToEnclosureList(Enclosure.createEnclosure(i, 20, "any"));
    }

    for (int j = 0; j < cage; j++) {
      addToIsolationList(Isolation.createIsolation(j));
    }
  }

  /**
   * Return name of the sanctuary.
   * @return Sanctuary Name.
   */
  @Override
  public String getSanctuaryName() {
    return sanctuaryName;
  }

  /**
   * Return number of enclosures in the sanctuary.
   * @return total number of Enclosures.
   */
  @Override
  public int getTotalEnclosures() {
    return totalEnclosures;
  }

  /**
   * Return number of cages in the sanctuary.
   * @return total number of Cages.
   */
  @Override
  public int getTotalCages() {
    return totalCages;
  }

  /**
   * Returns food details of the Sanctuary.
   * @return Food Details.
   */
  @Override
  public HashMap<String, Integer> getFoodDetails() {
    return foodDetails;
  }

  /**
   * Adds Details of food and its quantities to the existing list.
   * @param food Food item to add.
   * @param quantity Quantity to add.
   */
  private void addToFoodDetails(String food, int quantity) {
    if (foodDetails.containsKey(food)) {
      this.foodDetails.put(food, foodDetails.get(food) + quantity);
    }
    else {
      this.foodDetails.put(food, quantity);
    }
  }

  /**
   * Increase the Enclosure count.
   * @param count Increase by count.
   */
  @Override
  public void incTotalEnclosures(int count) {
    for (int i = 0; i < count; i++) {
      Enclosure enclosure = new Enclosure(0, 100, "any");
      totalEnclosures += 1;
      addToEnclosureList(enclosure);
    }
  }

  /**
   * Increase the cage count.
   * @param count Increase by count.
   */
  @Override
  public void incTotalCages(int count) {
    for (int i = 0; i < count; i++) {
      Isolation isolation = new Isolation(0);
      totalCages += 1;
      addToIsolationList(isolation);
    }
  }

  /**
   * Ability to expand the enclosure.
   * @param encID - id of the enclosure to be updated.
   * @param size - size to which it should be updated.
   * @return - if the update is a success or failure.
   */
  @Override
  public boolean incEnclosureSize(int encID, int size) {
    for (Enclosure e: enclosureList) {
      if (e.getID() == encID && size > e.getEnclosureSize()) {
        e.setEnclosureSize(size);
        return true;
      }
    }
    return false;
  }

  private Isolation searchIsolation(String name) {
    for (Isolation i: isolationList) {
      if (!i.getIsEmpty() && Objects.equals(i.getPrimate().getMonkeyName(), name)) {
        return i;
      }
    }
    return null;
  }

  private Primates searchMonkey(String name) {
    for (Primates p: primateList) {
      if (Objects.equals(p.getMonkeyName(), name)) {
        return p;
      }
    }
    return null;
  }

  private Enclosure searchEnclosure(String name) {
    for (Enclosure e: enclosureList) {
      Primates pTemp = searchMonkey(name);
      if (pTemp != null && Objects.equals(pTemp.getSpecies(), e.getSpeciesType())
              && e.searchTroop(pTemp)) {
        return e;
      }
    }
    return null;
  }

  /**
   * Adds a new monkey to Isolation for checkup.
   * @param p - Primate object.
   * @return - True if monkey is added, else False.
   */
  public boolean newMonkey(Primates p) {
    for (Isolation i: isolationList) {
      if (i.addMonkey(p)) {
        primateList.add(p);
        addToFoodDetails(p.getFavFood(), p.getFoodQty());
        return true;
      }
    }
    throw new IllegalStateException("Sorry!! No Space in Isolation for a new monkey. "
            + "Looking for space in other Sanctuaries...");
  }

  /**
   * Moves monkey to Enclosure.
   * @param name - monkey name.
   * @throws IllegalStateException - several exceptions thrown.
   */
  @Override
  public void moveToEnclosure(String name) {
    Enclosure eTemp = searchEnclosure(name);
    if (eTemp != null) {
      throw new IllegalStateException(" Monkey already in Enclosure. Enclosure Number: "
              + eTemp.getID());
    } else {
      Isolation iTemp = searchIsolation(name);
      if (iTemp == null) {
        throw new IllegalStateException("This monkey is not one of our sanctuary monkeys.");
      } else {
        Primates pTemp = iTemp.removeMonkey(name);
        boolean flag = false;
        for (Enclosure e: enclosureList) {
          if ((Objects.equals(e.getSpeciesType(), pTemp.getSpecies())
                  && e.getEnclosureCapacity() > 0) || Objects.equals(e.getSpeciesType(), "any")) {
            flag = e.addMonkey(pTemp);
            break;
          }
        }
        if (!flag) {
          throw new IllegalStateException(" No space for monkey in Enclosure. ");
        }
      }
    }
  }

  /**
   * Moves monkey to Isolation.
   * @param name - monkey name.
   * @throws IllegalStateException - several exceptions thrown.
   */
  @Override
  public void moveToIsolation(String name) {
    Isolation iTemp = searchIsolation(name);
    if (iTemp != null) {
      throw new IllegalStateException(" Monkey already in Isolation. Cage Number: "
              + iTemp.getID());
    } else {
      Enclosure eTemp = searchEnclosure(name);
      if (eTemp == null) {
        throw new IllegalStateException("This monkey is not one of ours.");
      } else {
        Primates pTemp = eTemp.removeMonkey(name);
        boolean flag = false;
        for (Isolation i: isolationList) {
          if (i.getIsEmpty()) {
            i.addMonkey(pTemp);
            flag = true;
            break;
          }
        }
        if (!flag) {
          throw new IllegalStateException(" No space for monkey in Isolation. ");
        }
      }
    }
  }

  /**
   * Returns the locations of particular species in the Sanctuary.
   * @param species - Species to search.
   * @return - locations where they are housed.
   */
  @Override
  public ArrayList<String> searchSpecies(String species) {
    ArrayList<String> locations = new ArrayList<>();
    String lower = species.toLowerCase();
    for (Enclosure e: enclosureList) {
      if (Objects.equals(e.getSpeciesType(), lower)) {
        locations.add("Enclosure-" + e.getID());
      }
    }
    for (Isolation i: isolationList) {
      if (i.getPrimate() != null) {
        if (Objects.equals(i.getPrimate().getSpecies(), lower)) {
          locations.add("Isolation-" + i.getID());
        }
      }
    }
    return locations;
  }

  /**
   * Returns the list of all species housed in the sanctuary.
   * @return set of all species.
   */
  @Override
  public TreeMap<String, ArrayList<String>> getAllSpecies() {
    TreeMap<String, ArrayList<String>> allSpecies = new TreeMap<>();
    ArrayList<String> list;
    for (Enclosure e: enclosureList) {
      if (!Objects.equals(e.getSpeciesType(), "any")) {
        if (allSpecies.containsKey(e.getSpeciesType())) {
          list = allSpecies.get(e.getSpeciesType());
          list.add("Enclosure-" + e.getID());
        } else {
          list = new ArrayList<>();
          list.add("Enclosure-" + e.getID());
          allSpecies.put(e.getSpeciesType(), list);
        }
      }
    }
    for (Isolation i: isolationList) {
      if (i.getPrimate() != null) {
        String sp = i.getPrimate().getSpecies();
        if (allSpecies.containsKey(sp)) {
          list = allSpecies.get(sp);
          list.add("Isolation-" + i.getID());
        } else {
          list = new ArrayList<>();
          list.add("Isolation-" + i.getID());
          allSpecies.put(sp, list);
        }
      }
    }
    return allSpecies;
  }

  /**
   * Returns the list of Monkey Names alphabetically.
   * @return - list of monkeyNames.
   */
  @Override
  public TreeMap<String, String> getAllMonkeys() {
    TreeMap<String, String> monkeyNames = new TreeMap<>();
    for (Enclosure e: enclosureList) {
      ArrayList<String> temp = e.getMonkeyNames();
      for (String s: temp) {
        monkeyNames.put(s, "Enclosure-" + e.getID());
      }
    }
    for (Isolation i: isolationList) {
      if (i.getPrimate() != null) {
        monkeyNames.put(i.getPrimate().getMonkeyName(), "Isolation-" + i.getID());
      }
    }
    return monkeyNames;
  }

  /**
   * Returns the shopping list for the sanctuary.
   * @return - shopping list.
   */
  @Override
  public HashMap<String, Integer> getShoppingList() {
    return getFoodDetails();
  }

  /**
   * Returns the sign for a given enclosure.
   * @param encID - enclosure identifier.
   * @return - Sign of primate details of that enclosure.
   * @throws IllegalArgumentException - invalid enclosure identifier.
   */
  @Override
  public ArrayList<ArrayList<String>> produceSign(int encID) {
    for (Enclosure e: enclosureList) {
      if (e.getID() == encID) {
        if (Objects.equals(e.getSpeciesType(), "any")) {
          return new ArrayList<>();
        }
        return e.getSign();
      }
    }
    throw new IllegalArgumentException("Invalid Enclosure specified.");
  }

  /**
   * Returns the quantity available for a given food item.
   * @param item - food item to be searched.
   * @return - quantity of food available for that food item.
   * @throws IllegalArgumentException - for food item not present in the list.
   */
  @Override
  public Integer searchFood(String item) {
    for (Map.Entry<String, Integer> entry: foodDetails.entrySet()) {
      if (Objects.equals(entry.getKey(), item)) {
        return entry.getValue();
      }
    }
    throw new IllegalArgumentException("Requested food item not present in the sanctuary food "
            + "list.");
  }

}
