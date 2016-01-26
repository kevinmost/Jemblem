package es.rabbithol.jemblem.ecs.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import es.rabbithol.jemblem.ecs.component.*;

public class CharacterBuilder {
  private FEClassComponent feClassComponent;
  private ExperienceComponent experience;
  private HealthComponent health;
  private InventoryComponent inventory;
  private PositionComponent position;
  private StatsComponent stats;
  private WeaponProficiencyComponent proficiency;
  private NameComponent name;

  public CharacterBuilder feClassComponent(FEClassComponent feClassComponent) {
    this.feClassComponent = feClassComponent;
    return this;
  }

  public CharacterBuilder experience(ExperienceComponent experience) {
    this.experience = experience;
    return this;
  }

  public CharacterBuilder health(HealthComponent health) {
    this.health = health;
    return this;
  }

  public CharacterBuilder inventory(InventoryComponent inventory) {
    this.inventory = inventory;
    return this;
  }

  public CharacterBuilder position(PositionComponent position) {
    this.position = position;
    return this;
  }

  public CharacterBuilder stats(StatsComponent stats) {
    this.stats = stats;
    return this;
  }

  public CharacterBuilder proficiency(WeaponProficiencyComponent proficiency) {
    this.proficiency = proficiency;
    return this;
  }

  public CharacterBuilder name(NameComponent name) {
    this.name = name;
    return this;
  }

  public Entity build() {
    final Entity entity = new Entity();
    for (Component component : new Component[]{feClassComponent, experience, health, inventory, position, stats, proficiency, name}) {
      if (component != null) {
        entity.add(component);
      }
    }
    return entity;
  }
}