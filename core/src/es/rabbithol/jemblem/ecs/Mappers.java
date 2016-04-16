package es.rabbithol.jemblem.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class Mappers {

  private static final Map<Class<?>, ComponentMapper<?>> mappers = new HashMap<>();

  @NotNull
  public static <T extends Component> Optional<T> getComponentFrom(@NotNull Entity entity, @NotNull Class<T> component) {
    return Optional.ofNullable(getMapper(component).get(entity));
  }

  @NotNull
  public static <T extends Component> T getComponentFrom(@NotNull Entity entity, @NotNull T defaultValue) {
    final Optional<T> result = getComponentFrom(entity, (Class<T>)defaultValue.getClass());
    return result.orElse(defaultValue);
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
