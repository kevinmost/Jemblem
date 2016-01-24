package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import es.rabbithol.jemblem.model.Rank;
import es.rabbithol.jemblem.model.WeaponType;

public class WeaponComponent implements Component {
  public WeaponType type;
  public Rank rank;
  public int might;
  public int weight;
  public int accuracy;
  public int crit;
  public int minRange;
  public int maxRange;
  public int weaponXP;
  public int cost;

  public boolean reversesWeaponTriangle;
}
