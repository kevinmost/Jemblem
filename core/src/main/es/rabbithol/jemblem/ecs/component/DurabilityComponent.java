package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class DurabilityComponent implements Component {
  public int durability;
  public int remainingUses;

  public DurabilityComponent() {
  }

  public DurabilityComponent(int durability) {
    this(durability, durability);
  }

  public DurabilityComponent(int durability, int remainingUses) {
    this.durability = durability;
    this.remainingUses = remainingUses;
  }

  public DurabilityComponent durability(int durability) {
    this.durability = durability;
    return this;
  }

  public DurabilityComponent remainingUses(int remainingUses) {
    this.remainingUses = remainingUses;
    return this;
  }
}
