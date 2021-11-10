package driver;

import java.util.Arrays;
import java.util.Scanner;

import dungeon.Direction;
import dungeon.Dungeon;
import dungeon.DungeonImpl;

/**
 * Driver class runs the program and simulates a user.
 * This driver class is for example runs where the movement of the player is hardcoded according to
 * what requirement I wanted to show in that run.
 * The runs and what they demonstrate are written below in the form of comments for better
 * understanding.
 */
public class DriverExampleRuns {

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

    int treasurePcnt;
    System.out.println("Enter the treasure percentage in your dungeon (should be > 0).");
    treasurePcnt = Integer.parseInt(sc.next());

    try {
      Dungeon d = new DungeonImpl(width, height, wrap, interconnectivity, treasurePcnt);
      System.out.println("START LOCATION: " + Arrays.toString(d.getStartLocation()) + " ||| "
              + "END LOCATION: " + Arrays.toString(d.getEndLocation()) + "\n");
      System.out.println(d.getGameDetails());
      // RUN - 1 Player movements. Comment one of the runs out for correct execution.
      // EXAMPLE RUN - 1: This run demonstrates the following,
      // 1. A wrapping dungeon,
      // 2. The player starting at the start and reaching the end, and
      // 3. The player's location and description at each step.
      System.out.println("The below sequence of consecutive west and east directions will show you "
              + "that the dungeon is wrapping.");
      System.out.println("====================================================");
      d.play(Direction.WEST, "yes");
      System.out.println(d.getGameDetails());
      d.play(Direction.WEST, "no");
      System.out.println(d.getGameDetails());
      d.play(Direction.WEST, "no");
      System.out.println(d.getGameDetails());
      d.play(Direction.EAST, "yes");
      System.out.println(d.getGameDetails());
      d.play(Direction.EAST, "no");
      System.out.println(d.getGameDetails());
      d.play(Direction.EAST, "no");
      System.out.println(d.getGameDetails());
      System.out.println("====================================================");
      d.play(Direction.SOUTH, "yes");
      System.out.println(d.getGameDetails());
      d.play(Direction.SOUTH, "no");
      System.out.println(d.getGameDetails());
      d.play(Direction.SOUTH, "yes");
      System.out.println(d.getGameDetails());
      d.play(Direction.SOUTH, "yes");
      System.out.println(d.getGameDetails());
      d.play(Direction.EAST, "yes");
      System.out.println(d.getGameDetails());
      d.play(Direction.EAST, "no");
      System.out.println(d.getGameDetails());
      d.play(Direction.EAST, "no");
      System.out.println(d.getGameDetails());

      // RUN - 2 Player movements. Comment one of the runs out for correct execution.
      // EXAMPLE RUN - 2: This run demonstrates the following,
      // 1. A non-wrapping dungeon,
      // 2. The player visiting every location in the dungeon, and
      // 3. The player's location and description at each step.
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no"); //0,3
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no"); //1,0
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no"); //3,4
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no"); //2,2
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no"); //5,2
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no"); //4,4
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no"); //2,2
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.WEST, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.NORTH, "no");
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.EAST, "no"); //4,1
    //      System.out.println(d.getGameDetails());
    //      d.play(Direction.SOUTH, "no");// 5,1 - END LOCATION
    //      System.out.println(d.getGameDetails());
    //      System.out.println("\nYou are the end location. GAME OVER!");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
