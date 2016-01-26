package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class NameComponent implements Component {
  public String name;

  public NameComponent() {
    this("");
  }

  public NameComponent(String name) {
    this.name = name;
  }
}
