package map;

import adventurer.Adventurer;
import exceptions.NoMapLineException;
import exceptions.WrongLineTypeException;
import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Dimension;
import utils.Orientation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

  @Test
  void createMap() throws NoMapLineException, WrongLineTypeException {
    List<String> lines = new ArrayList<>();
    lines.add("C - 3 - 4");
    lines.add("M - 1 - 0");
    lines.add("T - 0 - 3 - 2");

    Map map = new Map();
    map.createMap(lines);

    assertEquals(map.getMap().length, 3);
    assertEquals(map.getMap()[0].length, 4);

    assertTrue(map.getMap()[1][0] instanceof Mountain);
    assertEquals(map.getMap()[1][0].getCoordinates(), new Coordinates(1, 0));

    assertTrue(map.getMap()[0][3] instanceof Treasure);
    assertEquals(map.getMap()[0][3].getCoordinates(), new Coordinates(0, 3));
    assertEquals(((Treasure) map.getMap()[0][3]).getNumberOfTreasures(), 2);
  }

  @Test
  void initializeMap() {
    int width = 3, height = 4;
    Map map = new Map();
    map.setDimension(new Dimension(width, height));
    map.initializeMap(width, height);
    assertEquals(map.getMap().length, width);
    assertEquals(map.getMap()[0].length, height);
  }

  @Test
  void initializeMountains() {
    Map map1 = new Map(3, 3);
    List<Coordinates> mountainsCoordinates = new ArrayList<>();
    mountainsCoordinates.add(new Coordinates(0, 0));
    mountainsCoordinates.add(new Coordinates(1, 1));

    map1.initializeMountains(mountainsCoordinates);
    assertTrue(map1.getMap()[0][0] instanceof Mountain);
    assertEquals(map1.getMap()[0][0].getCoordinates(), new Coordinates(0, 0));

    assertTrue(map1.getMap()[1][1] instanceof Mountain);
    assertEquals(map1.getMap()[1][1].getCoordinates(), new Coordinates(1, 1));
  }

  @Test
  void initializeTreasures() {
    Map map1 = new Map(3, 3);
    List<List<Integer>> treasuresParameters = new ArrayList<>();
    treasuresParameters.add(new ArrayList<>(Arrays.asList(0, 0, 1)));
    treasuresParameters.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
    map1.initializeTreasures(treasuresParameters);
    assertTrue(map1.getMap()[0][0] instanceof Treasure);
    assertEquals(map1.getMap()[0][0].getCoordinates(), new Coordinates(0, 0));
    assertEquals(((Treasure) map1.getMap()[0][0]).getNumberOfTreasures(), 1);

    assertTrue(map1.getMap()[1][1] instanceof Treasure);
    assertEquals(map1.getMap()[1][1].getCoordinates(), new Coordinates(1, 1));
    assertEquals(((Treasure) map1.getMap()[1][1]).getNumberOfTreasures(), 1);
  }

  @Test
  void placeAdventurers() {
    Map map1 = new Map(3, 3);
    List<Adventurer> adventurers = new ArrayList<>();
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(0, 0), Orientation.NORTH, new ArrayList<>());
    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>());
    adventurers.add(adventurer1);
    adventurers.add(adventurer2);

    map1.placeAdventurers(adventurers);
    assertNull(map1.getMap()[1][0].getAdventurer());
    assertEquals(map1.getMap()[0][0].getAdventurer(), adventurer1);
    assertEquals(map1.getMap()[1][1].getAdventurer(), adventurer2);
  }

  @Test
  void isAccessibleByAdventurer() {
    Map map1 = new Map(3, 3);
    Adventurer adventurer1 = new Adventurer("A1", new Coordinates(0, 1), Orientation.NORTH, new ArrayList<>());
    assertTrue(map1.isAccessibleByAdventurer(new Coordinates(0, 0), adventurer1));

    Map map2 = new Map(3, 3);
    Adventurer adventurer2OnCase = new Adventurer("A2C", new Coordinates(0, 0), Orientation.NORTH, new ArrayList<>());
    map2.getMap()[0][0].setAdventurer(adventurer2OnCase);
    Adventurer adventurer2 = new Adventurer("A2", new Coordinates(0, 1), Orientation.NORTH, new ArrayList<>());
    assertFalse(map2.isAccessibleByAdventurer(new Coordinates(0, 0), adventurer2));

    Map map3 = new Map(3, 3);
    map3.getMap()[0][0] = new Mountain(new Coordinates(0, 0));
    Adventurer adventurer3 = new Adventurer("A3", new Coordinates(0, 1), Orientation.NORTH, new ArrayList<>());
    assertFalse(map3.isAccessibleByAdventurer(new Coordinates(0, 0), adventurer3));

    Map map4 = new Map(3, 3);
    map4.getMap()[0][0] = new Treasure(new Coordinates(0, 0), 3);
    Adventurer adventurer4 = new Adventurer("A4", new Coordinates(0, 1), Orientation.NORTH, new ArrayList<>());
    assertTrue(map4.isAccessibleByAdventurer(new Coordinates(0, 0), adventurer4));

    Map map5 = new Map(3, 3);
    map5.getMap()[0][0] = new Treasure(new Coordinates(0, 0), 2);
    Adventurer adventurer5OnTreasure = new Adventurer("A4T", new Coordinates(0, 0), Orientation.NORTH, new ArrayList<>());
    map5.getMap()[0][0].setAdventurer(adventurer5OnTreasure);
    Adventurer adventurer5 = new Adventurer("A5", new Coordinates(0, 1), Orientation.NORTH, new ArrayList<>());
    assertFalse(map5.isAccessibleByAdventurer(new Coordinates(0, 0), adventurer5));

    Map map6 = new Map(3, 3);
    Adventurer adventurer6OrientedNorth = new Adventurer("A6", new Coordinates(0, 0), Orientation.NORTH, new ArrayList<>());
    assertFalse(map6.isAccessibleByAdventurer(new Coordinates(0, -1), adventurer6OrientedNorth));
    Adventurer adventurer6OrientedWest = new Adventurer("A6", new Coordinates(0, 0), Orientation.WEST, new ArrayList<>());
    assertFalse(map6.isAccessibleByAdventurer(new Coordinates(-1, 0), adventurer6OrientedWest));
  }

  @Test
  void getMountainsCases() {
    Map map1 = new Map(2, 2);
    map1.getMap()[0][0] = new Mountain(new Coordinates(0, 0));
    List<Mountain> mountainsCases = map1.getMountainsCases();
    assertEquals(mountainsCases.size(), 1);
    assertEquals(mountainsCases.get(0).getCoordinates().getPositionX(), 0);
    assertEquals(mountainsCases.get(0).getCoordinates().getPositionY(), 0);
  }

  @Test
  void getTreasuresCases() {
    Map map1 = new Map(2, 2);
    map1.getMap()[0][0] = new Treasure(new Coordinates(0, 0), 3);
    List<Treasure> treasuresCases = map1.getTreasuresCases();
    assertEquals(treasuresCases.size(), 1);
    assertEquals(treasuresCases.get(0).getCoordinates().getPositionX(), 0);
    assertEquals(treasuresCases.get(0).getCoordinates().getPositionY(), 0);
    assertEquals(treasuresCases.get(0).getNumberOfTreasures(), 3);
  }

  @Test
  void validMapDimension() {
    Map map1 = new Map(0, 0);
    assertFalse(map1.validMapDimension());

    Map map2 = new Map(0, 1);
    assertFalse(map2.validMapDimension());

    Map map3 = new Map(1, 0);
    assertFalse(map3.validMapDimension());

    Map map4 = new Map(1, 1);
    assertTrue(map4.validMapDimension());
  }
}