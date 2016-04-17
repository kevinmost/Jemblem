package es.rabbithol.jemblem.model.fe_class;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import es.rabbithol.jemblem.ecs.component.StatsComponent;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.stats.Stats;
import es.rabbithol.jemblem.model.terrain.MovementCostType;

public enum FEClasses implements FEClass {
  NULL("", MovementCostType.NULL) {
    @Override
    public @NotNull Stats baseStats() {
      return StatsComponent.NULL_STATS.stats;
    }

    @Override
    public @NotNull Stats maxStats() {
      return StatsComponent.NULL_STATS.stats;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return Collections.emptySet();
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return null;
    }
  },
  LORD_LYN("Lord", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(16)
          .strength(4)
          .skill(7)
          .speed(9)
          .luck(0)
          .defense(2)
          .resistance(0)
          .constitution(5)
          .move(5);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.NON_PROMOTED_MAX_STATS;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.SWORD);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return BLADE_LORD;
    }
  },
  BLADE_LORD("Blade Lord", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(18)
          .strength(3)
          .skill(3)
          .speed(4)
          .luck(0)
          .defense(5)
          .resistance(0)
          .constitution(6)
          .move(6);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.get()
          .hp(60)
          .strength(24)
          .skill(29)
          .speed(30)
          .luck(30)
          .defense(22)
          .resistance(22)
          .constitution(25)
          .move(15);
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.SWORD, WeaponType.BOW);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return null;
    }
  },
  MYRMIDON_M("Myrmidon", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(16)
          .strength(4)
          .skill(9)
          .speed(9)
          .luck(0)
          .defense(2)
          .resistance(0)
          .constitution(8)
          .move(5);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.NON_PROMOTED_MAX_STATS;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.SWORD);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return SWORDMASTER_M;
    }
  },
  SWORDMASTER_M("Swordmaster", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(21)
          .strength(6)
          .skill(11)
          .speed(10)
          .luck(0)
          .defense(5)
          .resistance(2)
          .constitution(9)
          .move(6);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.get()
          .hp(60)
          .strength(24)
          .skill(29)
          .speed(30)
          .luck(30)
          .defense(22)
          .resistance(23)
          .constitution(25)
          .move(15);
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.SWORD);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return null;
    }
  },
  MYRMIDON_F("Myrmidon", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(15)
          .strength(3)
          .skill(9)
          .speed(10)
          .luck(0)
          .defense(1)
          .resistance(1)
          .constitution(5)
          .move(5);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.NON_PROMOTED_MAX_STATS;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.SWORD);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return SWORDMASTER_F;
    }
  },
  SWORDMASTER_F("Swordmaster", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(19)
          .strength(6)
          .skill(11)
          .speed(12)
          .luck(0)
          .defense(4)
          .resistance(3)
          .constitution(7)
          .move(6);

    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.get()
          .hp(60)
          .strength(22)
          .skill(29)
          .speed(30)
          .luck(30)
          .defense(22)
          .resistance(25)
          .constitution(25)
          .move(15);
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.SWORD);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return null;
    }
  },
  FIGHTER("Fighter", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(20)
          .strength(5)
          .skill(2)
          .speed(4)
          .luck(0)
          .defense(2)
          .resistance(0)
          .constitution(11)
          .move(5);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.NON_PROMOTED_MAX_STATS;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.AXE);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return WARRIOR;
    }
  },
  WARRIOR("Warrior", MovementCostType.FOOT) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(28)
          .skill(5)
          .speed(6)
          .luck(0)
          .defense(5)
          .resistance(0)
          .constitution(13)
          .move(6);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.get()
          .hp(60)
          .strength(30)
          .skill(28)
          .speed(26)
          .luck(30)
          .defense(26)
          .resistance(22)
          .constitution(20)
          .move(15);
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.AXE, WeaponType.BOW);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return null;
    }
  },
  BRIGAND("Brigand", MovementCostType.BANDITS) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(20)
          .strength(5)
          .skill(1)
          .speed(5)
          .luck(0)
          .defense(3)
          .resistance(0)
          .constitution(12)
          .move(5);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.NON_PROMOTED_MAX_STATS;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.AXE);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return BERSERKER;
    }
  },
  PIRATE("Pirate", MovementCostType.PIRATES) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(19)
          .strength(4)
          .skill(2)
          .speed(6)
          .luck(0)
          .defense(3)
          .resistance(0)
          .constitution(10)
          .move(5);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.NON_PROMOTED_MAX_STATS;
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.AXE);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return BERSERKER;
    }
  },
  BERSERKER("Berserker", MovementCostType.BERSERKER) {
    @Override
    public @NotNull Stats baseStats() {
      return Stats.get()
          .hp(24)
          .strength(7)
          .skill(6)
          .speed(7)
          .luck(0)
          .defense(6)
          .resistance(0)
          .constitution(13)
          .move(6);
    }

    @Override
    public @NotNull Stats maxStats() {
      return Stats.get()
          .hp(60)
          .strength(30)
          .skill(29)
          .speed(28)
          .luck(30)
          .defense(23)
          .resistance(21)
          .constitution(20)
          .move(15);
    }

    @Override
    public @NotNull Set<WeaponType> supportedWeaponTypes() {
      return EnumSet.of(WeaponType.AXE);
    }

    @Override
    public @Nullable FEClass promotesTo() {
      return null;
    }
  };

  @NotNull
  private final String displayName;

  @NotNull
  private final MovementCostType movementCostType;

  FEClasses(@NotNull String displayName, @NotNull MovementCostType movementCostType) {
    this.displayName = displayName;
    this.movementCostType = movementCostType;
  }

  @NotNull
  @Override
  public String displayName() {
    return displayName;
  }

  @Override
  public @NotNull MovementCostType movementCostType() {
    return movementCostType;
  }
}
