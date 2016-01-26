package java.es.rabbithol.jemblem;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import com.badlogic.gdx.Gdx;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.system.BattleSystem;
import org.junit.Before;
import org.junit.Test;

import es.rabbithol.jemblem.calculation.BattleCalculator;
import es.rabbithol.jemblem.ecs.component.*;
import es.rabbithol.jemblem.ecs.entity.CharacterBuilder;
import es.rabbithol.jemblem.ecs.entity.WeaponBuilder;
import es.rabbithol.jemblem.model.*;
import es.rabbithol.jemblem.model.fe_class.FEClasses;
import es.rabbithol.jemblem.model.rank.PrfRank;
import es.rabbithol.jemblem.model.rank.StandardRank;
import es.rabbithol.jemblem.model.stats.Stats;
import es.rabbithol.jemblem.model.stats.StatsAdapter;

public class BattleCalculatorTest {
  private final Engine engine = new Engine();

  private final Entity lyn = new CharacterBuilder()
      .name(new NameComponent("Lyn"))
      .feClassComponent(new FEClassComponent(FEClasses.LORD_LYN))
      .inventory(new InventoryComponent()
          .addItem(new WeaponBuilder()
              .name(new NameComponent("Mani Katti"))
              .durability(new DurabilityComponent(45))
              .weaponStats(new WeaponStatsComponent()
                  .type(WeaponType.SWORD)
                  .rank(PrfRank.MANI_KATTI)
                  .might(8)
                  .accuracy(80)
                  .crit(20)
                  .minRange(1)
                  .maxRange(1)
                  .weight(3)
                  .weaponXP(2)
                  .cost(-1)
                  .reversesWeaponTriangle(false))
              .build()
          )
          .equippedIndex(0)
      )
      .position(new PositionComponent(2, 2))
      .stats(new StatsComponent(FEClasses.LORD_LYN.baseStats()))
      .proficiency(new WeaponProficiencyComponent()
          .proficiency(WeaponType.SWORD, StandardRank.E))
      .build();
  private final Entity eliwood = new CharacterBuilder()
      .name(new NameComponent("Eliwood"))
      .feClassComponent(new FEClassComponent(FEClasses.BERSERKER))
      .inventory(new InventoryComponent()
          .addItem(new WeaponBuilder()
              .name(new NameComponent("Iron Sword"))
              .durability(new DurabilityComponent(46))
              .weaponStats(new WeaponStatsComponent()
                  .type(WeaponType.SWORD)
                  .rank(StandardRank.E)
                  .might(5)
                  .accuracy(90)
                  .crit(0)
                  .minRange(1)
                  .maxRange(1)
                  .weight(5)
                  .weaponXP(1)
                  .cost(460)
                  .reversesWeaponTriangle(false)
              )
              .build()
          )
          .equippedIndex(0)
      )
      .position(new PositionComponent(2, 3))
      .stats(new StatsComponent(FEClasses.BERSERKER.baseStats())) //TODO: Proper stat class
      .proficiency(new WeaponProficiencyComponent()
          .proficiency(WeaponType.SWORD, StandardRank.E)
      )
      .build();
  private final Entity hector = new Entity();

  @Test
  public void foo() {
    eliwood.add(new AttackerComponent());
    lyn.add(new DefenderComponent());

    engine.addEntity(lyn);
    engine.addEntity(eliwood);

    engine.addSystem(new BattleSystem());

    engine.update(1);
  }
}