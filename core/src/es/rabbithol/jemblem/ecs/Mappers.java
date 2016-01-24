package es.rabbithol.jemblem.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import java.util.HashMap;
import java.util.Map;


public class Mappers {

  private static final Map<Class<?>, ComponentMapper<?>> mappers = new HashMap<>();

  public static <T extends Component> T getComponentFrom(Entity entity, Class<T> component) {
    return getMapper(component).get(entity);
  }

  @SuppressWarnings("unchecked")
  private static <T extends Component> ComponentMapper<T> getMapper(Class<T> clazz) {
    if (mappers.containsKey(clazz)) {
      return (ComponentMapper<T>)mappers.get(clazz);
    }
    final ComponentMapper<T> componentMapper = ComponentMapper.getFor(clazz);
    mappers.put(clazz, componentMapper);
    return componentMapper;
  }

}
