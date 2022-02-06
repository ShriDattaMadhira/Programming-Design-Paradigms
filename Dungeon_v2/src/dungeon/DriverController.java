package dungeon;

import java.io.InputStreamReader;

/**
 * Driver that provides the necessary information for the controller.
 */
public class DriverController {
  /**
   * Main method of the driver.
   * @param args - arguments to construct the dungeon.
   */
  public static void main(String[] args) {
    int width = Integer.parseInt(args[0]);
    int height = Integer.parseInt(args[1]);
    boolean wrap = Integer.parseInt(args[2]) == 1;
    int interconnectivity = Integer.parseInt(args[3]);
    int treasure_pcnt = Integer.parseInt(args[4]);
    int noOfOtyughs = Integer.parseInt(args[5]);

    System.out.println("width = " + width + "\nheight = " + height + "\nwrapping = " + wrap
            + "\ninterconnectivity = " + interconnectivity + "\ntreasure percentage = "
            + treasure_pcnt + "\nnumber of otyughs = " + noOfOtyughs + "\n");

    try {
      Dungeon d = new AdvancedDungeon(width, height, wrap, interconnectivity, treasure_pcnt,
              noOfOtyughs);
      AdvDungeonController controller = new AdvDungeonConsoleController(
              new InputStreamReader(System.in), System.out);
      controller.playGame(d);
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage() + "\n");
    }
  }
}
