package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.List;

public class InventoryComponent implements Component {
  public List<Entity> inventory;
}
