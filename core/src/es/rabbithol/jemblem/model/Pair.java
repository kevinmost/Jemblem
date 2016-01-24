package es.rabbithol.jemblem.model;

public class Pair<K, V> {

  public final K first;
  public final V second;

  public static <K, V> Pair<K, V> of(K a, V b) {
    return new Pair<>(a, b);
  }

  private Pair(K a, V b) {
    this.first = a;
    this.second = b;
  }
}
