package net.anonhub.dragonCore.entityEngine.interfaces;

import net.anonhub.dragonCore.entityEngine.entity.Status;

public interface IEffect {
    void apply(Status status, int strength);
}
