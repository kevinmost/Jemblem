package es.rabbithol.jemblem.model;

import com.badlogic.ashley.core.Entity;

public interface Rank {
  String rankName();
  boolean canCharacterWield(Entity character, WeaponType type);
}
