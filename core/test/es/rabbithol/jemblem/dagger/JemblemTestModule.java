package es.rabbithol.jemblem.dagger;

import dagger.Module;
import es.rabbithol.jemblem.TestJemblemGame;

@Module
public class JemblemTestModule extends JemblemModule {
  public JemblemTestModule(TestJemblemGame game) {
    super(game);
  }
}
