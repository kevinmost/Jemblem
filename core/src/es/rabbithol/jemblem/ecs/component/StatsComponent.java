package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.model.stats.Stats;

public class StatsComponent implements Component {
  @NotNull
  public Stats stats;

  public StatsComponent(@NotNull  Stats stats) {
    this.stats = stats;
  }
}
