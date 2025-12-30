package net.anonhub.dragonCore.itemEngine;

import net.anonhub.dragonCore.tagEngine.Tag;

import java.util.ArrayList;

public class Item {
    private static final ArrayList<Item> items = new ArrayList<>();
    protected final ArrayList<Tag> tags = new ArrayList<>();

    private Item(String name, ArrayList<Tag> tags) {
        this.tags.addAll(tags);
        if (!name.matches("[a-z0-9_.]+")) {
            throw new IllegalArgumentException("Item names can only contain lower case alphanumeric characters and underscores");
        }
    }

}
