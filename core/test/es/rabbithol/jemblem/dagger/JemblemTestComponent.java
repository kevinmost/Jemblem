package es.rabbithol.jemblem.dagger;

import javax.inject.Singleton;

import dagger.Component;
import es.rabbithol.jemblem.unit_tests.BattleCalculatorTest;
import es.rabbithol.jemblem.unit_tests.TestWorldTileMovingLogic;

@Singleton
@Component(modules = {JemblemTestModule.class})
public interface JemblemTestComponent extends JemblemComponent {
  void inject(BattleCalculatorTest target);

  void inject(TestWorldTileMovingLogic target);
}
