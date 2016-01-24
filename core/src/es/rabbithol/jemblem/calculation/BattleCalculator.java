package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import java.util.EnumSet;
import java.util.Set;

import es.rabbithol.jemblem.JemblemGame;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.*;
import es.rabbithol.jemblem.model.rank.Rank;
import es.rabbithol.jemblem.model.rank.StandardRank;
import es.rabbithol.jemblem.model.WeaponType;

public class BattleCalculator {

  private final Info attacker;
  private final Info defender;

  public BattleCalculator(Entity attacker, Entity defender) {
    this.attacker = new Info(attacker);
    this.defender = new Info(defender);
    JemblemGame.game.component().inject(this);
    calculate();
  }

  public Result getAttackerResult() {
    return attacker.result;
  }

  public Result getDefenderResult() {
    return defender.result;
  }

  private void calculate() {
    // Using FE7 formulas from: http://fireemblem.wikia.com/wiki/Battle_Formulas
    calculateAttackSpeed();

    calculateRepeatedAttack();

    calculateHitRate();
    calculateEvade();
    calculateAccuracy();

    calculateAttackPower();
    calculateDefensePower();

    calculateDamage();
    calculateCritDamage();

    calculateCritRate();
    calculateCritEvade();
    calculateCritAccuracy();
  }

  private Info other(Info me) {
    if (me == attacker) {
      return defender;
    }
    return attacker;
  }

  private void calculateAttackSpeed() {
    for (Info character : attackerAndDefender()) {
      character.result.attackSpeed = character.stats.speed;
      final int encumbrance = Math.max(0, character.equippedWeapon.weight - character.stats.constitution);
      character.result.attackSpeed -= encumbrance;
    }
  }

  private void calculateRepeatedAttack() {
    for (Info character : attackerAndDefender()) {
      final boolean willHitTwice = character.stats.speed - other(character).stats.speed >= 4;
      character.result.numAttacks = willHitTwice ? 2 : 1;
    }
  }

  private void calculateHitRate() {
    for (Info character : attackerAndDefender()) {
      character.result.hitRate = character.equippedWeapon.accuracy
          + character.stats.skill * 2
          + character.stats.luck / 2
          // TODO: Support bonus
          + (shouldCharacterGetSRankBonus(character) ? 5 : 0);
    }
  }

  private void calculateEvade() {
    for (Info character : attackerAndDefender()) {
      character.result.evade = character.result.attackSpeed * 2
          + character.stats.luck;
      // TODO: Terrain bonus
    }
  }

  private void calculateAccuracy() {
    for (Info character : attackerAndDefender()) {
      character.result.accuracy = character.result.hitRate
          - other(character).result.evade;
    }
  }

  private void calculateAttackPower() {
    for (Info character : attackerAndDefender()) {
      // TODO: Light Brand and Wind Sword are a pain in the ass
      // See http://fireemblem.wikia.com/wiki/Attack_(Formula)#Fire_Emblem:_Rekka_no_Ken
      final boolean shouldUseSpecialCaseFormula = false;

      // TODO: Weapon effectiveness
      final int weaponEffectiveness = 1;

      character.result.attackPower =
          character.stats.strength / (shouldUseSpecialCaseFormula ? 2 : 1)
              + weaponEffectiveness * (character.equippedWeapon.might + getWeaponTriangleDamageBonus(character, other(character)));
    }
  }

  private void calculateDefensePower() {
    final Set<WeaponType> magicWeaponTypes = EnumSet.of(
        WeaponType.MAGIC_ANIMA,
        WeaponType.MAGIC_LIGHT,
        WeaponType.MAGIC_DARK);
    for (Info character : attackerAndDefender()) {
      final boolean isMagicAttack = magicWeaponTypes.contains(other(character).equippedWeapon.type);

      character.result.defensePower = 0
          // TODO: Terrain bonus
          + (isMagicAttack ? character.stats.resistance : character.stats.defense);
    }
  }

  private void calculateDamage() {
    for (Info character : attackerAndDefender()) {
      character.result.damage = character.result.attackPower
          - other(character).result.defensePower;
    }
  }

  private void calculateCritDamage() {
    for (Info character : attackerAndDefender()) {
      character.result.critDamage = 3 * character.result.damage;
    }
  }

  private void calculateCritRate() {
    for (Info character : attackerAndDefender()) {
      // TODO: Class crit bonus for swordmaster and berserker
      final boolean hasClassCritBonus = false;

      character.result.critRate = character.equippedWeapon.crit
          + character.stats.skill / 2
          + (hasClassCritBonus ? 15 : 0)
          + (shouldCharacterGetSRankBonus(character) ? 5 : 0);
    }
  }

  private void calculateCritEvade() {
    for (Info character : attackerAndDefender()) {
      character.result.critEvade = character.stats.luck;
    }
  }

  private void calculateCritAccuracy() {
    for (Info character : attackerAndDefender()) {
      character.result.critAccuracy = character.result.critRate
          - other(character).result.critEvade;
    }
  }

  private int getWeaponTriangleDamageBonus(Info me, Info them) {
    final WeaponStatsComponent myWeapon = me.equippedWeapon;
    final WeaponStatsComponent theirWeapon = them.equippedWeapon;
    // Two *reavers cancel each other out
    final int multiplier = myWeapon.reversesWeaponTriangle ^ theirWeapon.reversesWeaponTriangle ? 2 : 1;

    final int value;
    if (myWeapon.type.hasAdvantageOver() == theirWeapon.type) {
      value = 15;
    } else if (theirWeapon.type.hasAdvantageOver() == myWeapon.type) {
      value = -15;
    } else {
      value = 0;
    }
    return multiplier * value;
  }

  private boolean shouldCharacterGetSRankBonus(Info character) {
    final WeaponType equippedWeaponType = character.equippedWeapon.type;
    final Rank rankInEquippedWeaponType =
        character.weaponProficiency.proficiencies.get(equippedWeaponType);
    return rankInEquippedWeaponType == StandardRank.S;
  }

  private int getSpacesApart(Info attacker, Info defender) {
    return Math.abs(attacker.position.y - defender.position.y)
        + Math.abs(attacker.position.x - defender.position.x);
  }

  private Info[] attackerAndDefender() {
    return new Info[]{attacker, defender};
  }

  public static class Result {
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

    private Result() {}
  }

  private static class Info {
    private final WeaponStatsComponent equippedWeapon;
    private final StatsComponent stats;
    private final WeaponProficiencyComponent weaponProficiency;
    private final PositionComponent position;
    private final Result result;

    public Info(Entity character) {
      final Entity equippedWeapon = Mappers.getComponentFrom(character, InventoryComponent.class).getEquippedInventoryItem();
      this.equippedWeapon = Mappers.getComponentFrom(equippedWeapon, WeaponStatsComponent.class);

      this.stats = Mappers.getComponentFrom(character, StatsComponent.class);
      this.weaponProficiency = Mappers.getComponentFrom(character, WeaponProficiencyComponent.class);
      this.position = Mappers.getComponentFrom(character, PositionComponent.class);
      this.result = new Result();
    }
  }
}
