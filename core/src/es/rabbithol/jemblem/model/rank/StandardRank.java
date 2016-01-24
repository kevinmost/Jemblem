package es.rabbithol.jemblem.model.rank;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.WeaponProficiencyComponent;
import es.rabbithol.jemblem.model.WeaponType;

public enum StandardRank implements Rank {
  NO {
    @Override
    public String rankName() {
      return "-";
    }
  },
  E,
  D,
  C,
  B,
  A,
  S,
  ;

  StandardRank() {
  }

  @Override
  public String rankName() {
    return name();
  }
}
