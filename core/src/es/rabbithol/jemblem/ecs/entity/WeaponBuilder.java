package es.rabbithol.jemblem.ecs.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.component.DurabilityComponent;
import es.rabbithol.jemblem.ecs.component.WeaponStatsComponent;

public class WeaponBuilder {
  private DurabilityComponent durability;
  private WeaponStatsComponent weaponStats;

  public WeaponBuilder durability(DurabilityComponent durability) {
    this.durability = durability;
    return this;
  }

  public WeaponBuilder weaponStats(WeaponStatsComponent weaponStats) {
    this.weaponStats = weaponStats;
    return this;
  }

  public Entity build() {
    final Entity entity = new Entity();
    for (Component component : new Component[]{durability, weaponStats}) {
      if (component != null) {
        entity.add(component);
      }
    }
    return entity;
  }
}
