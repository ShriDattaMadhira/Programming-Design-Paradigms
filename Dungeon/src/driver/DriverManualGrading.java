package driver;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonImpl;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Driver class runs the program and simulates a user.
 * This should be used while manual grading by the TA or the professor as it has the ability for
 * user inputs at every step.
 */
public class DriverManualGrading {
  /**
   * Main method to test our model.
   * @param args - args.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int width;
    System.out.println("Enter the width of your dungeon (should be > 0).");
    width = Integer.parseInt(sc.next());

    int height;
    System.out.println("Enter the height of your dungeon (should be > 0).");
    height = Integer.parseInt(sc.next());

    boolean wrap;
    System.out.println("Enter if your dungeon is wrapping or not [1-Wrapping, 0-Not Wrapping].");
    int temp = Integer.parseInt(sc.next());
    if (temp == 1) {
      wrap = true;
    } else {
      wrap = false;
    }

    int interconnectivity;
    System.out.println("Enter the interconnectivity of your dungeon (should be >= 0).");
    interconnectivity = Integer.parseInt(sc.next());

    int treasure_pcnt;
    System.out.println("Enter the treasure percentage in your dungeon (should be > 0).");
    treasure_pcnt = Integer.parseInt(sc.next());

    try {
      Dungeon d = new DungeonImpl(width, height, wrap, interconnectivity, treasure_pcnt);
      String direction;
      String pick;
      System.out.println("START LOCATION: " + Arrays.toString(d.getStartLocation()) + " ||| "
              + "END LOCATION: " + Arrays.toString(d.getEndLocation()) + "\n");
      System.out.println(d.getGameDetails());
      try {
        while (!d.isGameOver()) {
          System.out.println("What direction you want to go next?");
          direction = sc.next();
          System.out.println("Do you want to pick the treasure at that location? [yes/no]");
          pick = sc.next();
          switch (direction.toLowerCase()) {
            case "north":
              d.play(Direction.NORTH, pick);
              break;
            case "south":
              d.play(Direction.SOUTH, pick);
              break;
            case "east":
              d.play(Direction.EAST, pick);
              break;
            case "west":
              d.play(Direction.WEST, pick);
              break;
            default:
              throw new IllegalArgumentException("Direction can only be North/South/East/West.");
          }
          System.out.println("\n" + d.getGameDetails());
        }
        System.out.println("\nYou are the end location. GAME OVER!");
      } catch (Exception e) {
        System.out.println("\n" + e.getMessage());
      }
    } catch (Exception e) {
      System.out.println("\n" + e.getMessage());
    }
  }
  // end of class.
}
