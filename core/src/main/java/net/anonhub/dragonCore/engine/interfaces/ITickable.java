package net.anonhub.dragonCore.engine.interfaces;

import java.util.ArrayList;

public interface ITickable {
    ArrayList<ITickable> tickables = new ArrayList<>();
    void tick();
    default void addToList() {
        tickables.add(this);
    }
}
