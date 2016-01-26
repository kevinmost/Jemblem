package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import java.util.EnumSet;

import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.fe_class.FEClass;
import es.rabbithol.jemblem.model.fe_class.FEClasses;

public class BattleCalculator {

  private final BattleCalculatorInfo[] attackerAndDefender = new BattleCalculatorInfo[2];

  public BattleCalculator(Entity attacker, Entity defender) {
    attackerAndDefender[0] = new BattleCalculatorInfo(attacker);
    attackerAndDefender[1] = new BattleCalculatorInfo(defender);
    calculate();
  }

  public BattleCalculatorResult getAttackerResult() {
    return attackerAndDefender[0].result;
  }

  public BattleCalculatorResult getDefenderResult() {
    return attackerAndDefender[1].result;
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

  private BattleCalculatorInfo other(BattleCalculatorInfo me) {
    if (me == attackerAndDefender[1]) {
      return attackerAndDefender[0];
    }
    return attackerAndDefender[1];
  }

  private void calculateAttackSpeed() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.attackSpeed = character.stats.speed();
      final int encumbrance = Math.max(0, character.equippedWeapon.weight - character.stats.constitution());
      character.result.attackSpeed -= encumbrance;
    }
  }

  private void calculateRepeatedAttack() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      final boolean willHitTwice = character.stats.speed() - other(character).stats.speed() >= 4;
      character.result.numAttacks = willHitTwice ? 2 : 1;
    }
  }

  private void calculateHitRate() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.hitRate = character.equippedWeapon.accuracy
          + character.stats.skill() * 2
          + character.stats.luck() / 2
          // TODO: Support bonus
          + (character.shouldCharacterGetSRankBonus() ? 5 : 0);
    }
  }

  private void calculateEvade() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.evade = character.result.attackSpeed * 2
          + character.stats.luck()
          + character.getTileCharacterIsOn().terrain.avoidBuff();
    }
  }

  private void calculateAccuracy() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.accuracy = character.result.hitRate
          - other(character).result.evade;
    }
  }

  private void calculateAttackPower() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      // TODO: Light Brand and Wind Sword are a pain in the ass
      // See http://fireemblem.wikia.com/wiki/Attack_(Formula)#Fire_Emblem:_Rekka_no_Ken
      final boolean shouldUseSpecialCaseFormula = false;

      // TODO: Weapon effectiveness
      final int weaponEffectiveness = 1;

      character.result.attackPower =
          character.stats.strength() / (shouldUseSpecialCaseFormula ? 2 : 1)
              + weaponEffectiveness * (character.equippedWeapon.might + character.getWeaponTriangleDamageBonus(other(character)));
    }
  }

  private void calculateDefensePower() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      final boolean isMagicAttack = WeaponType.MAGIC_WEAPON_TYPES.contains(other(character).equippedWeapon.type);

      character.result.defensePower = 0
          + character.getTileCharacterIsOn().terrain.defenseBuff()
          + (isMagicAttack ? character.stats.resistance() : character.stats.defense());
    }
  }

  private void calculateDamage() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.damage = character.result.attackPower
          - other(character).result.defensePower;
    }
  }

  private void calculateCritDamage() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.critDamage = 3 * character.result.damage;
    }
  }

  private void calculateCritRate() {
    final EnumSet<? extends FEClass> classesWithCritBonus = EnumSet.of(
        FEClasses.SWORDMASTER_F,
        FEClasses.SWORDMASTER_M,
        FEClasses.BERSERKER
    );
    for (BattleCalculatorInfo character : attackerAndDefender) {
      final boolean hasClassCritBonus = classesWithCritBonus.contains(character.feClass);

      character.result.critRate = character.equippedWeapon.crit
          + character.stats.skill() / 2
          + (hasClassCritBonus ? 15 : 0)
          + (character.shouldCharacterGetSRankBonus() ? 5 : 0);
    }
  }

  private void calculateCritEvade() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.critEvade = character.stats.luck();
    }
  }

  private void calculateCritAccuracy() {
    for (BattleCalculatorInfo character : attackerAndDefender) {
      character.result.critAccuracy = character.result.critRate
          - other(character).result.critEvade;
    }
  }

}
