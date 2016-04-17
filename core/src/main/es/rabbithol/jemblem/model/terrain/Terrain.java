package es.rabbithol.jemblem.model.terrain;

import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;

public interface Terrain {
  @NotNull
  String displayName();

  default int defenseBuff() {
    return 0;
  }

  default int avoidBuff() {
    return 0;
  }

  default int healPercentage() {
    return 0;
  }

  int movementCostFor(@NotNull Entity character);
}
