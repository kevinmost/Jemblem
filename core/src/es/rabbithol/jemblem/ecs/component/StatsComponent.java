package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.model.stats.Stats;
import es.rabbithol.jemblem.model.stats.StatsAdapter;

public class StatsComponent implements Component {
  public static final StatsComponent NULL_STATS = new StatsComponent(Stats.get());

  @NotNull
  public Stats stats;

  public StatsComponent(@NotNull Stats stats) {
    this.stats = stats;
  }
}
