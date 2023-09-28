package map;

import utils.Coordinates;

public class Treasure extends Case {

  private int numberOfTreasures;

  public Treasure(Coordinates coordinates, int numberOfTreasures) {
    super(coordinates);
    this.numberOfTreasures = numberOfTreasures;
  }

  public void decreaseNumberOfTreasures() {
    this.numberOfTreasures--;
  }

  public int getNumberOfTreasures() {
    return numberOfTreasures;
  }

  @Override
  public String toString() {
    return this.getAdventurer() == null ? "T(" + numberOfTreasures + ')' : "A(" + this.getAdventurer().getName() + ")";
  }
}
