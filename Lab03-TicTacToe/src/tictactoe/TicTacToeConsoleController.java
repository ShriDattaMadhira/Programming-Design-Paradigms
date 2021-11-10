package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   * 
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("m cannot be null");
    }
    boolean flag1 = true;
    while (flag1) {
      String ip;
      int[] coordinates = new int[2];

      try {
        out.append(m.toString()).append("\n");
        out.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
        boolean flag2 = true;
        while (flag2) {
          int i = 0;
          while (i < coordinates.length) {
            ip = scan.next();
            if (ip.equalsIgnoreCase("q")) {
              out.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
              flag1 = false;
              break;
            }
            try {
              coordinates[i] = Integer.parseInt(ip);
              i += 1;
            } catch (NumberFormatException e) {
              out.append("Not a valid number: ").append(ip).append("\n");
            }
          }
          if (!flag1) {
            break;
          }
          try {
            m.move(coordinates[0] - 1, coordinates[1] - 1);
            flag2 = false;
          } catch (IllegalArgumentException e) {
            out.append("Not a valid move: ").append(Integer.toString(coordinates[0])).append(", ")
                    .append((Integer.toString(coordinates[1]))).append("\n");
          }
        }
        if (m.isGameOver()) {
          out.append(m.toString()).append("\nGame is over! ");
          Player winner = m.getWinner();
          if (winner == null) {
            out.append("Tie game.\n");
          } else if (winner == Player.X) {
            out.append("X wins.\n");
          } else {
            out.append("O wins.\n");
          }
          break;
        }
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      } // end of IOException try
      // end of while(flag)
    }
    // end of method playGame()
  }
  // end of class
}
