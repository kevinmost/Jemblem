package es.rabbithol.jemblem.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import es.rabbithol.jemblem.JemblemGame;

public class DesktopLauncher {
  public static void main(String[] arg) {
    new LwjglApplication(JemblemGame.get(), new LwjglApplicationConfiguration());
  }
}
