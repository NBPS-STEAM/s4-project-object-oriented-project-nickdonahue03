class Player {
  private int PlayerScore;
  private String Name;

  public Player() {
    Name = "Unknown Player Name";
    PlayerScore = 0;
  }
  
  public Player(String nameV) {
    Name = nameV;
    PlayerScore = 0;
  }

  // getters
  public String getName() { return Name; }
  public int getScore() { return PlayerScore; }

  // setters
  public void updateName(String nameV) {
    Name = nameV;
  }

  public void setScore(int scoreV) {
    PlayerScore = scoreV;
  }

  public void incrementScore(int scoreV) {
    PlayerScore = PlayerScore + scoreV;
  }
}