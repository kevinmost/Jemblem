package es.rabbithol.jemblem;

import es.rabbithol.jemblem.dagger.DaggerJemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemModule;

public class TestJemblemGame extends JemblemGame {

  @Override
  protected JemblemComponent createComponent() {
    return DaggerJemblemComponent.builder()
        .jemblemModule(new JemblemModule(this))
        .build();
  }
}
