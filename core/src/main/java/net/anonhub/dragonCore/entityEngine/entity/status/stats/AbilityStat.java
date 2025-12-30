package net.anonhub.dragonCore.entityEngine.entity.status.stats;

import net.anonhub.dragonCore.entityEngine.entity.Status;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.entity.Status;

import static net.anonhub.dragonCore.engine.Settings.display;

public class AbilityStat implements ITickable, IDisplayable {
    public final String name;

    private final Status status;

    public int effectModifier;
    public int statPoints;

    private int base;
    private int modifier;
    private int current;

    public AbilityStat(Status status, String name) {
        this.status = status;
        this.name = name;
        addToList();
    }

    public int getCurrent() {return current;}

    public int getBase() {return base;}


    @Override
    public void tick() {
        base = status.experience.getLevel()/5 + statPoints + 10;
        current = base + effectModifier;
        modifier = current - base;
        effectModifier = 0;
    }

    @Override
    public String toString() {
        return name + " " + current + " " + (modifier!=0?modifier>0?"+"+modifier:modifier:"");
    }

    @Override
    public String display() {
        return
                display.preset("obj") + display.capFirst(name) +
                "\n  " +display.preset("var") + "Points" + display.preset(":") + " " + display.color("yellow") + statPoints + display.base() +
                "\n  " +display.preset("var") + "Base" + display.preset(":") + " " + display.color("yellow") + base + display.base() +
                "\n  " +display.preset("var") + "Current" + display.preset(":") + " " + display.color("yellow") + current + display.base();
    }
}
