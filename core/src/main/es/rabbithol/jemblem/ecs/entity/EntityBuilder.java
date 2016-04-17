package es.rabbithol.jemblem.ecs.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.HashMap;
import java.util.Map;

abstract class EntityBuilder {
  private final Map<Class<? extends Component>, Component> components = new HashMap<>();

  protected final void addComponent(Component component) {
    components.put(component.getClass(), component);
  }

  public final Entity build() {
    final Entity entity = new Entity();
    components.forEach((key, value) -> entity.add(value));
    return entity;
  }
}
