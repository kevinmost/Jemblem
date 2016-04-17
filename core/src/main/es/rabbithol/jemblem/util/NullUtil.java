package es.rabbithol.jemblem.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NullUtil {
  @NotNull
  public static <T> T preventNull(@Nullable T object, @NotNull T defaultValue) {
    return object == null ? defaultValue : object;
  }
}
