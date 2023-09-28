package utils;

import java.util.Objects;

public class Coordinates {

  private int positionX;
  private int positionY;

  public Coordinates(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public int getPositionX() {
    return positionX;
  }

  public void setPositionX(int positionX) {
    this.positionX = positionX;
  }

  public int getPositionY() {
    return positionY;
  }

  public void setPositionY(int positionY) {
    this.positionY = positionY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Coordinates that)) return false;
    return getPositionX() == that.getPositionX() && getPositionY() == that.getPositionY();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPositionX(), getPositionY());
  }
}
