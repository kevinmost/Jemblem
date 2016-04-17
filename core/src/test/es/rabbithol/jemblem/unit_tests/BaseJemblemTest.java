package es.rabbithol.jemblem.unit_tests;

import org.junit.Before;

import es.rabbithol.jemblem.JemblemGame;

public abstract class BaseJemblemTest {
  protected JemblemGame game;

  @Before
  public void instantiateGameObject() {
    game = new JemblemGame();
  }
}
