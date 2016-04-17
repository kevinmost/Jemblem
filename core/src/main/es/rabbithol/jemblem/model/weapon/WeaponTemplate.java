package es.rabbithol.jemblem.model.weapon;

import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.ecs.component.DurabilityComponent;
import es.rabbithol.jemblem.ecs.component.NameComponent;
import es.rabbithol.jemblem.ecs.component.WeaponStatsComponent;
import es.rabbithol.jemblem.ecs.entity.WeaponBuilder;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.range.DefaultRangeStrategy;
import es.rabbithol.jemblem.model.rank.StandardRank;

public enum WeaponTemplate implements WeaponGenerator {
  IRON_SWORD {
    @Override
    public @NotNull Entity generateWeapon() {
      return new WeaponBuilder()
          .name(new NameComponent("Iron Sword"))
          .durability(new DurabilityComponent(46))
          .weaponStats(new WeaponStatsComponent()
              .type(WeaponType.SWORD)
              .rank(StandardRank.E)
              .weight(5)
              .might(5)
              .accuracy(90)
              .crit(0)
              .range(new DefaultRangeStrategy(1))
              .weaponXP(1)
              .cost(460)
              .reversesWeaponTriangle(false)
          ).build();
    }
  },

  ;
}
