package net.anonhub.untitledPlatformer;

import net.anonhub.dragonCore.engine.Renders;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.entityEngine.Entity;


public class Mod {
    public Mod(Renders renders) {
        Entity entity;
        entity = new Entity("./assets/cat.png", 40, 70, renders);
        entity.data.player = true;
        entity.lore.name = "player";
        entity.data.tags.add(Tags.PLAYER);
//        entity.interactBox.defineBox("body", new float[]{-20,-30,20,30});
        entity.collisionBox.defineBox("body", new float[]{-20,-35,20,35});
        entity.data.coords.absolute = new float[]{50,50};
//        entity.collisionBox.defineBox("armL", new float[]{-40,-10,-20,35});
//        entity.hurtBox.defineBox("body", new float[]{-20,-30,20,30});



//        create tiles
//        for (int y = 0; y < 1; y++) {
//            for (int x = 0; x <1; x++) {
//                byte span = 50;
                entity = new Entity("./assets/bricks.png", 20,20, renders);
                entity.data.tags.add(Tags.WALL);
                entity.lore.name = "tile";
                entity.collisionBox.defineBox("body", new float[]{-10,-10,10,10});
//                entity.data.coords.absolute[0] = x*60-120;
//                entity.data.coords.absolute[1] = y*60;
                entity.data.coords.absolute[0] = 0;
                entity.data.coords.absolute[1] = 0;
//            }
//        }
    }

    public void display(IDisplayable displayable) {
        System.out.println(displayable.display());
    }
}
