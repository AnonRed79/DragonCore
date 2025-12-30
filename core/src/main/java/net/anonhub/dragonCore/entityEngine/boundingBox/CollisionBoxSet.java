package net.anonhub.dragonCore.entityEngine.boundingBox;

import com.badlogic.gdx.graphics.Color;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.entityEngine.Entity;

import static net.anonhub.dragonCore.entityEngine.Entity.entities;


public class CollisionBoxSet extends BoundingBoxSet{
    private int tick = 0;
    public boolean[] check = new boolean[]{false, false, false, false};


    public CollisionBoxSet(Entity entity) {
        super(entity, Color.WHITE, BoundingBoxType.COLLISION);
    }

    public void collision(){
        for (String key:boxes.keySet()) {
            for (Entity entity: Entity.entities) {for (String boxKey:entity.collisionBox.boxes.keySet()) {
                if (entity.equals(this.entity)) {
                    continue;
                }
                BoundingBox thisBox = boxes.get(key);
                BoundingBox otherBox = entity.collisionBox.boxes.get(boxKey);
                check = new boolean[]{false, false, false, false, false, false};

                if (
                    thisBox.getCenter()[0]>otherBox.getCenter()[0]
                ) {
                    if (
                        thisBox.getLeft() < otherBox.getRight()
                    ) {
                        thisBox.setLeft(otherBox.getRight());
                    }
                }
                if (
                    thisBox.getCenter()[0]<otherBox.getCenter()[0]
                )
                    {
                    if (
                        thisBox.getRight() > otherBox.getLeft()
                    ) {
                        thisBox.setRight(otherBox.getLeft());
                    }
                }

            }}
        }
    }
}
