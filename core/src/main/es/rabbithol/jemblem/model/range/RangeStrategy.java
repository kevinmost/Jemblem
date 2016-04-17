package es.rabbithol.jemblem.model.range;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.component.PositionComponent;

public interface RangeStrategy {
  boolean canHit(Entity me, PositionComponent target);
}
