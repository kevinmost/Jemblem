package es.rabbithol.jemblem.model.fe_class;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.stats.Stats;
import es.rabbithol.jemblem.model.terrain.MovementCostType;

public interface FEClass {
  @NotNull
  String displayName();

  @NotNull
  Stats baseStats();

  @NotNull
  Stats maxStats();

  @NotNull
  Set<WeaponType> supportedWeaponTypes();

  /**
   * @return the promotion of this class, or {@code null} if there is no promotion (either this
   * class is already promoted, or simply does not promote)
   */
  @Nullable
  FEClass promotesTo();

  @NotNull
  MovementCostType movementCostType();
}
