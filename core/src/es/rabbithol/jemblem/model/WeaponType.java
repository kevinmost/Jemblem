package es.rabbithol.jemblem.model;

import java.util.EnumSet;
import java.util.Set;

public enum WeaponType implements Comparable<WeaponType> {
  SWORD {
    @Override
    public WeaponType hasAdvantageOver() {
      return AXE;
    }
  },
  LANCE {
    @Override
    public WeaponType hasAdvantageOver() {
      return SWORD;
    }
  },
  AXE {
    @Override
    public WeaponType hasAdvantageOver() {
      return LANCE;
    }
  },
  BOW {
    @Override
    public WeaponType hasAdvantageOver() {
      return null;
    }
  },
  MAGIC_ANIMA {
    @Override
    public WeaponType hasAdvantageOver() {
      return MAGIC_LIGHT;
    }
  },
  MAGIC_LIGHT {
    @Override
    public WeaponType hasAdvantageOver() {
      return MAGIC_DARK;
    }
  },
  MAGIC_DARK {
    @Override
    public WeaponType hasAdvantageOver() {
      return MAGIC_ANIMA;
    }
  },
  STAFF {
    @Override
    public WeaponType hasAdvantageOver() {
      return null;
    }
  };

  public static final Set<WeaponType> MAGIC_WEAPON_TYPES = EnumSet.of(MAGIC_ANIMA, MAGIC_LIGHT, MAGIC_DARK);
  public static final Set<WeaponType> PHYSICAL_WEAPON_TYPES = EnumSet.of(SWORD, LANCE, AXE, BOW);

  public abstract WeaponType hasAdvantageOver();
}
