import java.util.Scanner;
import java.util.ArrayList;

class gameBoard {
  private ArrayList<Integer> Board = new ArrayList<Integer>(); // create an array for the game board.
  private ArrayList<Player> PlayerNames = new ArrayList<Player>();

  public Scanner Input = new Scanner(System.in);

  public gameBoard(Player Player1, Player Player2) {
    PlayerNames.add(Player1);
    PlayerNames.add(Player2);

    // create 9 empty values in our list of integers, gameBoard.
    for (int i = 0; i < 9; i++) {
      Board.add(0); 
    }
  }

  // methods
  public void run() {
    clearScreen();
    boolean Playing = true; // debounce to keep the while loop running
    boolean player = true; // track which players turn it is

    // game loop
    while (Playing) {
      boolean correctSpot = false;
      while (!correctSpot) { // loop to make sure that the current player enters a valid board spot
        drawBoard();
        System.out.println((player ? PlayerNames.get(0) : PlayerNames.get(1)).getName() + " pick a spot!");
        int slot = getSlot(Input.nextLine().toLowerCase());
        if (slot != 99 && Board.get(slot) == 0) {
          if (player) {
            Board.set(slot, 1);
            player = false;
          } else {
            Board.set(slot, 2);
              player = true;
          }
          correctSpot = true;
        }
      }

      // winner check
      int Winner = checkForWinner();
      if (Winner == 1 || Winner == 2) {
        Playing = false;
        pause(2500);
        clearScreen();
        System.out.println((PlayerNames.get(Winner - 1).getName().toUpperCase())+ " WINS!");
      } else if (Winner == 3) {
        Playing = false;
        pause(2500);
        clearScreen();
        System.out.println(PlayerNames.get(0).getName().toUpperCase() + " and " + PlayerNames.get(1).getName().toUpperCase() + " WIN... BECAUSE ITS A TIE!!!!");
      }
    }
  }
  
  private int getSlot(String inp) {  // finds the slot for a given input (a1, b1, c1, ...)
    
    String[] sp = inp.split("(?!^)");/*https://stackoverflow.com/questions/5235401/split-string-into-array-of-character-strings*/

    if (sp.length != 2) {
      return 99;
    }

    int nmbr = 0;
    // check the row
    if (sp[0].equals("a")) { // row 1: values 0, 1, 2
      nmbr += 0;
    } else if (sp[0].equals("b")) { // row 2: values 3, 4, 5
      nmbr += 3;
    } else if (sp[0].equals("c")) { // row 3: values 6, 7, 8
      nmbr += 6;
    } else {
      return 99;
    }
    
    int parsed = Integer.parseInt(sp[1]);

    if (parsed > 3 || parsed == 0) {
      return 99;
    }

    nmbr += parsed - 1;  // add the column value
    return nmbr;
  }
  private int checkForWinner() {
    ArrayList<Integer> brd = getBoard(); 
      // winner check
      // loops through the current board to see if someone has won

      // getting the value for each slot
      int Slot1 = brd.get(0); // get is a method of ArrayList
      int Slot2 = brd.get(1);
      int Slot3 = brd.get(2);
      int Slot4 = brd.get(3);
      int Slot5 = brd.get(4);
      int Slot6 = brd.get(5);
      int Slot7 = brd.get(6);
      int Slot8 = brd.get(7);
      int Slot9 = brd.get(8);

      // row check
      if (Slot1 == Slot2 && Slot1 == Slot3) {
        return Slot1;  // return the player who won
      }
      if (Slot4 == Slot5 && Slot4 == Slot6) {
        return Slot4; // return the player who won
      }
      if (Slot7 == Slot8 && Slot7 == Slot9) {
        return Slot7;  // return the player who won
      }
      // column check
      if (Slot1 == Slot4 && Slot1 == Slot7) {
        return Slot1; // return the player who won
      }
      if (Slot2 == Slot5 && Slot2 == Slot8) {
        return Slot2; // return the player who won
      }
      if (Slot3 == Slot6 && Slot3 == Slot9) {
        return Slot3; // return the player who won
      }

      // cross check
      if (Slot1 == Slot5 && Slot1 == Slot9) {
        return Slot1; // return the player who won
      }
      if (Slot3 == Slot5 && Slot3 == Slot7) {
        return Slot3; // return the player who won
      }

      // check for tie
      for (int i = 0; i < brd.size(); i++) {
        if (brd.get(i) == 0) {
          return 0;  // game isnt over yet
        }
      }
      return 3;  // tie
    }
  public void drawBoard(){
    /*
      Draws the board to the console
    */

    ArrayList<Integer> brd = getBoard();

    clearScreen();

    /*
    | | | |
    | | | |
    | | | |
    */
    String p = "";  // what to print out
    for (int i = 0; i < brd.size(); i++) {  // looping through the board
      String put = "";  // what will appear in the console
      int z = brd.get(i);  // value of the current square
      if (z == 1) {  // value is 1 which means it is an x square
        put = "x";
      } else if (z == 2) {  // value is 2 which means it is an o square
        put = "o";
      } else if (z == 0) {  // value is 0 which means it hasn't been claimed yet.
        // finding the row and column
        if (i == 0  || i == 1 || i == 2) {
          put = "a" + (i+1);
        }
        if (i == 3  || i == 4 || i == 5) {
          put = "b" + ((i-3) + 1);
        }
        if (i == 6  || i == 7 || i == 8) {
          put = "c" + ((i-6) + 1);
        }
      }

      // formatting
      p += "| " + put + " ";
      if (i == 2 || i == 5 || i == 8) {
        p += "|\n"; // closing off rows.

      }
      //System.out.println(i + " " + brd.get(i));
    }
    System.out.println(PlayerNames.get(0).getName() + " v. " + PlayerNames.get(1).getName());
    System.out.println(p);  // display the board
  }
  // getters
  public ArrayList<Integer> getBoard() { return Board; }
  public Player getPlayer1() { return PlayerNames.get(0); }
  public Player getPlayer2() { return PlayerNames.get(1); }
  
  private static void clearScreen() {  
      System.out.print("\033[H\033[2J");  
      System.out.flush();  
  }  

    /*
    pause();
    https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
  */
  private static void pause(int ms) {
    try {
        Thread.sleep(ms);
    } catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e);
    }
  }
}