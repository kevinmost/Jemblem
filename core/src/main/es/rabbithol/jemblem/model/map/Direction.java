package es.rabbithol.jemblem.model.map;

import es.rabbithol.jemblem.util.ArrayUtil;

public enum Direction {
  NORTH,
  EAST,
  SOUTH,
  WEST;

  public Direction clockwise() {
    return ArrayUtil.getItemAtIndexWrapped(values(), ordinal() + 1);
  }

  public Direction counterclockwise() {
    return ArrayUtil.getItemAtIndexWrapped(values(), ordinal() - 1);
  }
}
