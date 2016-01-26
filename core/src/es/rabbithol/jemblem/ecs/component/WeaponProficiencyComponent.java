package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.rank.Rank;
import es.rabbithol.jemblem.model.rank.StandardRank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class WeaponProficiencyComponent implements Component {
  private final Map<WeaponType, Rank> proficiencies = new HashMap<>(WeaponType.values().length);

  @NotNull
  public WeaponProficiencyComponent proficiency(WeaponType type, Rank rank) {
    this.proficiencies.put(type, rank);
    return this;
  }

  @NotNull
  public Rank getRankIn(@Nullable WeaponType type) {
    if (type == null) {
      return StandardRank.NO;
    }
    return proficiencies.getOrDefault(type, StandardRank.NO);
  }
}
