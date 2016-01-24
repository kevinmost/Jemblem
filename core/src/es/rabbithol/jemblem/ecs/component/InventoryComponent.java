package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryComponent implements Component {
  public List<Entity> inventory = new ArrayList<>(5);
  public int equippedIndex = -1;

  public InventoryComponent addItem(Entity item) {
    inventory.add(item);
    return this;
  }

  public InventoryComponent addItems(Entity... items) {
    if (items != null) {
      Collections.addAll(this.inventory, items);
    }
    return this;
  }

  public InventoryComponent removeItem(Entity item) {
    inventory.remove(item);
    return this;
  }

  public InventoryComponent removeItem(int index) {
    inventory.remove(index);
    return this;
  }

  public InventoryComponent setItems(List<Entity> items) {
    this.inventory = items;
    return this;
  }

  public InventoryComponent equippedIndex(int equippedIndex) {
    this.equippedIndex = equippedIndex;
    return this;
  }

  public Entity getEquippedInventoryItem() {
    return inventory.get(equippedIndex);
  }
}
