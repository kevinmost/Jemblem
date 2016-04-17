package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import java.util.EnumSet;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.fe_class.FEClass;
import es.rabbithol.jemblem.model.fe_class.FEClasses;

public class BattleCalculator {

  private final BattleCalculatorInfo attacker;
  private final BattleCalculatorInfo defender;

  public BattleCalculator(Entity attacker, Entity defender) {
    this.attacker = new BattleCalculatorInfo(attacker);
    this.defender = new BattleCalculatorInfo(defender);
    calculate();
  }

  public BattleCalculatorResult getAttackerResult() {
    return attacker.result;
  }

  public BattleCalculatorResult getDefenderResult() {
    return defender.result;
  }

  private void calculate() {
    // Using FE7 formulas from: http://fireemblem.wikia.com/wiki/Battle_Formulas
    Stream.<BiConsumer<BattleCalculatorInfo, BattleCalculatorInfo>>of(
        this::calculateAbleToHit,

        this::calculateAttackSpeed,

        this::calculateRepeatedAttack,

        this::calculateHitRate,
        this::calculateEvade,
        this::calculateAccuracy,

        this::calculateAttackPower,
        this::calculateDefensePower,

        this::calculateDamage,
        this::calculateCritDamage,

        this::calculateCritRate,
        this::calculateCritEvade,
        this::calculateCritAccuracy)
        .forEach(method -> {
          method.accept(attacker, defender);
          method.accept(defender, attacker);
        });
  }

  private void calculateAbleToHit(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.withinRange = me.equippedWeapon.rangeStrategy.canHit(me.entity, them.position);
  }

  private void calculateAttackSpeed(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.attackSpeed = me.stats.speed();
    final int encumbrance = Math.max(0, me.equippedWeapon.weight - me.stats.constitution());
    me.result.attackSpeed -= encumbrance;
  }

  private void calculateRepeatedAttack(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    final boolean willHitTwice = me.stats.speed() - them.stats.speed() >= 4;
    me.result.numAttacks = willHitTwice ? 2 : 1;
  }

  private void calculateHitRate(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.hitRate = me.equippedWeapon.accuracy
        + me.stats.skill() * 2
        + me.stats.luck() / 2
        // TODO: Support bonus
        + (me.shouldCharacterGetSRankBonus() ? 5 : 0);
  }

  private void calculateEvade(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.evade = me.result.attackSpeed * 2
        + me.stats.luck()
        + me.getTileCharacterIsOn().terrain.avoidBuff();
  }

  private void calculateAccuracy(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.accuracy = me.result.hitRate
        - them.result.evade;
  }

  private void calculateAttackPower(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    // TODO: Light Brand and Wind Sword are a pain in the ass
    // See http://fireemblem.wikia.com/wiki/Attack_(Formula)#Fire_Emblem:_Rekka_no_Ken
    final boolean shouldUseSpecialCaseFormula = false;

    // TODO: Weapon effectiveness
    final int weaponEffectiveness = 1;

    me.result.attackPower =
        me.stats.strength() / (shouldUseSpecialCaseFormula ? 2 : 1)
            + weaponEffectiveness * (me.equippedWeapon.might + me.getWeaponTriangleDamageBonus(them));
  }

  private void calculateDefensePower(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    final boolean isMagicAttack = WeaponType.MAGIC_WEAPON_TYPES.contains(them.equippedWeapon.type);

    me.result.defensePower = 0
        + me.getTileCharacterIsOn().terrain.defenseBuff()
        + (isMagicAttack ? me.stats.resistance() : me.stats.defense());
  }

  private void calculateDamage(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.damage = me.result.attackPower
        - them.result.defensePower;
  }

  private void calculateCritDamage(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.critDamage = 3 * me.result.damage;
  }

  private void calculateCritRate(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    final EnumSet<? extends FEClass> classesWithCritBonus = EnumSet.of(
        FEClasses.SWORDMASTER_F,
        FEClasses.SWORDMASTER_M,
        FEClasses.BERSERKER
    );
    final boolean hasClassCritBonus = classesWithCritBonus.contains(me.feClass);

    me.result.critRate = me.equippedWeapon.crit
        + me.stats.skill() / 2
        + (hasClassCritBonus ? 15 : 0)
        + (me.shouldCharacterGetSRankBonus() ? 5 : 0);
  }

  private void calculateCritEvade(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.critEvade = me.stats.luck();
  }

  private void calculateCritAccuracy(BattleCalculatorInfo me, BattleCalculatorInfo them) {
    me.result.critAccuracy = me.result.critRate
        - them.result.critEvade;
  }

}
