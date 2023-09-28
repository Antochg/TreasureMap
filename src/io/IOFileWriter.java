package io;

import adventurer.Adventurer;
import map.Map;
import map.Mountain;
import map.Treasure;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IOFileWriter {

  public static void writeOutputFile(String filePath, Map map, List<Adventurer> adventurers) throws FileNotFoundException {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
      writeMapLine(writer, map);
      writeMountainsLines(writer, map);
      writeTreasuresLines(writer, map);
      writeAdventurersLines(writer, adventurers);

      writer.close();
    } catch (IOException e) {
      throw new FileNotFoundException("File not found");
    }
  }

  private static void writeMapLine(BufferedWriter writer, Map map) throws IOException {
    writer.write("C - " + map.getDimension().getWidth() + " - " + map.getDimension().getHeight() + "\n");
  }

  private static void writeMountainsLines(BufferedWriter writer, Map map) throws IOException {
    List<Mountain> mountainsCases = map.getMountainsCases();
    for(Mountain mountain : mountainsCases) {
      writer.write("M - " + mountain.getCoordinates().getPositionX() + " - " + mountain.getCoordinates().getPositionY() + "\n");
    }
  }

  private static void writeTreasuresLines(BufferedWriter writer, Map map) throws IOException {
    List<Treasure> treasuresCases = map.getTreasuresCases();
    for(Treasure treasure : treasuresCases) {
      if(treasure.getNumberOfTreasures() > 0 ) {
        writer.write("T - " + treasure.getCoordinates().getPositionX() + " - " + treasure.getCoordinates().getPositionY() + " - " + treasure.getNumberOfTreasures() + "\n");
      }
    }
  }

  private static void writeAdventurersLines(BufferedWriter writer, List<Adventurer> adventurers) throws IOException {
    for(Adventurer adventurer : adventurers) {
      writer.write("A - " + adventurer.getName() + " - " + adventurer.getCoordinates().getPositionX() + " - " + adventurer.getCoordinates().getPositionY() +
              " - " + adventurer.getOrientation().getValue() + " - " + adventurer.getFoundTreasures() + "\n");
    }
  }
}
