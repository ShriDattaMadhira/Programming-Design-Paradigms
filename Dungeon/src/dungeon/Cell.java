package dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the Cells interface.
 * A cell is a node in our dungeon. It has entrances and in some cases treasure.
 * A player can go through it to go to neighboring cells in his search of the end location.
 * Depending on the number of entrances, the cell can be either a Cave or a Tunnel.
 */
class Cell implements Cells {
  private final int[] coordinates;
  private List<Treasure> treasureList;
  private final Map<Direction, Cell> neighborList;
  private int entrances;
  private boolean isCave;
  private boolean isTunnel;

  Cell(int[] coordinates) {
    if (coordinates.length < 2) {
      throw new IllegalArgumentException("Invalid coordinates");
    }
    this.coordinates = coordinates;
    this.entrances = 0;
    this.neighborList = new LinkedHashMap<>();
    this.treasureList = new ArrayList<>();
    this.isCave = false;
    this.isTunnel = false;
  }

  @Override
  public int[] getCoordinates() {
    return coordinates;
  }

  @Override
  public List<Treasure> getTreasureList() {
    return treasureList;
  }

  void setTreasureList(Treasure t, int quantity) {
    for (int i = 0; i < quantity; i++) {
      this.treasureList.add(t);
    }
  }

  void emptyTreasure() {
    this.treasureList = new ArrayList<>();
  }

  @Override
  public Map<Direction, Cell> getNeighborList() {
    return neighborList;
  }

  void setNeighborList(Direction d, Cell c) {
    neighborList.put(d, c);
  }

  @Override
  public int getEntrances() {
    return entrances;
  }

  void setEntrances() {
    this.entrances = this.neighborList.size();
  }

  @Override
  public boolean getIsCave() {
    return this.isCave;
  }

  void setIsCave(boolean bool) {
    this.isCave = bool;
  }

  @Override
  public boolean getIsTunnel() {
    return this.isTunnel;
  }

  void setIsTunnel(boolean bool) {
    this.isTunnel = bool;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cell cell = (Cell) o;
    return Arrays.equals(coordinates, cell.coordinates);
  }

  @Override
  public int hashCode() {
    return 31 * Arrays.hashCode(coordinates);
  }
}
