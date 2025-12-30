package net.anonhub.dragonCore.entityEngine.entity;

import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.Entity;

public class Lore implements ITickable {
    private final Entity entity;
    public String name = "test name in class file";

    public Lore(Entity entity) {
        this.entity = entity;
        addToList();
    }

    @Override
    public void tick() {

    }
}
