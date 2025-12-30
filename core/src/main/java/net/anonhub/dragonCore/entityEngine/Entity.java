package net.anonhub.dragonCore.entityEngine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.anonhub.dragonCore.engine.Renders;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.entityEngine.boundingBox.BoundingBoxSet;
import net.anonhub.dragonCore.entityEngine.boundingBox.BoundingBoxType;
import net.anonhub.dragonCore.entityEngine.boundingBox.CollisionBoxSet;
import net.anonhub.dragonCore.entityEngine.entity.*;
import net.anonhub.dragonCore.engine.interfaces.ITickable;

import java.util.ArrayList;
import java.util.List;

import static net.anonhub.dragonCore.engine.Settings.display;
import static net.anonhub.dragonCore.engine.Settings.ticker;

public class Entity implements ITickable, IDisplayable {
    public static final EntityList entities = new EntityList();

    private int previousTick;
    private Renders renders;

    public final Status status = new Status(this);
    public final Inventory inventory = new Inventory(this, 100.00, 10);
    public final Lore lore = new Lore(this);
    public final Sprite sprite;
    public final Data data = new Data(this);
    public final BoundingBoxSet hurtBox = new BoundingBoxSet(this, Color.ORANGE, BoundingBoxType.HURT);
    public final BoundingBoxSet hitBox = new BoundingBoxSet(this, Color.RED, BoundingBoxType.HIT);
    public final BoundingBoxSet interactBox = new BoundingBoxSet(this, Color.GREEN, BoundingBoxType.INTERACT);
    public final CollisionBoxSet collisionBox = new CollisionBoxSet(this);
    public final ArrayList<BoundingBoxSet> boundingBoxSets = new ArrayList<>(List.of(hurtBox, hitBox, interactBox, collisionBox));



    public Entity(String texturePath, float width, float height, Renders renders) {
        entities.add(this);
        sprite = new Sprite(this, texturePath, width, height, renders);
        this.renders = renders;
        addToList();
    }
    public Entity(String texturePath, Renders renders) {
        entities.add(this);
        sprite = new Sprite(this, texturePath, renders);
        this.renders = renders;
        addToList();
    }

    public void drawBoxes() {
        int currentTick = ticker.getSessionTick();
        if (previousTick < currentTick) {
            previousTick = currentTick;
            BoundingBoxSet temp;
            temp = boundingBoxSets.getFirst();
            boundingBoxSets.removeFirst();
            boundingBoxSets.add(temp);
        }
        for (BoundingBoxSet box: boundingBoxSets) {
            box.draw();
        }
    }


    @Override
    public void tick() {
        data.coords.previous = data.coords.absolute;
        data.coords.absolute[0] += (float) (data.getVelocity()[0]);
        data.coords.absolute[1] += (float) (data.getVelocity()[1]);
        if (data.player) {
            collisionBox.collision();
        }
    }


    @Override
    public String toString() {
        return
            lore.name;
//                display.preset("obj") + "Entity" + display.preset("op") + display.preset("var") + (lore.name!=null? lore.name:display.preset("null")) + display.preset("cp") + display.reset();
    }


    @Override
    public String display() {
        return
                display.preset("obj") + "Entity" +
                "\n" + "  "   + display.preset("obj") + "Status" + display.base() +
                "\n" + "    " + display.color("yellow") + status.experience.getLevelExperience() + display.reset() + display.preset("/") + display.color("yellow") + status.experience.getExperienceToNextLevel() + display.reset() +
                "\n" + "    " + display.color("yellow") + status.experience.getDisplayLevel() + display.reset() +
                " "           + display.color("pink")   + status.experience.getDisplayTier() + display.reset() +
                " "           + display.color("mint")   + status.experience.getDisplayAscendance() + display.reset() +
                "\n" + "    " + display.color("red")    + (int)status.vitality.current+"/"+(int)status.vitality.getMax() + display.reset() +
                " "           + display.color("green")  + (int)status.stamina.current+"/"+(int)status.stamina.getMax() + display.reset() +
                " "           + display.color("blue")   + (int)status.mana.current+"/"+(int)status.mana.getMax() + display.reset() +
                "\n" + "    " + display.color("banana") + status.constitution.getCurrent() + (status.constitution.getBase() == status.constitution.getCurrent()?"":(display.preset("op") + (status.constitution.getCurrent() - status.constitution.getBase() > 0?display.color("azure")+"+":display.color("red")) + (status.constitution.getCurrent() - status.constitution.getBase()) + display.preset("cp"))) +
                " "           + display.color("banana") + status.strength.getCurrent() + (status.strength.getBase() == status.strength.getCurrent()?"":(display.preset("op") + (status.strength.getCurrent() - status.strength.getBase() > 0?display.color("azure")+"+":display.color("red")) + (status.strength.getCurrent() - status.strength.getBase()) + display.preset("cp"))) +
                " "           + display.color("banana") + status.agility.getCurrent() + (status.agility.getBase() == status.agility.getCurrent()?"":(display.preset("op") + (status.agility.getCurrent() - status.agility.getBase() > 0?display.color("azure")+"+":display.color("red")) + (status.agility.getCurrent() - status.agility.getBase()) + display.preset("cp"))) +
                "\n" + "    " + display.color("banana") + status.will.getCurrent() + (status.will.getBase() == status.will.getCurrent()?"":(display.preset("op") + (status.will.getCurrent() - status.will.getBase() > 0?display.color("azure")+"+":display.color("red")) + (status.will.getCurrent() - status.will.getBase()) + display.preset("cp"))) +
                " "           + display.color("banana") + status.intelligence.getCurrent() + (status.intelligence.getBase() == status.intelligence.getCurrent()?"":(display.preset("op") + (status.intelligence.getCurrent() - status.intelligence.getBase() > 0?display.color("azure")+"+":display.color("red")) + (status.intelligence.getCurrent() - status.intelligence.getBase()) + display.preset("cp"))) +
                " "           + display.color("banana") + status.perception.getCurrent() + (status.perception.getBase() == status.perception.getCurrent()?"":(display.preset("op") + (status.perception.getCurrent() - status.perception.getBase() > 0?display.color("azure")+"+":display.color("red")) + (status.perception.getCurrent() - status.perception.getBase()) + display.preset("cp"))) + display.reset() +
                "\n" + "  "   + display.preset("obj") + "Inventory" +  display.base() +
                "\n" + "    " + inventory +
                "\n" + "  "   + display.preset("obj") + "Lore" +  display.base() +
                "\n" + "    " + lore;
    }
}






