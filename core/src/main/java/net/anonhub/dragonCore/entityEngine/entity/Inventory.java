package net.anonhub.dragonCore.entityEngine.entity;

import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.entityEngine.entity.inventory.InventorySlot;
import net.anonhub.dragonCore.engine.interfaces.ITickable;

import java.util.ArrayList;

// TODO need to make this work
public class Inventory implements ITickable, IDisplayable {
    private Entity entity;
    public double weightLimit;
    public int spaceLimit;

    ArrayList<InventorySlot> inventorySlots = new ArrayList<>();

    public Inventory(Entity entity, double weightLimit, int spaceLimit) {
        this.entity = entity;
        this.weightLimit = weightLimit;
        this.spaceLimit = spaceLimit;
        if (spaceLimit > 0) {
            for (int x = 0; x < spaceLimit; x++) {
                inventorySlots.add(new InventorySlot((short) x));
            }
        }
        addToList();
    }

    @Override
    public void tick() {}
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String display() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < inventorySlots.toArray().length; x++){
            stringBuilder.append(inventorySlots.get(x));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
