package es.rabbithol.jemblem.calculation;

import java.util.EnumSet;

import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.fe_class.FEClass;
import es.rabbithol.jemblem.model.fe_class.FEClasses;

public enum BattleCalculationStepLabel {
  ABLE_TO_HIT {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.withinRange = me.equippedWeapon.rangeStrategy.canHit(me.entity, them.position);
        return true;
      };
    }
  },
  ATTACK_SPEED {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.attackSpeed = me.stats.speed();
        final int encumbrance = Math.max(0, me.equippedWeapon.weight - me.stats.constitution());
        me.result.attackSpeed -= encumbrance;
        return true;
      };
    }
  },
  REPEATED_ATTACK {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        final boolean willHitTwice = me.stats.speed() - them.stats.speed() >= 4;
        me.result.numAttacks = willHitTwice ? 2 : 1;
        return true;
      };
    }
  },
  HIT_RATE {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.hitRate = me.equippedWeapon.accuracy
            + me.stats.skill() * 2
            + me.stats.luck() / 2
            // TODO: Support bonus
            + (me.shouldCharacterGetSRankBonus() ? 5 : 0);
        return true;
      };
    }
  },
  EVADE {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.evade = me.result.attackSpeed * 2
            + me.stats.luck()
            + me.getTileCharacterIsOn().terrain.avoidBuff();
        return true;
      };
    }
  },
  ACCURACY {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        // TODO: Light Brand and Wind Sword are a pain in the ass
        // See http://fireemblem.wikia.com/wiki/Attack_(Formula)#Fire_Emblem:_Rekka_no_Ken
        final boolean shouldUseSpecialCaseFormula = false;

        // TODO: Weapon effectiveness
        final int weaponEffectiveness = 1;

        me.result.attackPower =
            me.stats.strength() / (shouldUseSpecialCaseFormula ? 2 : 1)
                + weaponEffectiveness * (me.equippedWeapon.might + me.getWeaponTriangleDamageBonus(them));
        return true;
      };
    }
  },
  ATTACK_POWER {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        final boolean isMagicAttack = WeaponType.MAGIC_WEAPON_TYPES.contains(them.equippedWeapon.type);

        me.result.defensePower = 0
            + me.getTileCharacterIsOn().terrain.defenseBuff()
            + (isMagicAttack ? me.stats.resistance() : me.stats.defense());
        return true;
      };
    }
  },
  DEFENSE_POWER {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.damage = me.result.attackPower
            - them.result.defensePower;
        return true;
      };
    }
  },
  DAMAGE {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.damage = me.result.attackPower
            - them.result.defensePower;
        return true;
      };
    }
  },
  CRIT_DAMAGE {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.critDamage = 3 * me.result.damage;
        return true;
      };
    }
  },
  CRIT_RATE {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
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
        return true;
      };
    }
  },
  CRIT_EVADE {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.critEvade = me.stats.luck();
        return true;
      };
    }
  },
  CRIT_ACCURACY {
    @Override
    public BattleCalculationStep getDefaultImplementation() {
      return (me, them) -> {
        me.result.critAccuracy = me.result.critRate
            - them.result.critEvade;
        return true;
      };
    }
  };

  BattleCalculationStepLabel() {
  }

  public abstract BattleCalculationStep getDefaultImplementation();
}
