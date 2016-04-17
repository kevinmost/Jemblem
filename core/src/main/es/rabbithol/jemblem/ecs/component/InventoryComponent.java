package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InventoryComponent implements Component {
  public static final InventoryComponent NULL_INVENTORY = new InventoryComponent();

  private List<Entity> inventory = new ArrayList<>();
  private int equippedIndex = -1;

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

  public InventoryComponent setEquippedIndex(int equippedIndex) {
    this.equippedIndex = equippedIndex;
    return this;
  }

  public Optional<Entity> getEquippedInventoryItem() {
    if (0 <= equippedIndex && equippedIndex <= inventory.size()) {
      return Optional.of(inventory.get(equippedIndex));
    }
    return Optional.empty();
  }
}
