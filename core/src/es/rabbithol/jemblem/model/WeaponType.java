package es.rabbithol.jemblem.model;

public enum WeaponType {
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

  public abstract WeaponType hasAdvantageOver();
}
