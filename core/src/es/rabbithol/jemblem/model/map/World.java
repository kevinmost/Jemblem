package es.rabbithol.jemblem.model.map;

import es.rabbithol.jemblem.model.terrain.Terrain;
import es.rabbithol.jemblem.model.terrain.Terrains;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;

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

  @Nullable
  public Tile getTileToRightOf(Tile tile) {
    if (tile.x == tiles[tile.x].length - 1) {
      return null;
    }
    return tiles[tile.x + 1][tile.y];
  }

  @Nullable
  public Tile getTileToLeftOf(Tile tile) {
    if (tile.x == 0) {
      return null;
    }
    return tiles[tile.x - 1][tile.y];
  }

  @Nullable
  public Tile getTileAbove(Tile tile) {
    if (tile.y == 0) {
      return null;
    }
    return tiles[tile.x][tile.y - 1];
  }

  @Nullable
  public Tile getTileBelow(Tile tile) {
    if (tile.y == tiles.length - 1) {
      return null;
    }
    return tiles[tile.x][tile.y + 1];
  }

  public static class Tile {
    //TODO: Don't do this
    public Terrain terrain = Terrains.PLAIN;

    public final int x;
    public final int y;

    public Tile(int x, int y) {
      this.x = x;
      this.y = y;
    }

    // TODO: Sprite
  }
}
