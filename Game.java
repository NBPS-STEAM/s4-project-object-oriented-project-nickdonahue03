import java.util.Scanner;
import java.util.ArrayList;

class Game {

  public Scanner Input = new Scanner(System.in);
  private ArrayList<Player> Profiles = new ArrayList<Player>();
  private ArrayList<gameBoard> Boards = new ArrayList<gameBoard>();

  public Game() {
    clearScreen();
    // create the first player profile.
    System.out.println("WELCOME TO TIC-TAC-TOE!");
    pause(2500);
    System.out.println("What is your name?\n");
    
    String playerName = Input.nextLine();
    if (playerName.equals("")) {
      Profiles.add(new Player());
    } else {
      Profiles.add(new Player(playerName));
    }

    runMenu();
  }

  private void runMenu() {
    clearScreen();
    System.out.println("WELCOME TO TIC-TAC-TOE!\n\nEnter an option's number: (ex. 1, 2, ...)");
    System.out.println("1. Play");
    System.out.println("2. Create a profile");
    System.out.println("3. View all profiles");

    String menuChoice = Input.nextLine();

    if (menuChoice.equals("1")) {  // send to the Play method
      runBoard();
    } else if (menuChoice.equals("2")) { // create a new profile
      runProfileCreation();
    } else if (menuChoice.equals("3")) { // view all existing profiles
      runViewProfiles();
    } else { // if no option is chosen, reset
      runMenu();
    }

  }

  private void runBoard() {
    if (Profiles.size() == 1) { // check if only one profile exists
      clearScreen();
      System.out.println("What is the name of your opponent?\n");

      String playerName = Input.nextLine();
      if (playerName.equals("")) {
        Profiles.add(new Player());
      } else {
        Profiles.add(new Player(playerName));
      }     
    }

    // select profiles for the match
    clearScreen();
    System.out.println("Match Creation\n");
    System.out.println("Enter an profile's number: (ex. 1, 2, ...)");

    // load all available profiles to the screen.
    for (int i = 0; i < Profiles.size(); i++) {
      System.out.println((i+1) + ". " + Profiles.get(i).getName());
    }

    System.out.println("8. return to the main menu\n");
    System.out.println("Select Player 1: (ex. 1, 2, ...)\n");
    int matchChoice1 = Input.nextInt();
    int matchChoice2;

    // verify player choices
    if (matchChoice1 == 8) { // return to the menu
      runMenu();
    } else if (matchChoice1 >= 1 && matchChoice1 <= (Profiles.size() + 1)) {
      System.out.println("Select Player 2: (ex. 1, 2, ...)\n");
      matchChoice2 = Input.nextInt(); 
      if (matchChoice2 == 8) { // return to the menu
        runMenu();
      } else if (matchChoice2 >= 1 && matchChoice2 <= (Profiles.size() + 1)) {
        gameBoard newBoard = new gameBoard(Profiles.get(matchChoice1 - 1), Profiles.get(matchChoice2 - 1));
        Boards.add(newBoard);
        newBoard.run();
        pause(5000);
        runMenu();        
      } else {
        runBoard();
      }
    } else {
      runBoard();
    }
  }

  private void runProfileCreation() {
    runMenu();
  }

  private void runViewProfiles() {
    runMenu();
  }
  
  public void Run() {
  }
  /*
    clearScreen();
    https://stackoverflow.com/questions/2979383/java-clear-the-console
  */
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