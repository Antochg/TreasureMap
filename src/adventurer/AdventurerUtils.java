package adventurer;

import java.util.List;

public class AdventurerUtils {

  public static boolean checkAdventurersHaveNextMovement(List<Adventurer> adventurers) {
    return adventurers.stream()
            .anyMatch(Adventurer::hasNextMovement);
  }
}
