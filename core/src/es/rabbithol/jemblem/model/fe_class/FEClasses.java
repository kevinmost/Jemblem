package es.rabbithol.jemblem.model.fe_class;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Set;

import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.stats.Stats;
import es.rabbithol.jemblem.model.terrain.MovementCostType;

public enum FEClasses implements FEClass {
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
  }
  ;

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
