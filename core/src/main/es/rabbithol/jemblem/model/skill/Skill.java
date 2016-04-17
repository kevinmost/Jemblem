package es.rabbithol.jemblem.model.skill;

public interface Skill {

  Trigger triggers();

  enum Trigger {
    /**
     * Adds an option to your menu
     */
    MANUAL,

    /**
     * Changes battle calculation
     */
    CALCULATION,

    /**
     * Does something on hit
     */
    ON_HIT,
  }
}
