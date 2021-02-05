public class App {
  public static void main(String[] args) {
    Game GameClass = new Game(); // Screen class (main game code)
    
    // Game Loop
    while (true) {
      GameClass.Run();
    }
  }
}
