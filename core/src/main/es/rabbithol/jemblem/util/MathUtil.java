package es.rabbithol.jemblem.util;

import es.rabbithol.jemblem.ecs.component.PositionComponent;
import es.rabbithol.jemblem.model.map.Tile;

public class MathUtil {
  public static int distanceBetween(Tile t1, Tile t2) {
    return Math.abs(t1.x - t2.x) + Math.abs(t1.y - t2.y);
  }

  public static int distanceBetween(Tile t1, PositionComponent t2) {
    return Math.abs(t1.x - t2.x) + Math.abs(t1.y - t2.y);
  }

  public static int distanceBetween(PositionComponent t1, Tile t2) {
    return Math.abs(t1.x - t2.x) + Math.abs(t1.y - t2.y);
  }

  public static int distanceBetween(PositionComponent t1, PositionComponent t2) {
    return Math.abs(t1.x - t2.x) + Math.abs(t1.y - t2.y);
  }
}
