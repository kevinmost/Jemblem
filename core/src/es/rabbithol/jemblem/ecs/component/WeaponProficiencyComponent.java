package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import java.util.HashMap;
import java.util.Map;

import es.rabbithol.jemblem.model.Rank;
import es.rabbithol.jemblem.model.WeaponType;

public class WeaponProficiencyComponent implements Component {
  public final Map<WeaponType, Rank> proficiencies = new HashMap<>(WeaponType.values().length);
}
