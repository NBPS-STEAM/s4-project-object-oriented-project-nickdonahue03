import java.util.Scanner;
import java.util.ArrayList;

class Game {
  public Scanner Input = new Scanner(System.in);
  private ArrayList<Player> Profiles = new ArrayList<Player>();

  public Game() {
    clearScreen();
    System.out.println("WELCOME TO TIC-TAC-TOE!");
    pause(2500);
    System.out.println("What is your name?\n");
    String playerName = Input.nextLine();
    if playerName.equals("") {
      Profiles.add(new Player());
    } else {
      Profiles.add(new Player(playerName));
    }
  }

  public void runMenu() {
    clearScreen();
    System.out.println("WELCOME TO TIC-TAC-TOE!\n\nEnter an option's number: (ex. 1, 2, ...)");
    pause(2500);
    System.out.println("1. Play");
    System.out.println("2. Create a profile");
    System.out.println("3. View all profiles");
  }

  public void Run() {
  }
  /*
    clearScreen();
    https://stackoverflow.com/questions/2979383/java-clear-the-console
  */
  public static void clearScreen() {  
      System.out.print("\033[H\033[2J");  
      System.out.flush();  
  }  

    /*
    pause();
    https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java
  */
  public static void pause(int ms) {
    try {
        Thread.sleep(ms);
    } catch (InterruptedException e) {
        System.err.format("IOException: %s%n", e);
    }
  }
}