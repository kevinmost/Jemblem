package es.rabbithol.jemblem.model.range;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.component.PositionComponent;

public class DefaultRangeStrategy extends RangeStrategyAdapter {
  private final int minRange;
  private final int maxRange;

  public DefaultRangeStrategy(int range) {
    this(range, range);
  }

  public DefaultRangeStrategy(int minRange, int maxRange) {
    this.minRange = minRange;
    this.maxRange = maxRange;
  }

  @Override
  public boolean canHit(Entity me, PositionComponent target) {
    final int distance = getDistanceBetween(me, target);
    return minRange <= distance && distance <= maxRange;
  }
}
