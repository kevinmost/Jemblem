package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
  public static final PositionComponent NULL_POSITION = new PositionComponent(-1, -1);

  public int x;
  public int y;

  public PositionComponent() {
  }

  public PositionComponent(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
