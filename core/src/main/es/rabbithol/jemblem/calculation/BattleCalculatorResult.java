package es.rabbithol.jemblem.calculation;

public class BattleCalculatorResult {
  public boolean withinRange;
  public int attackSpeed;
  public int numAttacks;
  public int hitRate;
  public int evade;
  public int accuracy;
  public int attackPower;
  public int defensePower;
  public int damage;
  public int critDamage;
  public int critRate;
  public int critEvade;
  public int critAccuracy;

  BattleCalculatorResult() {
  }

  @Override
  public String toString() {
    return "withinRange: " + withinRange + "\n" +
        "attackSpeed: " + attackSpeed + "\n" +
        "numAttack: " + numAttacks + "\n" +
        "hitRate: " + hitRate + "\n" +
        "evade: " + evade + "\n" +
        "accuracy: " + accuracy + "\n" +
        "attackPower: " + attackPower + "\n" +
        "defensePower: " + defensePower + "\n" +
        "damage: " + damage + "\n" +
        "critDamage: " + critDamage + "\n" +
        "critRate: " + critRate + "\n" +
        "critEvade: " + critEvade + "\n" +
        "critAccuracy: " + critAccuracy + "\n";
  }
}
