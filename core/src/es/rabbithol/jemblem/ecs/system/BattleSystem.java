package es.rabbithol.jemblem.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import es.rabbithol.jemblem.calculation.BattleCalculator;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.AttackerComponent;
import es.rabbithol.jemblem.ecs.component.DefenderComponent;
import es.rabbithol.jemblem.ecs.component.HealthComponent;
import es.rabbithol.jemblem.util.BattleLogger;

import java.util.PrimitiveIterator;
import java.util.Random;

public class BattleSystem extends EntitySystem {
  private final PrimitiveIterator.OfInt randomNumber = new Random().ints(0, 100).iterator();

  @Override
  public void update(float deltaTime) {
    final Entity attacker = getEngine().getEntitiesFor(Family.one(AttackerComponent.class).get()).first();
    final Entity defender = getEngine().getEntitiesFor(Family.one(DefenderComponent.class).get()).first();

    final BattleCalculator battleCalculator = new BattleCalculator(attacker, defender);

    final Participant attackerParticipant = new Participant(attacker, battleCalculator.getAttackerResult());
    final Participant defenderParticipant = new Participant(defender, battleCalculator.getDefenderResult());

    BattleLogger.logPreview(attacker, battleCalculator.getAttackerResult());
    BattleLogger.logPreview(defender, battleCalculator.getDefenderResult());

    BattleLogger.logHealth(attacker);
    BattleLogger.logHealth(defender);

    //By default, everyone attacks once
    applyAttack(attackerParticipant, defenderParticipant);
    applyAttack(defenderParticipant, attackerParticipant);

    if (attackerParticipant.numAttacks > 1) {
      applyAttack(attackerParticipant, defenderParticipant);
    }

    if (defenderParticipant.numAttacks > 1) {
      applyAttack(defenderParticipant, attackerParticipant);
    }

    BattleLogger.logHealth(attacker);
    BattleLogger.logHealth(defender);

    super.update(deltaTime);
  }

  private void applyAttack(Participant me, Participant them) {
    HealthComponent themHealth = Mappers.getComponentFrom(them.entity, HealthComponent.class);

    assert (themHealth != null);

    String action = "misses";

    int damageTotal = 0;
    //TODO: We want to accurately model the RNG system in Rekka no Ken!
    if (me.accuracy >= randomNumber.nextInt()) {
      action = "hits";

      damageTotal += me.damage;

      if (me.critAccuracy >= randomNumber.nextInt()) {
        action = "crits";

        damageTotal += me.critDamage;
      }
    }


    BattleLogger.logAttack(me.entity, them.entity, action, damageTotal);

    themHealth.currentHP -= damageTotal;
  }

  public static class Participant {
    Entity entity;

    int accuracy;
    int damage;

    int critAccuracy;
    int critDamage;

    int numAttacks;

    public Participant(Entity entity, BattleCalculator.Result result) {
      this.entity = entity;

      this.accuracy = result.accuracy;
      this.damage = result.damage;

      this.critAccuracy = result.critAccuracy;
      this.critDamage = result.critDamage;

      this.numAttacks = result.numAttacks;
    }
  }
}
