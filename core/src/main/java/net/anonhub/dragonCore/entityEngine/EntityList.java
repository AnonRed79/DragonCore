package net.anonhub.dragonCore.entityEngine;

import net.anonhub.dragonCore.entityEngine.interfaces.ITagUse;
import net.anonhub.dragonCore.tagEngine.Tag;
import net.anonhub.dragonCore.tagEngine.Tags;

import java.util.ArrayList;

import static net.anonhub.dragonCore.entityEngine.Entity.entities;

public class EntityList extends ArrayList<Entity>{
    public boolean useOnTag(Tag tag, ITagUse lambda) {
        for (Entity entity:this) {
            if (entity.data.tags.contains(tag)) {
                lambda.run(entity);
            }
        }
        return true;
    }
    public Entity getPlayer() {

        Entity player = null;
        for (Entity entity: Entity.entities) {
            if (entity.data.tags.contains(Tags.PLAYER)) {
                player = entity;
            }
        }
        if (player == null) {
            throw new NullPointerException("Couldn't find player in entitylist");
        }
        return player;
    }
}
