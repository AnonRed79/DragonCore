package net.anonhub.dragonCore.entityEngine.entity.inventory;

import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.itemEngine.Item;
import net.anonhub.dragonCore.tagEngine.Tag;
import net.anonhub.dragonCore.tagEngine.TagList;

import java.util.ArrayList;

// TODO need to make this work
public class InventorySlot implements ITickable, IDisplayable {
    public final short slotNum;
    private Item item;
    public TagList tagList = new TagList();

    public InventorySlot(short slotNum) {
        this.slotNum = slotNum;

    }

    public InventorySlot(short slotNum, TagList tagList) {
        this.slotNum = slotNum;
        this.tagList = tagList;
    }

//    TODO add the display method
    @Override
    public String display() {
        return "";
    }

//    TODO add things that need to be ticked
    @Override
    public void tick() {}
}
