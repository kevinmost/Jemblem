package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
  public int x;
  public int y;

  public PositionComponent(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static class Builder {
    private int x;
    private int y;

    public Builder x(int x) {
      this.x = x;
      return this;
    }

    public Builder y(int y) {
      this.y = y;
      return this;
    }

    public PositionComponent build() {
      return new PositionComponent(x, y);
    }
  }
}
