package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOFileReader {

  public static List<String> readFile(String filePath) throws FileNotFoundException {
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if(line.charAt(0) != '#') {
          lines.add(line);
        }
      }
    } catch (IOException e) {
      throw new FileNotFoundException("File not found");
    }

    return lines;
  }
}
