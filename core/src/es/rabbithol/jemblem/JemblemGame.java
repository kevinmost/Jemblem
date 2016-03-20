package es.rabbithol.jemblem;

import com.badlogic.gdx.ApplicationAdapter;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import es.rabbithol.jemblem.dagger.DaggerJemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemModule;
import es.rabbithol.jemblem.ecs.AshleyHelper;

public class JemblemGame extends ApplicationAdapter {

  private static JemblemGame game;

  private static void setInstance(@NotNull JemblemGame game) {
    if (JemblemGame.game != null) {
      throw new IllegalStateException("JemblemGame has already been set!");
    }
    JemblemGame.game = game;
  }

  @NotNull
  public static JemblemGame get() {
    if (game == null) {
      throw new IllegalStateException("Can't get JemblemGame without setting it first");
    }
    return game;
  }

  @Inject
  AshleyHelper ashleyHelper;

  private final JemblemComponent component = createComponent();

  protected JemblemGame() {
    setInstance(this);
  }

  @Override
  public void create() {
    component().inject(this);
  }

  protected JemblemComponent createComponent() {
    return DaggerJemblemComponent.builder()
        .jemblemModule(new JemblemModule(this))
        .build();
  }

  public JemblemComponent component() {
    return component;
  }
}
