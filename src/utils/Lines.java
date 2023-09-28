package utils;

import adventurer.Adventurer;
import exceptions.NoMapLineException;
import exceptions.WrongLineTypeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lines {

  private static final char MAP = 'C';
  private static final char MOUNTAIN = 'M';
  private static final char TREASURE = 'T';
  private static final char ADVENTURER = 'A';

  private static final String lineSplit = " - ";

  public static String getMapLine(List<String> lines) throws NoMapLineException {
    for (String line : lines) {
      if (isMapType(line)) {
        return line;
      }
    }
    throw new NoMapLineException("There is no map line");
  }

  public static Dimension getMapDimensions(String line) throws WrongLineTypeException {
    String[] splittedLine = line.split(lineSplit);
    if(isMapType(line)) {
      return new Dimension(Integer.parseInt(splittedLine[1]), Integer.parseInt(splittedLine[2]));
    }
    throw new WrongLineTypeException("Wrong line type");
  }

  public static List<Coordinates> getMountainsCoordinates(List<String> lines) {
    List<Coordinates> mountainsCoordinates = new ArrayList<>();
    for (String line : lines) {
      if (isMountainType(line)) {
        String[] splittedLine = line.split(lineSplit);
        mountainsCoordinates.add(new Coordinates(Integer.parseInt(splittedLine[1]), Integer.parseInt(splittedLine[2])));
      }
    }
    return mountainsCoordinates;
  }

  public static List<List<Integer>> getTreasuresParameters(List<String> lines) {
    List<List<Integer>> treasuresParameters = new ArrayList<>();
    for (String line : lines) {
      if (isTreasureType(line)) {
        String[] splittedLine = line.split(lineSplit);
        List<Integer> treasureParameters = new ArrayList<>();
        for (int i = 1; i < splittedLine.length; i++) {
          treasureParameters.add(Integer.parseInt(splittedLine[i]));
        }
        treasuresParameters.add(treasureParameters);
      }
    }
    return treasuresParameters;
  }

  public static List<Adventurer> getAdventurers(List<String> lines) {
    List<Adventurer> adventurers = new ArrayList<>();
    for (String line : lines) {
      if (isAdventurerType(line)) {
        String[] splittedLine = line.split(lineSplit);
        List<String> adventurerParameters = new ArrayList<>(Arrays.asList(splittedLine));
        adventurerParameters.remove(0);

        String name = adventurerParameters.get(0);
        Coordinates coordinates = new Coordinates(Integer.parseInt(adventurerParameters.get(1)), Integer.parseInt(adventurerParameters.get(2)));
        Orientation orientation = Orientation.get(adventurerParameters.get(3));
        List<Character> movements = adventurerParameters.get(4).chars().mapToObj(e -> (char)e).collect(Collectors.toList());
        Adventurer adventurer = new Adventurer(name, coordinates, orientation, (ArrayList<Character>) movements);
        adventurers.add(adventurer);
      }
    }
    return adventurers;
  }
  
  public static boolean isMapType(String line) {
    return line.charAt(0) == MAP;
  }

  public static boolean isMountainType(String line) {
    return line.charAt(0) == MOUNTAIN;
  }

  public static boolean isTreasureType(String line) {
    return line.charAt(0) == TREASURE;
  }

  public static boolean isAdventurerType(String line) {
    return line.charAt(0) == ADVENTURER;
  }
}
