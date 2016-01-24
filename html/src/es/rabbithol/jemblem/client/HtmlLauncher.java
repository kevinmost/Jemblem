package es.rabbithol.jemblem.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import es.rabbithol.jemblem.JemblemGame;

public class HtmlLauncher extends GwtApplication {

  @Override
  public GwtApplicationConfiguration getConfig() {
    return new GwtApplicationConfiguration(480, 320);
  }

  @Override
  public ApplicationListener getApplicationListener() {
    return JemblemGame.game;
  }
}