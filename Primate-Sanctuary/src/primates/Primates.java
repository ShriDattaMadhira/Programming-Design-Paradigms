package primates;

/**
 * Monkeys, Apes, Gorillas, and Humans are called Primates.
 */
public interface Primates {
  /**
   * Returns monkey name.
   * @return - monkey name.
   */
  public String getMonkeyName();

  /**
   * Returns monkey species.
   * @return - Species.
   */
  public String getSpecies();

  /**
   * Returns monkey's favorite food.
   * @return - favorite food.
   */
  public String getFavFood();

  /**
   * Returns quantity of food the monkey eats.
   * @return - monkey's food quantity.
   */
  public int getFoodQty();

  /**
   * Returns monkey size.
   * @return - monkey size.
   */
  public nmwSize getMonkeySize();

  /**
   * Returns monkey's space requirement.
   * @return - monkey's space requirement.
   */
  public int getSpaceReq();

  /**
   * Returns if monkey is male or female.
   * @return - sex.
   */
  public char getSex();

  /**
   * Returns monkey height.
   * @return - monkey height.
   */
  public int getWeight();

  /**
   * Returns monkey agea.
   * @return - monkey age.
   */
  public int getAge();

  /**
   * Sets monkey's favorite food.
   */
  public void setFavFood(String favFood);

  /**
   * Sets monkey's food quantity.
   */
  public void setFoodQty(int foodQty);

  /**
   * Sets monkey size.
   */
  public void setMonkeySize(nmwSize monkeySize);

  /**
   * Sets monkey space requirement.
   */
  public void setSpaceReq(nwmSpace spaceReq);

  /**
   * Sets monkey height.
   */
  public void setWeight(int height);

  /**
   * Sets monkey age.
   */
  public void setAge(int age);
}
