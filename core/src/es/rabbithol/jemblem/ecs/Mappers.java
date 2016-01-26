package es.rabbithol.jemblem.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class Mappers {

  private static final Map<Class<?>, ComponentMapper<?>> mappers = new HashMap<>();

  @Nullable
  public static <T extends Component> T getComponentFrom(@NotNull Entity entity, @NotNull Class<T> component) {
    return getMapper(component).get(entity);
  }

  @NotNull
  public static <T extends Component> T getComponentFrom(@NotNull Entity entity, @NotNull T defaultValue) {
    final Component result = getComponentFrom(entity, defaultValue.getClass());
    if (result == null) {
      return defaultValue;
    }
    //noinspection unchecked
    return (T)result;
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
