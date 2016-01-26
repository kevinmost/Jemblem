package es.rabbithol.jemblem.model.rank;

import com.badlogic.ashley.core.Entity;
import es.rabbithol.jemblem.model.WeaponType;

public interface Rank {
  String rankName();
  boolean canCharacterWield(Entity character, WeaponType thisWeaponType);
}
