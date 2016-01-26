package es.rabbithol.jemblem.calculation;

import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import es.rabbithol.jemblem.JemblemGame;
import es.rabbithol.jemblem.ecs.Mappers;
import es.rabbithol.jemblem.ecs.component.*;
import es.rabbithol.jemblem.model.WeaponType;
import es.rabbithol.jemblem.model.fe_class.FEClass;
import es.rabbithol.jemblem.model.map.World;
import es.rabbithol.jemblem.model.rank.Rank;
import es.rabbithol.jemblem.model.rank.StandardRank;
import es.rabbithol.jemblem.model.stats.Stats;
import es.rabbithol.jemblem.util.NullUtil;

public class BattleCalculatorInfo {

  @NotNull
  public final WeaponStatsComponent equippedWeapon;

  @NotNull
  public final Stats stats;

  @NotNull
  public final FEClass feClass;

  @NotNull
  public final WeaponProficiencyComponent weaponProficiency;

  @NotNull
  public final PositionComponent position;

  @NotNull
  public final BattleCalculatorResult result;

  @Inject
  World world;

  public BattleCalculatorInfo(Entity character) {
    JemblemGame.game.component().inject(this);

    final Entity equippedWeaponEntity = NullUtil
        .preventNull(Mappers.getComponentFrom(character, InventoryComponent.class),
            InventoryComponent.NULL_INVENTORY)
        .getEquippedInventoryItem();
    this.equippedWeapon = Mappers.getComponentFrom(equippedWeaponEntity, WeaponStatsComponent.NULL_WEAPON_STATS_COMPONENT);

    this.stats = Mappers.getComponentFrom(character, StatsComponent.NULL_STATS).stats;

    this.feClass = Mappers.getComponentFrom(character, FEClassComponent.NULL_CLASS).feClass;

    this.weaponProficiency = Mappers.getComponentFrom(character, WeaponProficiencyComponent.NULL_PROFICIENCY);

    this.position = Mappers.getComponentFrom(character, PositionComponent.NULL_POSITION);

    this.result = new BattleCalculatorResult();
  }

  public int getWeaponTriangleDamageBonus(BattleCalculatorInfo them) {
    final WeaponStatsComponent myWeapon = this.equippedWeapon;
    final WeaponStatsComponent theirWeapon = them.equippedWeapon;
    // Two *reavers cancel each other out
    final int multiplier = myWeapon.reversesWeaponTriangle ^ theirWeapon.reversesWeaponTriangle ? 2 : 1;

    final int value;
    if (myWeapon.type.hasAdvantageOver() == theirWeapon.type) {
      value = 15;
    } else if (theirWeapon.type.hasAdvantageOver() == myWeapon.type) {
      value = -15;
    } else {
      value = 0;
    }
    return multiplier * value;
  }

  public boolean shouldCharacterGetSRankBonus() {
    final WeaponType equippedWeaponType = this.equippedWeapon.type;
    final Rank rankInEquippedWeaponType =
        this.weaponProficiency.getRankIn(equippedWeaponType);
    return rankInEquippedWeaponType == StandardRank.S;
  }

  public int getSpacesApart(BattleCalculatorInfo them) {
    return Math.abs(this.position.y - them.position.y)
        + Math.abs(this.position.x - them.position.x);
  }

  public World.Tile getTileCharacterIsOn() {
    return world.tiles[position.x][position.y];
  }
}
