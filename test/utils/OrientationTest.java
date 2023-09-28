package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

  @Test
  void get() {
    assertSame(Orientation.get("N"), Orientation.NORTH);
    assertSame(Orientation.get("S"), Orientation.SOUTH);
    assertSame(Orientation.get("E"), Orientation.EAST);
    assertSame(Orientation.get("W"), Orientation.WEST);
  }
}