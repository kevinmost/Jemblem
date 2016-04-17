package es.rabbithol.jemblem.model.range;

import com.badlogic.ashley.core.Entity;

import java.util.Optional;

import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.PositionComponent;
import es.rabbithol.jemblem.util.MathUtil;

abstract class RangeStrategyAdapter implements RangeStrategy {
  protected int getDistanceBetween(Entity me, PositionComponent target) {
    final Optional<PositionComponent> myPositionMaybe =
        Mappers.getComponentFrom(me, PositionComponent.class);
    if (!myPositionMaybe.isPresent()) {
      return -1;
    }
    final PositionComponent myPosition = myPositionMaybe.get();
    // TODO: Maybe we need to easier-reconcile the difference between a Tile and a PositionComponent
    // Should a Tile be an Entity with a PositionComponent?
    return MathUtil.distanceBetween(myPosition, target);
  }
}
