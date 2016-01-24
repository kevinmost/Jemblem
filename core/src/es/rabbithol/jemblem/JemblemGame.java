package es.rabbithol.jemblem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import javax.inject.Inject;

import es.rabbithol.jemblem.dagger.DaggerJemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemComponent;
import es.rabbithol.jemblem.dagger.JemblemModule;
import es.rabbithol.jemblem.ecs.AshleyHelper;
import es.rabbithol.jemblem.ecs.component.WeaponComponent;

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
    final WeaponComponent weaponComponent = new WeaponComponent();
    weaponComponent.might = 5;
    weaponComponent.accuracy = 70;
    final Entity entity = ashleyHelper.createEntity(weaponComponent);
    Gdx.app.log("", "This entity is: " + entity);
  }

  @Override
  public void render() {
  }

  public JemblemComponent component() {
    return game.component;
  }
}
