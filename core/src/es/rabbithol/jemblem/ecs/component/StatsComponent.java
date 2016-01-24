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
  public int move;

  public StatsComponent(int strength, int skill, int speed, int luck, int defense, int resistance, int constitution, int move) {
    this.strength = strength;
    this.skill = skill;
    this.speed = speed;
    this.luck = luck;
    this.defense = defense;
    this.resistance = resistance;
    this.constitution = constitution;
    this.move = move;
  }

  public static class Builder {
    private int strength;
    private int skill;
    private int speed;
    private int luck;
    private int defense;
    private int resistance;
    private int constitution;
    private int move;

    public Builder strength(int strength) {
      this.strength = strength;
      return this;
    }

    public Builder skill(int skill) {
      this.skill = skill;
      return this;
    }

    public Builder speed(int speed) {
      this.speed = speed;
      return this;
    }

    public Builder luck(int luck) {
      this.luck = luck;
      return this;
    }

    public Builder defense(int defense) {
      this.defense = defense;
      return this;
    }

    public Builder resistance(int resistance) {
      this.resistance = resistance;
      return this;
    }

    public Builder constitution(int constitution) {
      this.constitution = constitution;
      return this;
    }

    public Builder move(int move) {
      this.move = move;
      return this;
    }

    public StatsComponent build() {
      return new StatsComponent(strength, skill, speed, luck, defense, resistance, constitution, move);
    }

  }
}
