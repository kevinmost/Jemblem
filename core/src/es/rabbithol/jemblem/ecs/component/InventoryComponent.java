package es.rabbithol.jemblem.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.List;

public class InventoryComponent implements Component {
  public List<Entity> inventory;
  public int equippedIndex;

  public InventoryComponent(List<Entity> inventory, int equippedIndex) {
    this.inventory = inventory;
    this.equippedIndex = equippedIndex;
  }

  public Entity getEquippedInventoryItem() {
    return inventory.get(equippedIndex);
  }

  public static class Builder {
    private List<Entity> inventory;
    private int equippedIndex;

    public Builder inventory(List<Entity> inventory) {
      this.inventory = inventory;
      return this;
    }

    public Builder equippedIndex(int equippedIndex) {
      this.equippedIndex = equippedIndex;
      return this;
    }

    public InventoryComponent build() {
      return new InventoryComponent(inventory, equippedIndex);
    }
  }
}
