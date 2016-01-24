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
  Engine provideEngine() {
    return new Engine();
  }

  @Provides
  @Singleton
  JemblemGame provideGame() {
    return game;
  }
}
