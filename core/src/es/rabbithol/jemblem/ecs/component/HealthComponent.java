package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
  public int maxHP;
  public int currentHP;

  public HealthComponent() {
  }

  public HealthComponent(int maxHP) {
    this(maxHP, maxHP);
  }

  public HealthComponent(int currentHP, int maxHP) {
    this.currentHP = currentHP;
    this.maxHP = maxHP;
  }
}
