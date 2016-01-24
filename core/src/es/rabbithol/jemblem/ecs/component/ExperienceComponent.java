package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;

public class ExperienceComponent implements Component {
  public int level = 1;

  public int currentLevelXP = 0;

  public ExperienceComponent level(int level) {
    this.level = level;
    return this;
  }

  public ExperienceComponent currentLevelXP(int currentLevelXP) {
    this.currentLevelXP = currentLevelXP;
    return this;
  }
}
