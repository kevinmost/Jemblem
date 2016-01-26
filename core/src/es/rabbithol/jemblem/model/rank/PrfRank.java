package es.rabbithol.jemblem.model.rank;

import com.badlogic.ashley.core.Entity;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.FEClassComponent;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.fe_class.FEClass;
import es.rabbithol.jemblem.model.fe_class.FEClasses;

import java.util.EnumSet;
import java.util.Set;

public enum PrfRank implements Rank {
  MANI_KATTI("Prf") {
    private final Set<? extends FEClass> classesThatCanWieldManiKatti = EnumSet.of(
        FEClasses.LORD_LYN,
        FEClasses.BLADE_LORD
    );

    @Override
    public boolean canCharacterWield(Entity character, WeaponType thisWeaponType) {
      return classesThatCanWieldManiKatti.contains(
          Mappers.getComponentFrom(character, FEClassComponent.class).feClass);
    }
  },
  WO_DAO(StandardRank.D) {
    private final Set<? extends FEClass> classesThatCanWieldWoDao = EnumSet.of(
        FEClasses.LORD_LYN,
        FEClasses.BLADE_LORD,
        FEClasses.MYRMIDON_M,
        FEClasses.SWORDMASTER_M
        // TODO: Also female swordmasters and myrmidons
    );

    @Override
    public boolean canCharacterWield(Entity character, WeaponType thisWeaponType) {
      return classesThatCanWieldWoDao.contains(
          Mappers.getComponentFrom(character, FEClassComponent.class).feClass);
    }
  }
  ;

  private final String displayName;

  PrfRank(StandardRank displayName) {
    this(displayName.rankName());
  }

  PrfRank(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public final String rankName() {
    return displayName;
  }
}
