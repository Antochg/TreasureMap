package map;

import org.junit.jupiter.api.Test;
import utils.Coordinates;

import static org.junit.jupiter.api.Assertions.*;

class TreasureTest {

  @Test
  void decreaseNumberOfTreasures() {
    Treasure treasure = new Treasure(new Coordinates(0, 0), 4);
    treasure.decreaseNumberOfTreasures();
    assertEquals(treasure.getNumberOfTreasures(), 3);
  }
}