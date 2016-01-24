package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
  public int maxHP;
  public int currentHP;

  public HealthComponent(int maxHP, int currentHP) {
    this.maxHP = maxHP;
    this.currentHP = currentHP;
  }

  public static class Builder {
    private int maxHP;
    private int currentHP;

    public Builder maxHP(int maxHP) {
      this.maxHP = maxHP;
      return this;
    }

    public Builder currentHP(int currentHP) {
      this.currentHP = currentHP;
      return this;
    }

    public HealthComponent build() {
      return new HealthComponent(maxHP, currentHP);
    }
  }
}
