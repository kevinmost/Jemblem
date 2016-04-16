package es.rabbithol.jemblem.unit_tests;

import org.junit.Before;

import es.rabbithol.jemblem.TestJemblemGame;

public abstract class BaseJemblemTest {
  private TestJemblemGame testJemblemGame;

  @Before
  public void instantiateGameObject() {
    testJemblemGame = new TestJemblemGame();
  }
}
