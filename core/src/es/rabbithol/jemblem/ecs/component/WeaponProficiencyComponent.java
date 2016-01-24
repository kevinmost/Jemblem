package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;
import java.util.Map;

import es.rabbithol.jemblem.model.Rank;
import es.rabbithol.jemblem.model.StandardRank;
import es.rabbithol.jemblem.model.WeaponType;
import javafx.util.Pair;

public class WeaponProficiencyComponent implements Component {
  public final Map<WeaponType, Rank> proficiencies = new HashMap<>(WeaponType.values().length);

  @SafeVarargs
  public WeaponProficiencyComponent(Pair<WeaponType, Rank>... proficiencies) {
    if (proficiencies != null) {
      for (Pair<WeaponType, Rank> proficiency : proficiencies) {
        this.proficiencies.put(proficiency.getKey(), proficiency.getValue());
      }
    }
    for (WeaponType weaponType : WeaponType.values()) {
      this.proficiencies.putIfAbsent(weaponType, StandardRank.NO);
    }
  }
}
