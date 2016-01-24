package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class DurabilityComponent implements Component {
  public int durability;
  public int remainingUses;

  public DurabilityComponent(int durability, int remainingUses) {
    this.durability = durability;
    this.remainingUses = remainingUses;
  }

  public static class Builder {
    private int durability;
    private int remainingUses;

    public Builder durability(int durability) {
      this.durability = durability;
      return this;
    }

    public Builder remainingUses(int remainingUses) {
      this.remainingUses = remainingUses;
      return this;
    }

    public DurabilityComponent build() {
      return new DurabilityComponent(durability, remainingUses);
    }
  }
}
