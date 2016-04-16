package es.rabbithol.jemblem.model.map;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

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


  @NotNull
  public Optional<Tile> getTileTo(@NotNull Direction direction, @NotNull Tile tile) {
    switch (direction) {
      case NORTH:
        return getTileAbove(tile);
      case SOUTH:
        return getTileBelow(tile);
      case WEST:
        return getTileToLeftOf(tile);
      case EAST:
        return getTileToRightOf(tile);
    }
    throw new IllegalArgumentException("Direction can't be null!");
  }

  public Optional<Tile> getTileToRightOf(@NotNull Tile tile) {
    return maybeGetTile(tile.x + 1, tile.y);
  }

  public Optional<Tile> getTileToLeftOf(@NotNull Tile tile) {
    return maybeGetTile(tile.x - 1, tile.y);
  }

  public Optional<Tile> getTileAbove(@NotNull Tile tile) {
    return maybeGetTile(tile.x, tile.y - 1);
  }

  public Optional<Tile> getTileBelow(@NotNull Tile tile) {
    return maybeGetTile(tile.x, tile.y + 1);
  }

  private Optional<Tile> maybeGetTile(int x, int y) {
    if (willTileBeInValidRange(x, y)) {
      return Optional.empty();
    }
    return Optional.of(tiles[x][y]);
  }

  private boolean willTileBeInValidRange(int x, int y) {
    return 0 <= x && x <= tiles.length - 1 &&
        0 <= y && y <= tiles[x].length - 1;
  }

}
