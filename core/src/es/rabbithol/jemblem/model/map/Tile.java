package es.rabbithol.jemblem.model.map;

import es.rabbithol.jemblem.model.terrain.Terrain;

public class Tile {
  public Terrain terrain;

  public final int x;
  public final int y;

  public Tile(int x, int y) {
    this.x = x;
    this.y = y;
  }

  // TODO: Sprite
}
