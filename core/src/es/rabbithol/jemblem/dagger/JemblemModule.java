package es.rabbithol.jemblem.dagger;

import com.badlogic.ashley.core.Engine;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.rabbithol.jemblem.JemblemGame;

@Module
public class JemblemModule {

  private final JemblemGame game;

  public JemblemModule(JemblemGame game) {
    this.game = game;
  }

  @Provides
  @Singleton
  public Engine engine() {
    return new Engine();
  }

  @Provides
  @Singleton
  public JemblemGame game() {
    return game;
  }
}
