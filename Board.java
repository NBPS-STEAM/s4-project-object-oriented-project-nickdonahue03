import java.util.Scanner;
import java.util.ArrayList;

class Board {
  private ArrayList<Integer> gameBoard = new ArrayList<Integer>(); // create an array for the game board.
  public Board() {
    for (int i = 0; i < 9; i++) {
      gameBoard.add(0);
    }
  }
}