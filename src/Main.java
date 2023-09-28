import adventurer.Adventurer;
import adventurer.AdventurerUtils;
import io.IOFileReader;
import io.IOFileWriter;
import map.Map;
import utils.Lines;

import java.util.List;

public class Main {
  public static void main(String[] args) throws Exception {
    String filePath = "resources/entry.txt";
    List<String> lines = IOFileReader.readFile(filePath);

    Map map = new Map(lines);

    List<Adventurer> adventurers = Lines.getAdventurers(lines);
    map.placeAdventurers(adventurers);

    System.out.println("La carte initiale : ");
    map.displayMap();

    while(AdventurerUtils.checkAdventurersHaveNextMovement(adventurers)) {
      for(Adventurer adventurer : adventurers) {
        adventurer.playNextMovement(map);
      }
    }

    System.out.println("La carte finale : ");
    map.displayMap();

    String outputFilePath = "resources/output.txt";
    IOFileWriter.writeOutputFile(outputFilePath, map, adventurers);
  }
}