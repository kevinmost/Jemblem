package es.rabbithol.jemblem.model;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.WeaponProficiencyComponent;

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

  @Override
  public boolean canCharacterWield(Entity character, WeaponType type) {
    final Rank characterRank = Mappers.getComponentFrom(character, WeaponProficiencyComponent.class)
        .proficiencies.get(type);
    if (characterRank == null) {
      return false;
    }
    return characterRank instanceof StandardRank &&
        ((StandardRank) characterRank).ordinal() >= this.ordinal();
  }
}
