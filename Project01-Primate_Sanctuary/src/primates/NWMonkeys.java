package primates;

import java.util.ArrayList;
import java.util.Objects;

/**
 * New World Monkeys.
 */
public class NWMonkeys implements Primates {
  private final String monkeyName;
  private final String species;
  private String favFood;
  private int foodQty;
  private nmwSize monkeySize;
  private int spaceReq;
  private final char sex;
  private int weight;
  private int age;
  private static ArrayList<String> namesList = new ArrayList<>();

  /**
   * Constructor for nwMonkeys class.
   * @param monkeyName - name of the monkey.
   * @param species - species of the monkey.
   * @param favFood - favorite food of the monkey.
   * @param foodQty - quantity of food for the monkey.
   * @param monkeySize - size of the monkey.
   * @param spaceReq - space required by the monkey.
   * @param sex - sex of the monkey.
   * @param weight - height of the monkey.
   * @param age - age of the monkey.
   * @throws IllegalArgumentException - invalid arguments.
   */
  public NWMonkeys(String monkeyName, String species, String favFood, int foodQty,
                   nmwSize monkeySize, nwmSpace spaceReq, char sex, int weight, int age) {
    if (!checkUniqueness(monkeyName)) {
      throw new IllegalArgumentException(monkeyName
              + " is a name of other monkey. Name should be unique");
    }
    if (Character.toLowerCase(sex) != 'm' && Character.toLowerCase(sex) != 'f') {
      throw new IllegalArgumentException(" Invalid sex for monkey. Only m/f allowed. ");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight should be > 0.");
    }
    if (age < 0) {
      throw new IllegalArgumentException("Age cannot be negative.");
    }
    this.monkeyName = monkeyName;
    namesList.add(monkeyName);
    this.species = species.toLowerCase();
    this.favFood = favFood;
    this.foodQty = foodQty;
    this.monkeySize = monkeySize;
    this.spaceReq = spaceReq.getSpaceRequirement();
    this.sex = Character.toLowerCase(sex);
    this.weight = weight;
    this.age = age;
  }

  private boolean checkUniqueness(String name) {
    if (namesList.size() != 0) {
      for (String s : namesList) {
        if (Objects.equals(s, name)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns monkey name.
   * @return - monkey name.
   */
  @Override
  public String getMonkeyName() {
    return monkeyName;
  }

  /**
   * Returns monkey species.
   * @return - Species.
   */
  @Override
  public String getSpecies() {
    return species;
  }

  /**
   * Returns monkey's favorite food.
   * @return - favorite food.
   */
  @Override
  public String getFavFood() {
    return favFood;
  }

  /**
   * Returns quantity of food the monkey eats.
   * @return - monkey's food quantity.
   */
  @Override
  public int getFoodQty() {
    return foodQty;
  }

  /**
   * Returns monkey size.
   * @return - monkey size.
   */
  @Override
  public nmwSize getMonkeySize() {
    return monkeySize;
  }

  /**
   * Returns monkey's space requirement.
   * @return - monkey's space requirement.
   */
  @Override
  public int getSpaceReq() {
    return spaceReq;
  }

  /**
   * Returns if monkey is male or female.
   * @return - sex.
   */
  @Override
  public char getSex() {
    return sex;
  }

  /**
   * Returns monkey weight.
   * @return - monkey weight.
   */
  @Override
  public int getWeight() {
    return weight;
  }

  /**
   * Returns monkey ages.
   * @return - monkey age.
   */
  @Override
  public int getAge() {
    return age;
  }

  /**
   * Sets monkey's favorite food.
   */
  @Override
  public void setFavFood(String favFood) {
    this.favFood = favFood;
  }

  /**
   * Sets monkey's food quantity.
   */
  @Override
  public void setFoodQty(int foodQty) {
    this.foodQty = foodQty;
  }

  /**
   * Sets monkey size.
   */
  @Override
  public void setMonkeySize(nmwSize monkeySize) {
    this.monkeySize = monkeySize;
  }

  /**
   * Sets monkey space requirement.
   */
  @Override
  public void setSpaceReq(nwmSpace spaceReq) {
    this.spaceReq = spaceReq.getSpaceRequirement();
  }

  /**
   * Sets monkey weight.
   */
  @Override
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Sets monkey age.
   */
  @Override
  public void setAge(int age) {
    this.age = age;
  }
}
