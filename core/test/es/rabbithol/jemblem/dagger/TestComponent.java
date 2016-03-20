package es.rabbithol.jemblem.dagger;

import javax.inject.Singleton;

import dagger.Component;
import es.rabbithol.jemblem.BattleCalculatorTest;

@Singleton
@Component(modules = {TestModule.class, JemblemModule.class})
public interface TestComponent {
  void inject(BattleCalculatorTest target);
}
