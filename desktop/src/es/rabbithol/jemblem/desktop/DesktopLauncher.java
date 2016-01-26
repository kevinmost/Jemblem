package es.rabbithol.jemblem.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import es.rabbithol.jemblem.JemblemGame;
import es.rabbithol.jemblem.dagger.DaggerJemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemModule;

public class DesktopLauncher {
  public static void main(String[] arg) {
    new LwjglApplication(JemblemGame.game, new LwjglApplicationConfiguration());
  }
}
