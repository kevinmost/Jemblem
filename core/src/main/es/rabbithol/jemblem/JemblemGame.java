package es.rabbithol.jemblem;

import com.badlogic.gdx.ApplicationAdapter;

import org.jetbrains.annotations.NotNull;

import es.rabbithol.jemblem.model.map.World;

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

  public World world;

  public JemblemGame() {
    setInstance(this);
  }


}
