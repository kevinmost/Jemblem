package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.*;

import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.rank.Rank;
import es.rabbithol.jemblem.model.rank.StandardRank;

public class WeaponProficiencyComponent implements Component {
  public final Map<WeaponType, Rank> proficiencies = new HashMap<>(WeaponType.values().length);

  public final Set<Rank> overrideRanks = new HashSet<>();

  public WeaponProficiencyComponent() {
    for (WeaponType weaponType : WeaponType.values()) {
      this.proficiencies.put(weaponType, StandardRank.NO);
    }
  }

  public WeaponProficiencyComponent proficiency(WeaponType type, Rank rank) {
    this.proficiencies.put(type, rank);
    return this;
  }

  public WeaponProficiencyComponent overrideRank(Rank overrideRank) {
    this.overrideRanks.add(overrideRank);
    return this;
  }

  public boolean canWield(Entity weapon) {
    final WeaponStatsComponent weaponStats =
        Mappers.getComponentFrom(weapon, WeaponStatsComponent.class);
    return weaponStats != null && canWield(weaponStats);
  }

  public boolean canWield(WeaponStatsComponent weaponStats) {
    if (overrideRanks.contains(weaponStats.rank)) {
      return true;
    }

    final Rank characterRank = proficiencies.get(weaponStats.type);
    return characterRank != null &&
        characterRank instanceof StandardRank &&
        weaponStats.rank instanceof StandardRank &&
        ((StandardRank)characterRank).ordinal() >= ((StandardRank)weaponStats.rank).ordinal();
  }
}
