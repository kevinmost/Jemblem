package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
  public int currentHP;

  public HealthComponent(int currentHP) {
    this.currentHP = currentHP;
  }
}
