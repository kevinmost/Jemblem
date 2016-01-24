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

  @Override
  public boolean canCharacterWield(Entity character, WeaponType thisWeaponType) {
    final WeaponProficiencyComponent proficiencies =
        Mappers.getComponentFrom(character, WeaponProficiencyComponent.class);
    if (proficiencies == null) {
      return false;
    }
    final Rank rankInThisWeaponType = proficiencies.getRankIn(thisWeaponType);
    return rankInThisWeaponType instanceof StandardRank &&
        ((StandardRank)rankInThisWeaponType).ordinal() >= this.ordinal();
  }

  public StandardRank nextRank() {
    if (this == S || this == NO) {
      throw new UnsupportedOperationException("Can't get a next rank after " + this.name());
    }
    return StandardRank.values()[this.ordinal() + 1];
  }
}
