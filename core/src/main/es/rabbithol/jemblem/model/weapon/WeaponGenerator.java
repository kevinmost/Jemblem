package es.rabbithol.jemblem.model.weapon;

import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;

public interface WeaponGenerator {
  @NotNull
  Entity generateWeapon();
}
