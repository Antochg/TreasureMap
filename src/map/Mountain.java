package map;

import utils.Coordinates;

public class Mountain extends Case {

  public Mountain(Coordinates coordinates) {
    super(coordinates);
  }

  @Override
  public String toString() {
    return this.getAdventurer() == null ? "M" : "A(" + this.getAdventurer().getName() + ")";
  }
}
