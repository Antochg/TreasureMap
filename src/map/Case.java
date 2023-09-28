package map;

import adventurer.Adventurer;
import utils.Coordinates;

public class Case {

  private Coordinates coordinates;
  private Adventurer adventurer = null;

  public Case(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Adventurer getAdventurer() {
    return adventurer;
  }

  public void setAdventurer(Adventurer adventurer) {
    this.adventurer = adventurer;
  }

  @Override
  public String toString() {
    return this.getAdventurer() == null ? "." : "A(" + this.getAdventurer().getName() + ")";
  }
}
