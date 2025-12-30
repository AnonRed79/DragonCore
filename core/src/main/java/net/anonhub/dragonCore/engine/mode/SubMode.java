package net.anonhub.dragonCore.engine.mode;

import net.anonhub.dragonCore.engine.interfaces.ITickable;

public class SubMode implements ITickable {
    public boolean boxes = false;
    public boolean grid = false;
    public boolean menu = true;
    public boolean typing = false;
    public boolean terminal = true;
    public boolean dataDisplay = false;

    public SubMode() {
        addToList();
    }

    @Override
    public void tick() {
        if (!menu && terminal) {
            terminal = false;
        }
        if (!menu && typing) {
            typing = false;
        }
        if (!typing && terminal) {
            typing = true;
        }
        if (terminal && dataDisplay) {
            dataDisplay = false;
        }
    }
}
