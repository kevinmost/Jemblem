package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.rank.Rank;

public class WeaponStatsComponent implements Component {
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

  public WeaponStatsComponent type(WeaponType type) {
    this.type = type;
    return this;
  }

  public WeaponStatsComponent rank(Rank rank) {
    this.rank = rank;
    return this;
  }

  public WeaponStatsComponent might(int might) {
    this.might = might;
    return this;
  }

  public WeaponStatsComponent weight(int weight) {
    this.weight = weight;
    return this;
  }

  public WeaponStatsComponent accuracy(int accuracy) {
    this.accuracy = accuracy;
    return this;
  }

  public WeaponStatsComponent crit(int crit) {
    this.crit = crit;
    return this;
  }

  public WeaponStatsComponent minRange(int minRange) {
    this.minRange = minRange;
    return this;
  }

  public WeaponStatsComponent maxRange(int maxRange) {
    this.maxRange = maxRange;
    return this;
  }

  public WeaponStatsComponent weaponXP(int weaponXP) {
    this.weaponXP = weaponXP;
    return this;
  }

  public WeaponStatsComponent cost (int cost) {
    this.cost = cost;
    return this;
  }

  public WeaponStatsComponent reversesWeaponTriangle(boolean reversesWeaponTriangle) {
    this.reversesWeaponTriangle = reversesWeaponTriangle;
    return this;
  }
}
