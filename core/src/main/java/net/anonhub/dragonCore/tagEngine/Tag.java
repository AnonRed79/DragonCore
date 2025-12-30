package net.anonhub.dragonCore.tagEngine;

import net.anonhub.dragonCore.tagEngine.exceptions.TagAlreadyExistsException;
import net.anonhub.dragonCore.tagEngine.exceptions.TagAlreadyExistsException;

import java.util.ArrayList;

public class Tag {
    private static final ArrayList<Tag> tagList = new ArrayList<>();
    public static final Tag BASE = new Tag();

    private final String tag;
    private Tag parent;

    public Tag(String tag, Tag parent) {
        String tagString;
        for (Tag compTag:tagList) {
            tagString = compTag.toString();
            if (tagString.equals(tag)) {
                throw new TagAlreadyExistsException("Tag \""+tag+"\" already exists");
            }
        }
        this.tag = tag;
        this.parent = parent;
        tagList.add(this);
    }
    private Tag() {
        tag = "base";
    }

    public Tag getParent() {
        return parent;
    }

    public String getTag() {
        return tag;
    }

    public boolean isWithin(Tag tag) {
        Tag current = this;
        while (current.parent != null ) {
            if (current.equals(tag)) {
                return true;
            }
            current = current.parent;
        }
        return false;
    }

    @Override
    public String toString() {
        Tag current = this;
        StringBuilder str = new StringBuilder();
        while (current.parent != null) {
            str.insert(0, current.tag).insert(0, "/");
            current = current.parent;
        }

        return str.substring(1);
    }
}
