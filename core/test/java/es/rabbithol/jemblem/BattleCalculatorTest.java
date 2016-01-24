package java.es.rabbithol.jemblem;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

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

  private Entity lyn;
  private Entity eliwood = new Entity();
  private Entity hector = new Entity();

  @Before
  public void makeCharacters() {
    lyn = initLyn();
  }

  @Test
  public void foo() {
    final BattleCalculator calculator = new BattleCalculator(lyn, eliwood);
  }

  private Entity initLyn() {
    return new CharacterBuilder()
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
  }
}
