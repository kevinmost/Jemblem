package es.rabbithol.jemblem.model.range;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.PositionComponent;
import es.rabbithol.jemblem.ecs.component.StatsComponent;

// For all of the weapons with range "1 - Mag/2"
public class HalfOfMagicRangeStrategy extends RangeStrategyAdapter {

  @Override
  public boolean canHit(Entity me, PositionComponent target) {
    final int distance = getDistanceBetween(me, target);
    final int magic = Mappers.getComponentFrom(me, StatsComponent.class)
        .orElseThrow(() -> new RuntimeException("You should have a StatsComponent!"))
        .stats
        .strength();
    return distance <= (magic / 2);
  }
}
