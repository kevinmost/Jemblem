package es.rabbithol.jemblem.model.terrain;

import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.FEClassComponent;

public enum Terrains implements Terrain {
  UNCROSSABLE("----", 9000) {
    @Override
    protected int movementCostFor(@NotNull MovementCostType movementCostType) {
      return 9000;
    }
  },
  ARENA("Arena", 1) {
    @Override
    public int avoidBuff() {
      return 10;
    }
  },
  CLIFF("Cliff", 9000),
  DESERT("Desert", 2) {
    @Override
    public int avoidBuff() {
      return 5;
    }

    @Override
    protected int movementCostFor(@NotNull MovementCostType movementCostType) {
      switch (movementCostType) {
        case MAGES:
          return 1;
        case ARMORS:
        case FIGHTERS:
        case NOMAD:
        case NOMAD_TROOPER:
          return 3;
        case KNIGHTS_A:
        case KNIGHTS_B:
          return 4;
      }
      return super.movementCostFor(movementCostType);
    }
  },
  FOREST("Forest", 2) {
    @Override
    public int defenseBuff() {
      return 1;
    }

    @Override
    public int avoidBuff() {
      return 20;
    }

    @Override
    protected int movementCostFor(@NotNull MovementCostType movementCostType) {
      switch (movementCostType) {
        case KNIGHTS_A:
        case KNIGHTS_B:
          return 3;
      }
      return super.movementCostFor(movementCostType);
    }
  },
  MOUNTAIN("Mountain", 4) {
    @Override
    public int defenseBuff() {
      return 1;
    }

    @Override
    public int avoidBuff() {
      return 30;
    }

    @Override
    protected int movementCostFor(@NotNull MovementCostType movementCostType) {
      switch (movementCostType) {
        case KNIGHTS_A:
        case NOMAD:
        case ARMORS:
          return 9000;
        case FIGHTERS:
        case BANDITS:
        case PIRATES:
        case BERSERKER:
          return 3;
        case NOMAD_TROOPER:
          return 5;
        case KNIGHTS_B:
          return 6;
      }
      return super.movementCostFor(movementCostType);
    }
  },
  PLAIN("Plain", 1),
  RIVER("River", 9000) {
    @Override
    protected int movementCostFor(@NotNull MovementCostType movementCostType) {
      switch (movementCostType) {
        case FLIERS:
          return 1;
        case PIRATES:
        case BERSERKER:
          return 2;
        case FOOT:
        case BANDITS:
        case NOMAD_TROOPER:
          return 5;
      }
      return super.movementCostFor(movementCostType);
    }
  },
  THRONE("Throne", 1) {
    // TODO: Heal status conditions and give +5 res when standing on this.
    @Override
    public int defenseBuff() {
      return 2;
    }

    @Override
    public int avoidBuff() {
      return 20;
    }

    @Override
    public int healPercentage() {
      return 10;
    }
  }
  ;

  private final String displayName;
  private final int baseMoveCost;

  Terrains(String displayName, int baseMoveCost) {
    this.displayName = displayName;
    this.baseMoveCost = baseMoveCost;
  }

  protected int movementCostFor(@NotNull MovementCostType movementCostType) {
    if (movementCostType == MovementCostType.FLIERS) {
      return 1;
    }
    return baseMoveCost;
  }

  @Override
  public @NotNull String displayName() {
    return displayName;
  }

  @Override
  public final int movementCostFor(@NotNull Entity character) {
    final FEClassComponent characterFEClass =
        Mappers.getComponentFrom(character, FEClassComponent.class)
            .orElseThrow(() -> new RuntimeException("Should have an FEClassComponent"));
    if (characterFEClass == null) {
      throw new IllegalArgumentException("Entity given has no FEClassComponent");
    }
    return movementCostFor(characterFEClass.feClass.movementCostType());
  }
}
