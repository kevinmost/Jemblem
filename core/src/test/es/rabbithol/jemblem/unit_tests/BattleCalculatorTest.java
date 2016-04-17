package es.rabbithol.jemblem.unit_tests;

import com.badlogic.ashley.core.Entity;

import org.junit.Test;

import es.rabbithol.jemblem.calculation.BattleCalculator;
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
import es.rabbithol.jemblem.model.map.World;
import es.rabbithol.jemblem.model.range.DefaultRangeStrategy;
import es.rabbithol.jemblem.model.rank.PrfRank;
import es.rabbithol.jemblem.model.terrain.Terrains;
import es.rabbithol.jemblem.model.weapon.WeaponTemplate;

import static es.rabbithol.jemblem.model.rank.StandardRank.E;

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
                  .range(new DefaultRangeStrategy(1))
                  .weight(3)
                  .weaponXP(2)
                  .cost(-1)
                  .reversesWeaponTriangle(false)
              ).build()
          ).setEquippedIndex(0)
      ).position(new PositionComponent(2, 2))
      .stats(new StatsComponent(FEClasses.LORD_LYN.baseStats()))
      .proficiency(new WeaponProficiencyComponent()
          .proficiency(WeaponType.SWORD, E))
      .build();

  private final Entity eliwood = new CharacterBuilder()
      .name(new NameComponent("Eliwood"))
      .inventory(new InventoryComponent()
          .addItem(WeaponTemplate.IRON_SWORD.generateWeapon())
          .addItem(new WeaponBuilder()
              .name(new NameComponent("Iron Sword"))
              .durability(new DurabilityComponent(30))
              .weaponStats(new WeaponStatsComponent()
                  .type(WeaponType.SWORD)
                  .rank(E)
                  .might(5)
                  .accuracy(90)
                  .crit(0)
                  .range(new DefaultRangeStrategy(1))
                  .weight(5)
                  .weaponXP(1)
                  .cost(460)
              ).build()
          ).setEquippedIndex(1)
      ).position(new PositionComponent(2, 3))
      .stats(new StatsComponent(FEClasses.SWORDMASTER_M.baseStats()))
      .proficiency(new WeaponProficiencyComponent()
          .proficiency(WeaponType.SWORD, E))
      .build();

  @Test
  public void foo() {
    game.world = new World();
    game.world.tiles[2][2].terrain = Terrains.PLAIN;
    game.world.tiles[2][3].terrain = Terrains.PLAIN;
    final BattleCalculator calculator = new BattleCalculator(lyn, eliwood);
    System.out.println(calculator.getAttackerResult());
    System.out.println("------");
    System.out.println("------");
    System.out.println("------");
    System.out.println(calculator.getDefenderResult());
  }
}