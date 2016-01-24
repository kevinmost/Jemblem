package java.es.rabbithol.jemblem.dagger;

import java.es.rabbithol.jemblem.BattleCalculatorTest;

import javax.inject.Singleton;

import dagger.Component;
import es.rabbithol.jemblem.dagger.JemblemModule;

@Singleton
@Component(modules = {TestModule.class, JemblemModule.class})
public interface TestComponent {
  void inject(BattleCalculatorTest target);
}
