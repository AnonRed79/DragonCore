package net.anonhub.dragonCore.entityEngine.entity;

import net.anonhub.commonlib.Direction;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.mapEngine.Coords;
import net.anonhub.dragonCore.tagEngine.TagList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.anonhub.dragonCore.engine.Settings.display;

public class Data implements ITickable, IDisplayable {
    private final Entity entity;
    public final Coords coords = new Coords();
    public Coords prevCoords = new Coords();
    private float[] velocity = new float[]{0,0,0};

    public final TagList tags = new TagList();
    public final ArrayList<Direction> direction = new ArrayList<>();

    public Boolean player = false;
    public Boolean sprint = false;
    public Map<Character, Map<String, Float>> velocitySource = new HashMap<>();

    public Data(Entity entity) {
        this.entity = entity;
        addToList();
        velocitySource.put('x', new HashMap<>());
        velocitySource.put('y', new HashMap<>());
        velocitySource.put('z', new HashMap<>());
    }

    @Override
    public String display() {
        return
            "\n" + display.preset("obj")+"Data"+display.base() +
                "\n" + "    " + display.preset("obj") + "pLayer: " + display.color("yellow") + player +
                "\n" + "    " + display.preset("obj") + "velocity: " + display.color("yellow") + "X: " + velocity[0] + ", Y: " + velocity[1] + ", Z: " + velocity[2] +
                "\n" + "    " + display.preset("obj") + "tags" + display.color("yellow") + tags
            ;
    }

    @Override
    public void tick() {
        velocity[0] = 0;
        velocity[1] = 0;
        velocity[2] = 0;

        for (char character:new char[]{'x','y','z'}) {
            for (String key:velocitySource.get(character).keySet()) {
                float speed;
                speed = velocitySource.get(character).get(key);
                if (key.contains("actions/move")&&sprint) {
                    speed += velocitySource.get(character).get(key);
                }
                switch (character) {
                    case 'x' -> velocity[0] += speed;
                    case 'y' -> velocity[1] += speed;
                    case 'z' -> velocity[2] += speed;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (prevCoords.absolute[i] != coords.absolute[i]) {
                prevCoords.absolute[i] = coords.absolute[i];
            }
        }
    }



    public float[] getVelocity() {
        return velocity;
    }
}
