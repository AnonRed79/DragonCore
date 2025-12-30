package net.anonhub.dragonCore.tagEngine;

import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.guiEngine.terminalErrors.NoTargetError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.anonhub.dragonCore.entityEngine.Entity.entities;


public class TagList {
    private final Set<Tag> tags = new HashSet<>();

    public TagList() {}
    public TagList(List<Tag> tagList) {
        tags.addAll(tagList);
    }
    public TagList(Set<Tag> tagSet) {
        tags.addAll(tagSet);
    }
    public TagList(Tag tag) {
        tags.add(tag);
    }
    public void add(Tag tag){
        tags.add(tag);
    }
    public void add(List<Tag> tagList){
        tags.addAll(tagList);
    }
    public void add(Set<Tag> tagSet){
        tags.addAll(tagSet);
    }
    public void remove(Tag tag){
        tags.remove(tag);
    }
    public void remove(List<Tag> tagList){
        tagList.forEach(tags::remove);
    }
    public void remove(Set<Tag> tagSet){
        tags.removeAll(tagSet);
    }
    public boolean contains(Tag tag){
        for (Tag tag1:tags) {
            if (tag1.isWithin(tag)) {
                return true;
            }
        }
        return false;
    }
}
