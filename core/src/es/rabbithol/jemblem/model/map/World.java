package es.rabbithol.jemblem.model.map;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import es.rabbithol.jemblem.model.terrain.Terrain;

@Singleton
public class World {

  public final Tile[][] tiles = new Tile[10][10];

  @Inject
  World() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        tiles[i][j] = new Tile(i, j);
      }
    }
  }

  public enum Direction {
    ABOVE,
    BELOW,
    RIGHT,
    LEFT
  }

  @NotNull
  public Tile getTileTo(@NotNull Direction direction, @NotNull Tile tile) {
    switch (direction) {
      case ABOVE:
        return getTileAbove(tile);
      case BELOW:
        return getTileBelow(tile);
      case LEFT:
        return getTileToLeftOf(tile);
      case RIGHT:
        return getTileToRightOf(tile);
    }
    throw new IllegalArgumentException("Direction can't be null!");
  }

  @NotNull
  public Tile getTileToRightOf(@NotNull Tile tile) {
    if (!isTileValidRange(tile)) {
      return INVALID_TILE;
    }
    return tiles[tile.x + 1][tile.y];
  }

  @NotNull
  public Tile getTileToLeftOf(@NotNull Tile tile) {
    if (!isTileValidRange(tile)) {
      return INVALID_TILE;
    }
    return tiles[tile.x - 1][tile.y];
  }

  @NotNull
  public Tile getTileAbove(@NotNull Tile tile) {
    if (!isTileValidRange(tile)) {
      return INVALID_TILE;
    }
    return tiles[tile.x][tile.y - 1];
  }

  @NotNull
  public Tile getTileBelow(@NotNull Tile tile) {
    if (!isTileValidRange(tile)) {
      return INVALID_TILE;
    }
    return tiles[tile.x][tile.y + 1];
  }

  private boolean isTileValidRange(@NotNull Tile tile) {
    return 0 <= tile.x && tile.x <= tiles[tile.x].length - 1 &&
        0 <= tile.y && tile.y <= tiles.length - 1;
  }

  private static Tile INVALID_TILE = new Tile(-1, -1);

  public static class Tile {
    public Terrain terrain;

    public final int x;
    public final int y;

    public Tile(int x, int y) {
      this.x = x;
      this.y = y;
    }

    // TODO: Sprite
  }
}
