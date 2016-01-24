package es.rabbithol.jemblem;

import com.badlogic.gdx.ApplicationAdapter;

import javax.inject.Inject;

import es.rabbithol.jemblem.dagger.DaggerJemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemModule;
import es.rabbithol.jemblem.ecs.AshleyHelper;

public class JemblemGame extends ApplicationAdapter {

  public static JemblemGame game = new JemblemGame();

  @Inject
  AshleyHelper ashleyHelper;

  private JemblemComponent component;

  private JemblemGame() {
  }

  @Override
  public void create() {
    component = DaggerJemblemComponent.builder()
        .jemblemModule(new JemblemModule(this))
        .build();
    component().inject(this);
  }

  public JemblemComponent component() {
    return component;
  }
}
