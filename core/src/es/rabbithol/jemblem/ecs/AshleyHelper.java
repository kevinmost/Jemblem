package es.rabbithol.jemblem.ecs;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AshleyHelper {

  @Inject
  Engine engine;

  @Inject
  AshleyHelper() {}

  public Entity createEntity(Component... components) {
    final Entity entity = new Entity();
    for (Component component : components) {
      entity.add(component);
    }
    engine.addEntity(entity);
    return entity;
  }
}
