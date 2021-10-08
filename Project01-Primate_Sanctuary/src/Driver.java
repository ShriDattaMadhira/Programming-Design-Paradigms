import primates.NWMonkeys;
import primates.Primates;
import primates.nmwSize;
import primates.nwmSpace;
import sanctuary.ISanctuary;
import sanctuary.Sanctuary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * The driver class.
 */
public class Driver {

  /**
   * The main method.
   */
  public static void main(String[] args) {
    ISanctuary s = new Sanctuary("Jungle Friends Primate Sanctuary", 3,
            3);
    System.out.println("Sanctuary Name: " + s.getSanctuaryName());
    System.out.println("Number of Isolation cages in the sanctuary: " + s.getTotalCages());
    System.out.println("Number of Enclosures in the sanctuary: " + s.getTotalEnclosures());
    System.out.println("\n");

    System.out.println("Adding the newly acquired monkeys (m-01, m-02, m-03) "
            + "to isolation for further checkups.");

    Primates p1 = new NWMonkeys("m-01", "Driller", "nuts",
            90, nmwSize.SMALL, nwmSpace.SMALL, 'M', 10, 2);
    s.newMonkey(p1);
    Primates p2 = new NWMonkeys("m-02", "Baboon", "meat",
            550, nmwSize.LARGE, nwmSpace.LARGE, 'M', 50, 5);
    s.newMonkey(p2);
    Primates p3 = new NWMonkeys("m-03", "Driller", "leaves",
            150, nmwSize.SMALL, nwmSpace.SMALL, 'F', 8, 3);
    s.newMonkey(p3);

    System.out.println("Present locations of monkeys: " + s.getAllMonkeys() + "\n");

    System.out.println("m-03 is cleared from medical observation and "
            + "is being moved to Enclosure.\n");
    s.moveToEnclosure(p3.getMonkeyName());

    s.moveToEnclosure(p1.getMonkeyName());
    s.moveToEnclosure(p2.getMonkeyName());
    System.out.println("m-01 and m-02 are cleared from medical observation and "
            + "are being moved to Enclosure.");
    System.out.println("Present position of monkeys: " + s.getAllMonkeys() + "\n");

    // Report point - Get All Species.
    TreeMap<String, ArrayList<String>> allSp = s.getAllSpecies();
    System.out.println("-----------ALL SPECIES AND THEIR LOCATIONS IN THE SANCTUARY------------");
    System.out.println(allSp + "\n");

    // get all monkeys.
    System.out.println("-----------ALL MONKEYS AND THEIR LOCATIONS IN THE SANCTUARY------------");
    TreeMap<String, String> monkeys = s.getAllMonkeys();
    System.out.println(monkeys + "\n");

    // look up where species is housed point - search species.
    System.out.println("-------------------LOOK UP SPECIES IN THE SANCTUARY--------------------");
    String sp = "driller";
    ArrayList<String> lookupSp = s.searchSpecies(sp);
    if (lookupSp.size() == 0) {
      System.out.println("Unfortunately, this species is not being housed in this Sanctuary.");
    }
    else {
      System.out.print(sp.toUpperCase() + " is housed in: ");
      for (String s2: lookupSp) {
        System.out.print(s2 + " ");
      }
      System.out.println("\n");
    }

    // Shopping list.
    System.out.println("\n-------------------SHOPPING LIST FOR THE SANCTUARY-------------------");
    HashMap<String, Integer> list = s.getShoppingList();
    System.out.println(list + "\n");

    try {
      // Produce Sign for a given Enclosure.
      System.out.println("------------------------SIGN FOR GIVEN ENCLOSURE-----------------------");
      int enc = 0;
      ArrayList<ArrayList<String>> ps = s.produceSign(enc);
      if (ps.size() == 0) {
        System.out.println("Unfortunately the enclosure specified (Enclosure-" + enc
                + ") is empty at the moment.\n");
      }
      else {
        System.out.println("Enclosure-" + enc + " houses: ");
        System.out.println(ps + "\n");
      }

      enc = 3;
      ps = s.produceSign(enc);
    } catch (Exception e) {
      System.out.println("EXCEPTION : " + e.getMessage() + "\n");
    }

    try {
      // search for food.
      System.out.println("------------------------SEARCH FOR FOOD-----------------------");
      String food = "meat";
      System.out.println("Quantity of " + food + " available: " + s.searchFood(food));
    } catch (Exception e) {
      System.out.println("EXCEPTION : " + e.getMessage() + "\n");
    }
  }
}
