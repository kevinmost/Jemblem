package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.range.DefaultRangeStrategy;
import es.rabbithol.jemblem.model.range.RangeStrategy;
import es.rabbithol.jemblem.model.rank.Rank;
import es.rabbithol.jemblem.model.rank.StandardRank;

public class WeaponStatsComponent implements Component {
  public static final WeaponStatsComponent NULL_WEAPON_STATS_COMPONENT = new WeaponStatsComponent()
      .type(WeaponType.NULL)
      .rank(StandardRank.NO)
      .might(0)
      .weight(0)
      .accuracy(0)
      .crit(0)
      .range(new DefaultRangeStrategy(-2))
      .weaponXP(0)
      .cost(0)
      .reversesWeaponTriangle(false);

  public WeaponType type;
  public Rank rank;
  public int might;
  public int weight;
  public int accuracy;
  public int crit;
  public RangeStrategy rangeStrategy;
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

  public WeaponStatsComponent range(@NotNull RangeStrategy rangeStrategy) {
    this.rangeStrategy = rangeStrategy;
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
