package utils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Orientation {
  NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

  private static final Map<String, Orientation> LOOKUP = Arrays.stream(values()).collect(Collectors.toMap(Orientation::getValue, x -> x));
  private final String value;

  Orientation(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Orientation get(String value) {
    return LOOKUP.get(value);
  }
}
