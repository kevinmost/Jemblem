package es.rabbithol.jemblem.dagger;

import dagger.Component;
import es.rabbithol.jemblem.JemblemGame;
import es.rabbithol.jemblem.calculation.BattleCalculator;

import javax.inject.Singleton;

@Singleton
@Component(modules = JemblemModule.class)
public interface JemblemComponent {
  void inject(JemblemGame target);
  void inject(BattleCalculator target);
}
