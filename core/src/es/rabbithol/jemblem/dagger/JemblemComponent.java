package es.rabbithol.jemblem.dagger;

import javax.inject.Singleton;

import dagger.Component;
import es.rabbithol.jemblem.JemblemGame;
import es.rabbithol.jemblem.calculation.BattleCalculator;
import es.rabbithol.jemblem.calculation.BattleCalculatorInfo;

@Singleton
@Component(modules = JemblemModule.class)
public interface JemblemComponent {
  void inject(JemblemGame target);
  void inject(BattleCalculatorInfo target);
}
