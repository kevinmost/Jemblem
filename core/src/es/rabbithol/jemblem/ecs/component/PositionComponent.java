package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
  public int x;
  public int y;

  public PositionComponent() {
  }

  public PositionComponent(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
