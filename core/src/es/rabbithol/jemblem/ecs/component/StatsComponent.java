package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class StatsComponent implements Component {
  public int strength;
  public int skill;
  public int speed;
  public int luck;
  public int defense;
  public int resistance;
  public int constitution;
  public int aid;
  public int move;

  public StatsComponent strength(int strength) {
    this.strength = strength;
    return this;
  }

  public StatsComponent skill(int skill) {
    this.skill = skill;
    return this;
  }

  public StatsComponent speed(int speed) {
    this.speed = speed;
    return this;
  }

  public StatsComponent luck(int luck) {
    this.luck = luck;
    return this;
  }

  public StatsComponent defense(int defense) {
    this.defense = defense;
    return this;
  }

  public StatsComponent resistance(int resistance) {
    this.resistance = resistance;
    return this;
  }

  public StatsComponent constitution(int constitution) {
    this.constitution = constitution;
    return this;
  }

  public StatsComponent aid(int aid) {
    this.aid = aid;
    return this;
  }

  public StatsComponent move(int move) {
    this.move = move;
    return this;
  }
}
