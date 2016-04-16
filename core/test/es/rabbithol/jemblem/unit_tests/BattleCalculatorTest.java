package es.rabbithol.jemblem.unit_tests;

import com.badlogic.ashley.core.Entity;

import es.rabbithol.jemblem.ecs.component.DurabilityComponent;
import es.rabbithol.jemblem.ecs.component.InventoryComponent;
import es.rabbithol.jemblem.ecs.component.NameComponent;
import es.rabbithol.jemblem.ecs.component.PositionComponent;
import es.rabbithol.jemblem.ecs.component.StatsComponent;
import es.rabbithol.jemblem.ecs.component.WeaponProficiencyComponent;
import es.rabbithol.jemblem.ecs.component.WeaponStatsComponent;
import es.rabbithol.jemblem.ecs.entity.CharacterBuilder;
import es.rabbithol.jemblem.ecs.entity.WeaponBuilder;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.fe_class.FEClasses;
import es.rabbithol.jemblem.model.rank.PrfRank;
import es.rabbithol.jemblem.model.rank.StandardRank;

public class BattleCalculatorTest extends BaseJemblemTest {

  private final Entity lyn = new CharacterBuilder()
      .name(new NameComponent("Lyn"))
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
      )
      .position(new PositionComponent(2, 2))
      .stats(new StatsComponent(FEClasses.LORD_LYN.baseStats()))
      .proficiency(new WeaponProficiencyComponent()
          .proficiency(WeaponType.SWORD, StandardRank.E))
      .build();
  private final Entity eliwood = new Entity();
  private final Entity hector = new Entity();

//  @Test
//  public void foo() {
//    final BattleCalculator calculator = new BattleCalculator(lyn, eliwood);
//  }
}