package player;

import java.util.Arrays;
import java.util.Random;

/**
 * Generates random numbers that are not so random.
 */
public class RandomGenerator {

  private Random rand;
  private int[] seeds;
  private int count = 0;

  /**
   * Constructor when the user only inputs one seed instead of varargs.
   * @param seed - integer value.
   */
  public RandomGenerator(int seed) {
    if (seed < 0) {
      rand = new Random();
    } else {
      rand = new Random(seed);
    }
  }

  /**
   * Constructor for getting the same values everytime.
   * @param vals - vararg parameter for the numbers we want repeatedly.
   */
  public RandomGenerator(int... vals) {
    Arrays.sort(vals);
    this.seeds = vals;
  }

  /**
   * A single integer value from the seeds provided earlier by the user.
   * @return - integer.
   */
  public int returnNumber() {
    count += 1;
    if (count > seeds.length) {
      count = 1;
    }
    return seeds[count - 1];
  }

}
