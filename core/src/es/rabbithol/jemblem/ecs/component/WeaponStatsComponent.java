package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import es.rabbithol.jemblem.model.Rank;
import es.rabbithol.jemblem.model.WeaponType;

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

  public WeaponStatsComponent(WeaponType type, Rank rank, int might, int weight, int accuracy, int crit, int minRange, int maxRange, int weaponXP, int cost, boolean reversesWeaponTriangle) {
    this.type = type;
    this.rank = rank;
    this.might = might;
    this.weight = weight;
    this.accuracy = accuracy;
    this.crit = crit;
    this.minRange = minRange;
    this.maxRange = maxRange;
    this.weaponXP = weaponXP;
    this.cost = cost;
    this.reversesWeaponTriangle = reversesWeaponTriangle;
  }

  public static class Builder {
    private WeaponType type;
    private Rank rank;
    private int might;
    private int weight;
    private int accuracy;
    private int crit;
    private int minRange;
    private int maxRange;
    private int weaponXP;
    private int cost;
    private boolean reversesWeaponTriangle;

    public Builder type(WeaponType type) {
      this.type = type;
      return this;
    }

    public Builder rank(Rank rank) {
      this.rank = rank;
      return this;
    }

    public Builder might(int might) {
      this.might = might;
      return this;
    }

    public Builder weight(int weight) {
      this.weight = weight;
      return this;
    }

    public Builder accuracy(int accuracy) {
      this.accuracy = accuracy;
      return this;
    }

    public Builder crit(int crit) {
      this.crit = crit;
      return this;
    }

    public Builder minRange(int minRange) {
      this.minRange = minRange;
      return this;
    }

    public Builder maxRange(int maxRange) {
      this.maxRange = maxRange;
      return this;
    }

    public Builder weaponXP(int weaponXP) {
      this.weaponXP = weaponXP;
      return this;
    }

    public Builder cost (int cost) {
      this.cost = cost;
      return this;
    }

    public Builder reversesWeaponTriangle(boolean reversesWeaponTriangle) {
      this.reversesWeaponTriangle = reversesWeaponTriangle;
      return this;
    }
  }
}
