package es.rabbithol.jemblem.util;

import org.jetbrains.annotations.NotNull;

public class ArrayUtil {
  /**
   * @return The item at this index, wrapping around if the index is too high or too low.
   */
  public static <T> T getItemAtIndexWrapped(@NotNull T[] values, int index) {
    return values[index % values.length];
  }
}
