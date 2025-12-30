package net.anonhub.dragonCore.entityEngine.boundingBox;

import com.badlogic.gdx.graphics.Color;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.mapEngine.Coords;

import java.util.HashMap;
import java.util.Map;

public class BoundingBoxSet implements ITickable {
    protected final Coords coords = new Coords();
    protected final Entity entity;
    protected final BoundingBoxType type;

    protected Color color;

    public Map<String, BoundingBox> boxes = new HashMap<>();

    public BoundingBoxSet(Entity entity, Color color, BoundingBoxType type) {
        this.entity = entity;
        this.color = color;
        this.type = type;
        addToList();

    }

    public void draw() {
        for (String key:boxes.keySet()) {
            boxes.get(key).draw();
        }
    }

    public void defineBox(String boxName, float startX, float startY, float endX, float endY) {
        defineBox(boxName, new float[]{startX, startY, endX, endY});
    }
    public void defineBox(String boxName, float[] start, float[] end) {
        if (start.length!=2) {
            throw new IllegalArgumentException("float[] start, should have length of two but instead has a length of "+start.length);
        }
        if (end.length!=2) {
            throw new IllegalArgumentException("float[] end, should have length of two but instead has a length of "+start.length);
        }

        defineBox(boxName, new float[]{start[0], start[1], end[0], end[1]});
    }
    public void defineBox(String boxName, float[] box) {
        if (box.length!=4) {
            throw new IllegalArgumentException("float[] end, should have length of four but instead has a length of "+box.length);
        }
        boxes.put(boxName, new BoundingBox(entity, color, type, box));
    }



    @Override
    public void tick() {
        coords.absolute[0] = entity.data.coords.absolute[0];
        coords.absolute[1] = entity.data.coords.absolute[1];
    }
}
