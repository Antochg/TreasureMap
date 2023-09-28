package map;

import adventurer.Adventurer;
import exceptions.NoMapLineException;
import exceptions.WrongLineTypeException;
import utils.Coordinates;
import utils.Dimension;
import utils.Lines;

import java.util.ArrayList;
import java.util.List;

public class Map {

  private Dimension dimension;
  private Case[][] map;

  public Map() {
  }

  public Map(int width, int height) {
    this.dimension = new Dimension(width, height);
    initializeMap(dimension.getWidth(), dimension.getHeight());
  }

  public Map(List<String> lines) throws NoMapLineException, WrongLineTypeException {
    createMap(lines);
  }
  public void createMap(List<String> lines) throws NoMapLineException, WrongLineTypeException {
    String mapLine = Lines.getMapLine(lines);
    this.dimension = Lines.getMapDimensions(mapLine);
    initializeMap(dimension.getWidth(), dimension.getHeight());

    List<Coordinates> mountainsCoordinates = Lines.getMountainsCoordinates(lines);
    initializeMountains(mountainsCoordinates);

    List<List<Integer>> treasuresParameters = Lines.getTreasuresParameters(lines);
    initializeTreasures(treasuresParameters);
  }

  public void initializeMap(int width, int height) {
    this.map = new Case[this.dimension.getWidth()][dimension.getHeight()];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        this.map[i][j] = new Case(new Coordinates(i, j));
      }
    }
  }

  public void initializeMountains(List<Coordinates> mountainsCoordinates) {
    for(Coordinates mountainsCoordinate : mountainsCoordinates) {
      this.map[mountainsCoordinate.getPositionX()][mountainsCoordinate.getPositionY()] = new Mountain(new Coordinates(mountainsCoordinate.getPositionX(), mountainsCoordinate.getPositionY()));
    }
  }

  public void initializeTreasures(List<List<Integer>> treasuresParameters) {
    for(List<Integer> treasureParameters : treasuresParameters) {
      this.map[treasureParameters.get(0)][treasureParameters.get(1)] = new Treasure(new Coordinates(treasureParameters.get(0), treasureParameters.get(1)), treasureParameters.get(2));
    }
  }

  public void placeAdventurers(List<Adventurer> adventurers) {
    for(Adventurer adventurer : adventurers) {
      this.map[adventurer.getCoordinates().getPositionX()][adventurer.getCoordinates().getPositionY()].setAdventurer(adventurer);
    }
  }

  public boolean isAccessibleByAdventurer(Coordinates coordinates, Adventurer adventurer) {
    if(coordinates.getPositionX() >= 0 && coordinates.getPositionX() < this.dimension.getWidth()
            && coordinates.getPositionY() >= 0 && coordinates.getPositionY() < this.dimension.getHeight()
            && this.map[coordinates.getPositionX()][coordinates.getPositionY()].getAdventurer() == null) {
      Case mapCase = this.map[coordinates.getPositionX()][coordinates.getPositionY()];
      switch (mapCase.getClass().getSimpleName()) {
        case "Mountain" -> {
          return adventurer.canClimbMountain();
        }
        default -> {
          return true;
        }
      }
    }
    return false;
  }

  public List<Mountain> getMountainsCases() {
    List<Mountain> mountainCases = new ArrayList<>();
    for(int i = 0; i < this.dimension.getWidth(); i++) {
      for(int j = 0; j < this.dimension.getHeight(); j++) {
        if(this.map[i][j].getClass().getSimpleName().equals("Mountain")) {
          mountainCases.add((Mountain) this.map[i][j]);
        }
      }
    }
    return mountainCases;
  }

  public List<Treasure> getTreasuresCases() {
    List<Treasure> treasureCases = new ArrayList<>();
    for(int i = 0; i < this.dimension.getWidth(); i++) {
      for(int j = 0; j < this.dimension.getHeight(); j++) {
        if(this.map[i][j].getClass().getSimpleName().equals("Treasure")) {
          treasureCases.add((Treasure) this.map[i][j]);
        }
      }
    }
    return treasureCases;
  }

  public boolean validMapDimension() {
    return this.map.length > 0 && this.map[0].length > 0;
  }

  public void displayMap() {
    if(this.validMapDimension()) {
      for(int i = 0; i < this.map[0].length; i++) {
        for (Case[] cases : this.map) {
          System.out.print(cases[i].toString() + " ");
        }
        System.out.println("\n");
      }
    }
  }

  public Dimension getDimension() {
    return dimension;
  }

  public void setDimension(Dimension dimension) {
    this.dimension = dimension;
  }

  public Case[][] getMap() {
    return map;
  }

}
