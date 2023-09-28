package io;

import adventurer.Adventurer;
import exceptions.NoMapLineException;
import exceptions.WrongLineTypeException;
import map.Map;
import org.junit.jupiter.api.Test;
import utils.Coordinates;
import utils.Orientation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IOFileWriterTest {

  @Test
  void writeOutputFile() throws NoMapLineException, WrongLineTypeException, FileNotFoundException {
    List<String> lines = new ArrayList<>();
    lines.add("C - 3 - 4");
    lines.add("M - 1 - 0");
    lines.add("T - 0 - 3 - 2");

    Map map = new Map();
    map.createMap(lines);

    List<Adventurer> adventurers = new ArrayList<>();
    adventurers.add(new Adventurer("A1", new Coordinates(1, 1), Orientation.NORTH, new ArrayList<>()));

    String filePath = "resources/outputTest.txt";
    IOFileWriter.writeOutputFile(filePath, map, adventurers);

    File file = new File("resources/outputTest.txt");
    assertTrue(file.exists());
  }
}