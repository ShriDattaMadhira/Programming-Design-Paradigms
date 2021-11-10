package driver;

import arena.Arena;
import arena.Games;
import arena.LiveStream;
import armory.WeaponFactory;
import player.Player;
import player.Players;

import java.util.Objects;
import java.util.Scanner;

class Driver {
  public static void main(String[] args) {
    System.out.println("==========================WELCOME TO FIGHT CLUB==========================");

    System.out.println("\nINITIALIZING THE CHARACTERS....");
    Players p1 = new Player(1);
    System.out.println("Player 1 details \n" + p1);
    Players p2 = new Player(2);
    System.out.println("Player 2 details \n" + p2);

    // Trying to copy a null object.
    try {
      Players p3 = null;
      Players p1Copy = new Player(p3);
    } catch (Exception e) {
      System.out.println("\nERROR MESSAGE: " + e.getMessage() + "\n");
    }

    // throws exception as player is already equipped with Katana before entering the arena.
    try {
      Players p1Copy = new Player(p1);
      p1Copy.setWeapon(new WeaponFactory().createKatana());

      Players p2Copy = new Player(p2);
      Games a1 = new Arena(p1Copy, p2Copy);
    } catch (Exception e) {
      System.out.println("ERROR MESSAGE: " + e.getMessage() + "\n");
    }

    // trying to send null player to arena.
    try {
      Players p1Copy = new Player(p1);
      Players p2Copy = new Player(p2);
      Players p3 = null;
      Games a1 = new Arena(p3, p2Copy);
    } catch (Exception e) {
      System.out.println("ERROR MESSAGE: " + e.getMessage() + "\n");
    }

    Scanner sc = new Scanner(System.in);
    String choice = "y";
    while (Objects.equals(choice, "y")) {
      System.out.println("\n====================================================================="
              + "===========================================================================");
      // everything goes as expected.
      Players p1Copy = new Player(p1);
      Players p2Copy = new Player(p2);
      Games a1 = new Arena(p1Copy, p2Copy);
      System.out.println("\nPlayers " + p1.getPlayerID() + " and " + p2.getPlayerID()
              + " are entering the arena...");
      System.out.println("\nPlayers getting equipped with gear and weapons...");
      System.out.println("\nLadies and Gentlemen..."
              + " Presenting you the players for tonight's fight...\n");
      System.out.println(a1);
      System.out.println("\n\nLet the battle begin....\n");
      LiveStream liveStream = new LiveStream();
      a1.startBattle();
      System.out.println(liveStream);
      System.out.println("\nThe winner of the battle between p1 and p2 is Player-"
              + liveStream.getWinner());

      System.out.println("\nUnsatisfied with the result. Do you want a re-match? [y-Yes, n-No]");
      choice = sc.next();
      System.out.println("\nThat is it for today. Hope you enjoyed the battle and see you soon...");
    }
  }
}
