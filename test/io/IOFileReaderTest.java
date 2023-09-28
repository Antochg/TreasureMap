package io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOFileReaderTest {

  @Test
  void readFile() throws FileNotFoundException {
    Exception exception = assertThrows(FileNotFoundException.class, () -> {
      String filePath = "throwException.txt";
      List<String> lines = IOFileReader.readFile(filePath);
    });
    String expectedMessage = "File not found";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));

    String filePath = "resources/entryTest.txt";
    List<String> lines = IOFileReader.readFile(filePath);
    assertEquals(lines.size(), 1);
    assertEquals(lines.get(0), "Test");
  }
}