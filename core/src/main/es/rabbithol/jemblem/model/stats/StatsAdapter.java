package es.rabbithol.jemblem.model.stats;

public class StatsAdapter implements Stats {
  private int hp = -1;
  private int strength = -1;
  private int skill = -1;
  private int speed = -1;
  private int luck = -1;
  private int defense = -1;
  private int resistance = -1;
  private int constitution = -1;
  private int aid = -1;
  private int move = -1;

  StatsAdapter() {}

  @Override
  public int hp() {
    return hp;
  }

  @Override
  public int strength() {
    return strength;
  }

  @Override
  public int skill() {
    return skill;
  }

  @Override
  public int speed() {
    return speed;
  }

  @Override
  public int luck() {
    return luck;
  }

  @Override
  public int defense() {
    return defense;
  }

  @Override
  public int resistance() {
    return resistance;
  }

  @Override
  public int constitution() {
    return constitution;
  }

  @Override
  public int aid() {
    return aid;
  }

  @Override
  public int move() {
    return move;
  }

  @Override
  public Stats hp(int hp) {
    this.hp = hp;
    return this;
  }

  @Override
  public Stats strength(int strength) {
    this.strength = strength;
    return this;
  }

  @Override
  public Stats skill(int skill) {
    this.skill = skill;
    return this;
  }

  @Override
  public Stats speed(int speed) {
    this.speed = speed;
    return this;
  }

  @Override
  public Stats luck(int luck) {
    this.luck = luck;
    return this;
  }

  @Override
  public Stats defense(int defense) {
    this.defense = defense;
    return this;
  }

  @Override
  public Stats resistance(int resistance) {
    this.resistance = resistance;
    return this;
  }

  @Override
  public Stats constitution(int constitution) {
    this.constitution = constitution;
    return this;
  }

  @Override
  public Stats aid(int aid) {
    this.aid = aid;
    return this;
  }

  @Override
  public Stats move(int move) {
    this.move = move;
    return this;
  }
}
