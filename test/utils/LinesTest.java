package utils;

import adventurer.Adventurer;
import exceptions.NoMapLineException;
import exceptions.WrongLineTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinesTest {

  private final List<String> lines = new ArrayList<>();

  @BeforeEach
  public void init() {
    this.lines.add("C - 3 - 4");
    this.lines.add("M - 1 - 0");
    this.lines.add("M - 2 - 1");
    this.lines.add("T - 0 - 3 - 2");
    this.lines.add("T - 1 - 3 - 3");
    this.lines.add("A - Lara - 1 - 1 - S - AADADAGGA");
  }

  @Test
  void getMapLine() throws NoMapLineException {
    Exception exception = assertThrows(NoMapLineException.class, () -> {
      List<String> emptyLines = new ArrayList<>();
      String mapLine = Lines.getMapLine(emptyLines);
    });
    String expectedMessage = "There is no map line";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));

    String mapLine = Lines.getMapLine(this.lines);
    assertEquals(mapLine,"C - 3 - 4");
  }

  @Test
  void getMapDimensions() throws WrongLineTypeException {
    Exception exception = assertThrows(WrongLineTypeException.class, () -> {
      Dimension dimension = Lines.getMapDimensions("M - 1 - 0");
    });
    String expectedMessage = "Wrong line type";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));

    Dimension dimension = Lines.getMapDimensions("C - 3 - 4");
    assertEquals(dimension.getWidth(), 3);
    assertEquals(dimension.getHeight(), 4);
  }

  @Test
  void getMountainsCoordinates() {
    List<Coordinates> mountainsCoordinates = Lines.getMountainsCoordinates(this.lines);
    assertEquals(mountainsCoordinates.size(), 2);
    assertTrue(mountainsCoordinates.get(0).getPositionX() == 1 && mountainsCoordinates.get(0).getPositionY() == 0);
    assertTrue(mountainsCoordinates.get(1).getPositionX() == 2 && mountainsCoordinates.get(1).getPositionY() == 1);
  }

  @Test
  void getTreasuresParameters() {
    List<List<Integer>> treasuresParameters = Lines.getTreasuresParameters(this.lines);
    assertEquals(treasuresParameters.get(0).get(0), 0);
    assertEquals(treasuresParameters.get(0).get(1), 3);
    assertEquals(treasuresParameters.get(0).get(2), 2);

    assertEquals(treasuresParameters.get(1).get(0), 1);
    assertEquals(treasuresParameters.get(1).get(1), 3);
    assertEquals(treasuresParameters.get(1).get(2), 3);
  }

  @Test
  void getAdventurers() {
    List<Adventurer> adventurers1 = Lines.getAdventurers(this.lines);
    assertEquals(adventurers1.size(), 1);
    assertEquals(adventurers1.get(0).getName(), "Lara");
  }

  @Test
  void isMapType() {
    String mapLine = "C - 3 - 4";
    String mountainLine = "M - 1 - 0";
    String treasureLine = "T - 0 - 3 - 2";
    String adventurerLine = "A - Lara - 1 - 1 - S - AADADAGGA";

    assertTrue(Lines.isMapType(mapLine));
    assertFalse(Lines.isMapType(mountainLine));
    assertFalse(Lines.isMapType(treasureLine));
    assertFalse(Lines.isMapType(adventurerLine));
  }

  @Test
  void isMountainType() {
    String mapLine = "C - 3 - 4";
    String mountainLine = "M - 1 - 0";
    String treasureLine = "T - 0 - 3 - 2";
    String adventurerLine = "A - Lara - 1 - 1 - S - AADADAGGA";

    assertFalse(Lines.isMountainType(mapLine));
    assertTrue(Lines.isMountainType(mountainLine));
    assertFalse(Lines.isMountainType(treasureLine));
    assertFalse(Lines.isMountainType(adventurerLine));
  }

  @Test
  void isTreasureType() {
    String mapLine = "C - 3 - 4";
    String mountainLine = "M - 1 - 0";
    String treasureLine = "T - 0 - 3 - 2";
    String adventurerLine = "A - Lara - 1 - 1 - S - AADADAGGA";

    assertFalse(Lines.isTreasureType(mapLine));
    assertFalse(Lines.isTreasureType(mountainLine));
    assertTrue(Lines.isTreasureType(treasureLine));
    assertFalse(Lines.isTreasureType(adventurerLine));
  }

  @Test
  void isAdventurerType() {
    String mapLine = "C - 3 - 4";
    String mountainLine = "M - 1 - 0";
    String treasureLine = "T - 0 - 3 - 2";
    String adventurerLine = "A - Lara - 1 - 1 - S - AADADAGGA";

    assertFalse(Lines.isAdventurerType(mapLine));
    assertFalse(Lines.isAdventurerType(mountainLine));
    assertFalse(Lines.isAdventurerType(treasureLine));
    assertTrue(Lines.isAdventurerType(adventurerLine));
  }
}