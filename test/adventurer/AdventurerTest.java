package adventurer;

import map.Map;
import map.Mountain;
import map.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Orientation;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

  private Map map;

  @BeforeEach
  public void init() {
    this.map = new Map(3, 3);
  }

  @Test
  void playNextMovement() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>(
            Arrays.asList('A')));
    adventurer1.playNextMovement(map);
    assertEquals(adventurer1.getCoordinates().getPositionX(), 1);
    assertEquals(adventurer1.getCoordinates().getPositionY(), 0);

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>(
            Arrays.asList('G')));
    adventurer2.playNextMovement(map);
    assertEquals(adventurer2.getOrientation(), Orientation.WEST);

    Adventurer adventurer3 = new Adventurer("A3", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>(
            Arrays.asList('D')));
    adventurer3.playNextMovement(map);
    assertEquals(adventurer3.getOrientation(), Orientation.EAST);

    Adventurer adventurer4BeforeMovement = new Adventurer("A4", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    Adventurer adventurer4AfterMovement = new Adventurer("A4", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurer4AfterMovement.playNextMovement(map);
    assertEquals(adventurer4AfterMovement.getCoordinates(), adventurer4BeforeMovement.getCoordinates());
    assertEquals(adventurer4AfterMovement.getOrientation(), adventurer4BeforeMovement.getOrientation());
  }

  @Test
  void hasNextMovement() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>(
      Arrays.asList('A')));
    assertTrue(adventurer1.hasNextMovement());
    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(2, 2), Orientation.NORTH, new ArrayList<>());
    assertFalse(adventurer2.hasNextMovement());
  }

  @Test
  void moveForward() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurer1.moveForward(map);
    assertEquals(adventurer1.getCoordinates().getPositionX(), 1);
    assertEquals(adventurer1.getCoordinates().getPositionY(), 0);

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.SOUTH, new ArrayList<>());
    adventurer2.moveForward(map);
    assertEquals(adventurer2.getCoordinates().getPositionX(), 1);
    assertEquals(adventurer2.getCoordinates().getPositionY(), 2);

    Adventurer adventurer3 = new Adventurer("A3", new Coordinates(1, 1), Orientation.WEST, new ArrayList<>());
    adventurer3.moveForward(map);
    assertEquals(adventurer3.getCoordinates().getPositionX(), 0);
    assertEquals(adventurer3.getCoordinates().getPositionY(), 1);

    Adventurer adventurer4 = new Adventurer("A4", new Coordinates(1, 1), Orientation.EAST, new ArrayList<>());
    adventurer4.moveForward(map);
    assertEquals(adventurer4.getCoordinates().getPositionX(), 2);
    assertEquals(adventurer4.getCoordinates().getPositionY(), 1);
  }

  @Test
  void turnOrientationLeft() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurer1.turnOrientationLeft();
    assertEquals(adventurer1.getOrientation(), Orientation.WEST);

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.SOUTH, new ArrayList<>());
    adventurer2.turnOrientationLeft();
    assertEquals(adventurer2.getOrientation(), Orientation.EAST);

    Adventurer adventurer3 = new Adventurer("A3", new Coordinates(1, 1), Orientation.WEST, new ArrayList<>());
    adventurer3.turnOrientationLeft();
    assertEquals(adventurer3.getOrientation(), Orientation.SOUTH);

    Adventurer adventurer4 = new Adventurer("A4", new Coordinates(1, 1), Orientation.EAST, new ArrayList<>());
    adventurer4.turnOrientationLeft();
    assertEquals(adventurer4.getOrientation(), Orientation.NORTH);
  }

  @Test
  void turnOrientationRight() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurer1.turnOrientationRight();
    assertEquals(adventurer1.getOrientation(), Orientation.EAST);

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.SOUTH, new ArrayList<>());
    adventurer2.turnOrientationRight();
    assertEquals(adventurer2.getOrientation(), Orientation.WEST);

    Adventurer adventurer3 = new Adventurer("A3", new Coordinates(1, 1), Orientation.WEST, new ArrayList<>());
    adventurer3.turnOrientationRight();
    assertEquals(adventurer3.getOrientation(), Orientation.NORTH);

    Adventurer adventurer4 = new Adventurer("A4", new Coordinates(1, 1), Orientation.EAST, new ArrayList<>());
    adventurer4.turnOrientationRight();
    assertEquals(adventurer4.getOrientation(), Orientation.SOUTH);
  }

  @Test
  void canMoveForward() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    assertTrue(adventurer1.canMoveForward(this.map));

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    this.map.getMap()[1][0] = new Mountain(new Coordinates(1, 0));
    assertFalse(adventurer2.canMoveForward(this.map));

    Adventurer adventurer3 = new Adventurer("A3", new Coordinates(1, 2), Orientation.NORTH, new ArrayList<>());
    this.map.getMap()[1][1].setAdventurer(adventurer2);
    assertFalse(adventurer3.canMoveForward(this.map));

    Adventurer adventurer4 = new Adventurer("A4", new Coordinates(2, 2), Orientation.NORTH, new ArrayList<>());
    this.map.getMap()[2][1] = new Treasure(new Coordinates(2, 1), 2);
    assertTrue(adventurer4.canMoveForward(this.map));
  }

  @Test
  void foundTreasure() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurer1.moveForward(map);
    assertFalse(adventurer1.foundTreasure(this.map));

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    this.map.getMap()[1][0] = new Treasure(new Coordinates(1, 0), 0);
    adventurer2.moveForward(map);
    assertTrue(adventurer2.foundTreasure(this.map));
  }

  @Test
  void takeTreasure() {
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    this.map.getMap()[1][0] = new Treasure(new Coordinates(1, 0), 0);
    adventurer1.moveForward(this.map);
    adventurer1.takeTreasure(this.map);
    assertEquals(adventurer1.getFoundTreasures(), 0);

    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    this.map.getMap()[1][0] = new Treasure(new Coordinates(1, 0), 2);
    adventurer2.moveForward(this.map);
    adventurer2.takeTreasure(this.map);
    assertEquals(adventurer2.getFoundTreasures(), 1);
    Treasure treasure = (Treasure) this.map.getMap()[1][0];
    assertEquals(treasure.getNumberOfTreasures(), 1);

  }
}