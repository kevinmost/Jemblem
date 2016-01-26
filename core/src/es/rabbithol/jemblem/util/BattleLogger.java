package es.rabbithol.jemblem.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import es.rabbithol.jemblem.calculation.BattleCalculator;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.HealthComponent;
import es.rabbithol.jemblem.ecs.component.NameComponent;

public class BattleLogger {
  public static void logHealth(Entity e) {
    String name = Mappers.getComponentFrom(e, NameComponent.class).name;
    int currentHP = Mappers.getComponentFrom(e, HealthComponent.class).currentHP;

    Gdx.app.log("Health Check", name + " has " + currentHP + "HP");
  }

  public static void logAttack(Entity attacker, Entity defender, String action, int damage) {
    String attackerName = Mappers.getComponentFrom(attacker, NameComponent.class).name;
    String defenderName = Mappers.getComponentFrom(defender, NameComponent.class).name;

    String attackString =
        String.format(
            "%s %s %s for %s dmg",
            attackerName,
            action,
            defenderName,
            damage
        );

    Gdx.app.log("Attack Step", attackString);
  }

  public static void logPreview(Entity e, BattleCalculator.Result result) {
    NameComponent nameComponent = Mappers.getComponentFrom(e, NameComponent.class);

    String previewString =
        String.format(
            "%s\n" +
                "\tAS:%s\n" +
                "\tNA:%s\n" +
                "\tHR:%s\n" +
                "\tEV:%s\n" +
                "\tAC:%s\n" +
                "\tAP:%s\n" +
                "\tDP:%s\n" +
                "\tDG:%s\n" +
                "\tCD:%s\n" +
                "\tCR:%s\n" +
                "\tCE:%s\n" +
                "\tCA:%s",
            nameComponent.name,
            result.attackSpeed,
            result.numAttacks,
            result.hitRate,
            result.evade,
            result.accuracy,
            result.attackPower,
            result.defensePower,
            result.damage,
            result.critDamage,
            result.critRate,
            result.critEvade,
            result.critAccuracy
        );

    Gdx.app.log("Battle Preview", previewString);
  }
}
