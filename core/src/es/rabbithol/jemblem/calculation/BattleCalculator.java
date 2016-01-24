package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import java.util.EnumSet;
import java.util.Set;

import es.rabbithol.jemblem.JemblemGame;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.*;
import es.rabbithol.jemblem.model.Rank;
import es.rabbithol.jemblem.model.StandardRank;
import es.rabbithol.jemblem.model.WeaponType;

public class BattleCalculator {

  private final BattleSystemInfo attacker;
  private final BattleSystemInfo defender;

  public BattleCalculator(Entity attacker, Entity defender) {
    this.attacker = new BattleSystemInfo(attacker);
    this.defender = new BattleSystemInfo(defender);
    JemblemGame.game.component().inject(this);
    calculate();
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

  private BattleSystemInfo other(BattleSystemInfo me) {
    if (me == attacker) {
      return defender;
    }
    return attacker;
  }

  private void calculateAttackSpeed() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.attackSpeed = character.stats.speed;
      final int encumbrance = Math.max(0, character.equippedWeapon.weight - character.stats.constitution);
      character.calculationResult.attackSpeed -= encumbrance;
    }
  }

  private void calculateRepeatedAttack() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      final boolean willHitTwice = character.stats.speed - other(character).stats.speed >= 4;
      character.calculationResult.numAttacks = willHitTwice ? 2 : 1;
    }
  }

  private void calculateHitRate() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.hitRate = character.equippedWeapon.accuracy
          + character.stats.skill * 2
          + character.stats.luck / 2
          // TODO: Support bonus
          + (shouldCharacterGetSRankBonus(character) ? 5 : 0);
    }
  }

  private void calculateEvade() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.evade = character.calculationResult.attackSpeed * 2
          + character.stats.luck;
      // TODO: Terrain bonus
    }
  }

  private void calculateAccuracy() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.accuracy = character.calculationResult.hitRate
          - other(character).calculationResult.evade;
    }
  }

  private void calculateAttackPower() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      // TODO: Light Brand and Wind Sword are a pain in the ass
      // See http://fireemblem.wikia.com/wiki/Attack_(Formula)#Fire_Emblem:_Rekka_no_Ken
      final boolean shouldUseSpecialCaseFormula = false;

      // TODO: Weapon effectiveness
      final int weaponEffectiveness = 1;

      character.calculationResult.attackPower =
          character.stats.strength / (shouldUseSpecialCaseFormula ? 2 : 1)
              + weaponEffectiveness * (character.equippedWeapon.might + getWeaponTriangleDamageBonus(character, other(character)));
    }
  }

  private void calculateDefensePower() {
    final Set<WeaponType> magicWeaponTypes = EnumSet.of(
        WeaponType.MAGIC_ANIMA,
        WeaponType.MAGIC_LIGHT,
        WeaponType.MAGIC_DARK);
    for (BattleSystemInfo character : attackerAndDefender()) {
      final boolean isMagicAttack = magicWeaponTypes.contains(other(character).equippedWeapon.type);

      character.calculationResult.defensePower = 0
          // TODO: Terrain bonus
          + (isMagicAttack ? character.stats.resistance : character.stats.defense);
    }
  }

  private void calculateDamage() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.damage = character.calculationResult.attackPower
          - other(character).calculationResult.defensePower;
    }
  }

  private void calculateCritDamage() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.critDamage = 3 * character.calculationResult.damage;
    }
  }

  private void calculateCritRate() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      // TODO: Class crit bonus for swordmaster and berserker
      final boolean hasClassCritBonus = false;

      character.calculationResult.critRate = character.equippedWeapon.crit
          + character.stats.skill / 2
          + (hasClassCritBonus ? 15 : 0)
          + (shouldCharacterGetSRankBonus(character) ? 5 : 0);
    }
  }

  private void calculateCritEvade() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.critEvade = character.stats.luck;
    }
  }

  private void calculateCritAccuracy() {
    for (BattleSystemInfo character : attackerAndDefender()) {
      character.calculationResult.critAccuracy = character.calculationResult.critRate
          - other(character).calculationResult.critEvade;
    }
  }

  private int getWeaponTriangleDamageBonus(BattleSystemInfo me, BattleSystemInfo them) {
    final WeaponComponent myWeapon = me.equippedWeapon;
    final WeaponComponent theirWeapon = them.equippedWeapon;
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

  private boolean shouldCharacterGetSRankBonus(BattleSystemInfo character) {
    final WeaponType equippedWeaponType = character.equippedWeapon.type;
    final Rank rankInEquippedWeaponType =
        character.weaponProficiency.proficiencies.get(equippedWeaponType);
    return rankInEquippedWeaponType == StandardRank.S;
  }

  private int getSpacesApart(BattleSystemInfo attacker, BattleSystemInfo defender) {
    return Math.abs(attacker.position.y - defender.position.y)
        + Math.abs(attacker.position.x - defender.position.x);
  }

  private BattleSystemInfo[] attackerAndDefender() {
    return new BattleSystemInfo[]{attacker, defender};
  }

  private static class CalculationResult {
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
  }

  private static class BattleSystemInfo {
    private final WeaponComponent equippedWeapon;
    private final StatsComponent stats;
    private final WeaponProficiencyComponent weaponProficiency;
    private final PositionComponent position;
    private final CalculationResult calculationResult;

    public BattleSystemInfo(Entity character) {
      final Entity equippedWeapon = Mappers.getComponentFrom(character, InventoryComponent.class).getEquippedInventoryItem();
      this.equippedWeapon = Mappers.getComponentFrom(equippedWeapon, WeaponComponent.class);

      this.stats = Mappers.getComponentFrom(character, StatsComponent.class);
      this.weaponProficiency = Mappers.getComponentFrom(character, WeaponProficiencyComponent.class);
      this.position = Mappers.getComponentFrom(character, PositionComponent.class);
      this.calculationResult = new CalculationResult();
    }
  }
}
