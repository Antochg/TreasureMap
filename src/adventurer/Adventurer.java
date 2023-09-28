package adventurer;

import map.Case;
import map.Map;
import map.Treasure;
import utils.Coordinates;
import utils.Orientation;

import java.util.ArrayList;
import java.util.Objects;

public class Adventurer {

  private String name;
  private Coordinates coordinates;
  private Orientation orientation;
  private int foundTreasures = 0;
  private final ArrayList<Character> movements;

  public Adventurer(String name, Coordinates coordinates, Orientation orientation, ArrayList<Character> movements) {
    this.name = name;
    this.coordinates = coordinates;
    this.orientation = orientation;
    this.movements = movements;
  }

  public void playNextMovement(Map map) {
    if(hasNextMovement()) {
      switch (this.movements.get(0)) {
        case 'A' -> {
          if(canMoveForward(map)) moveForward(map);
          if(foundTreasure(map)) takeTreasure(map);
        }
        case 'G' -> turnOrientationLeft();
        case 'D' -> turnOrientationRight();
      }
      this.movements.remove(0);
    }
  }

  public boolean hasNextMovement() {
    return this.movements.size() > 0;
  }

  public void moveForward(Map map) {
    switch (this.orientation) {
      case NORTH -> {
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(null);
        this.coordinates.setPositionY(this.coordinates.getPositionY() - 1);
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(this);
      }
      case SOUTH -> {
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(null);
        this.coordinates.setPositionY(this.coordinates.getPositionY() + 1);
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(this);
      }
      case WEST -> {
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(null);
        this.coordinates.setPositionX(this.coordinates.getPositionX() - 1);
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(this);
      }
      case EAST -> {
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(null);
        this.coordinates.setPositionX(this.coordinates.getPositionX() + 1);
        map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()].setAdventurer(this);
      }
    }
  }

  public void turnOrientationLeft() {
    switch (this.orientation) {
      case NORTH -> setOrientation(Orientation.WEST);
      case SOUTH -> setOrientation(Orientation.EAST);
      case WEST -> setOrientation(Orientation.SOUTH);
      case EAST -> setOrientation(Orientation.NORTH);
    }
  }

  public void turnOrientationRight() {
    switch (this.orientation) {
      case NORTH -> setOrientation(Orientation.EAST);
      case SOUTH -> setOrientation(Orientation.WEST);
      case WEST -> setOrientation(Orientation.NORTH);
      case EAST -> setOrientation(Orientation.SOUTH);
    }
  }

  public boolean canMoveForward(Map map) {
    switch (this.orientation) {
      case NORTH -> {
        return map.isAccessibleByAdventurer(new Coordinates(this.coordinates.getPositionX() ,this.coordinates.getPositionY() - 1), this);
      }
      case SOUTH -> {
        return map.isAccessibleByAdventurer(new Coordinates(this.coordinates.getPositionX() ,this.coordinates.getPositionY() + 1), this);
      }
      case WEST -> {
        return map.isAccessibleByAdventurer(new Coordinates(this.coordinates.getPositionX() - 1 ,this.coordinates.getPositionY()), this);
      }
      case EAST -> {
        return map.isAccessibleByAdventurer(new Coordinates(this.coordinates.getPositionX() + 1 ,this.coordinates.getPositionY()), this);
      }
      default -> {
        return false;
      }
    }
  }

  public boolean foundTreasure(Map map) {
    Case mapCase = map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()];
    switch (mapCase.getClass().getSimpleName()) {
      case "Treasure" -> {
        return true;
      }
    }
    return false;
  }

  public void takeTreasure(Map map) {
    Treasure mapCase = (Treasure) map.getMap()[this.coordinates.getPositionX()][this.coordinates.getPositionY()];
    switch (mapCase.getClass().getSimpleName()) {
      case "Treasure" -> {
        if(mapCase.getNumberOfTreasures() > 0) {
          this.foundTreasures++;
          mapCase.decreaseNumberOfTreasures();
        }
      }
    }
  }

  public boolean canClimbMountain() {
    return false;
  }

  public String getName() {
    return name;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  public int getFoundTreasures() {
    return foundTreasures;
  }

  @Override
  public String toString() {
    return "Adventurer{" +
            "name='" + name + '\'' +
            ", coordinates=" + coordinates +
            ", orientation=" + orientation +
            ", foundTreasures=" + foundTreasures +
            ", movements=" + movements +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Adventurer that)) return false;
    return getFoundTreasures() == that.getFoundTreasures() && getName().equals(that.getName()) && getCoordinates().equals(that.getCoordinates()) && getOrientation() == that.getOrientation() && movements.equals(that.movements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getCoordinates(), getOrientation(), getFoundTreasures(), movements);
  }
}
