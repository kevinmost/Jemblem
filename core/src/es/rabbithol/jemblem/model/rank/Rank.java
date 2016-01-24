package es.rabbithol.jemblem.model.rank;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.component.WeaponProficiencyComponent;
import es.rabbithol.jemblem.model.WeaponType;

public interface Rank {
  String rankName();
}
