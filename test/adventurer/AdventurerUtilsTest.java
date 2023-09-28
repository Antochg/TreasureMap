package adventurer;

import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Orientation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerUtilsTest {

  @Test
  void checkAdventurersHaveNextMovement() {
    List<Adventurer> adventurers = new ArrayList<>();
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurers.add(adventurer1);
    assertFalse(AdventurerUtils.checkAdventurersHaveNextMovement(adventurers));

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>(
            Arrays.asList('A')));
    adventurers.add(adventurer2);
    assertTrue(AdventurerUtils.checkAdventurersHaveNextMovement(adventurers));
  }
}