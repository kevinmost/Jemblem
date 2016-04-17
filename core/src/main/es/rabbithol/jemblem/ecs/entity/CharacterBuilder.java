package es.rabbithol.jemblem.ecs.entity;

import es.rabbithol.jemblem.ecs.component.ExperienceComponent;
import es.rabbithol.jemblem.ecs.component.HealthComponent;
import es.rabbithol.jemblem.ecs.component.InventoryComponent;
import es.rabbithol.jemblem.ecs.component.NameComponent;
import es.rabbithol.jemblem.ecs.component.PositionComponent;
import es.rabbithol.jemblem.ecs.component.StatsComponent;
import es.rabbithol.jemblem.ecs.component.WeaponProficiencyComponent;

public class CharacterBuilder extends EntityBuilder {
  public CharacterBuilder experience(ExperienceComponent experience) {
    addComponent(experience);
    return this;
  }

  public CharacterBuilder health(HealthComponent health) {
    addComponent(health);
    return this;
  }

  public CharacterBuilder inventory(InventoryComponent inventory) {
    addComponent(inventory);
    return this;
  }

  public CharacterBuilder position(PositionComponent position) {
    addComponent(position);
    return this;
  }

  public CharacterBuilder stats(StatsComponent stats) {
    addComponent(stats);
    return this;
  }

  public CharacterBuilder proficiency(WeaponProficiencyComponent proficiency) {
    addComponent(proficiency);
    return this;
  }

  public CharacterBuilder name(NameComponent name) {
    addComponent(name);
    return this;
  }
}