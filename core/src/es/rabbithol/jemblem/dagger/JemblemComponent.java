package es.rabbithol.jemblem.dagger;

import com.badlogic.ashley.core.Engine;

import javax.inject.Singleton;

import dagger.Component;
import es.rabbithol.jemblem.JemblemGame;

@Singleton
@Component(modules = JemblemModule.class)
public interface JemblemComponent {
  Engine provideEngine();
  JemblemGame provideGame();

  void inject(JemblemGame target);
}
