package net.anonhub.dragonCore.entityEngine.entity.status.stats;

import net.anonhub.dragonCore.entityEngine.entity.Status;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.entity.Status;

import static net.anonhub.dragonCore.engine.Settings.display;

public class PoolStat implements ITickable, IDisplayable {
    public final String name;

    private final Status status;

    public int statPoints;
    public int effectModifier;
    public double current;

    private double modifier;
    private double base;
    private double max;



    public PoolStat(Status status, String name) {
        this.name = name;
        this.status = status;
        current = 100;
        tick();
        addToList();
    }

    public double getBase() {
        return base;
    }
    public double getMax() {
        return max;
    }



    @Override
    public void tick() {
//      TODO change +100 based on species and race
        base = status.experience.getLevel() * 5 + (statPoints*5) + 100;
        max = base + effectModifier;
        modifier = max - base;
        if (current < 0) {current = 0;}
        if (current > max) {current = max;}
        effectModifier = 0;
    }

    @Override
    public String toString() {
        return
                display.capFirst(name) + ": " + current + "/" + max + (modifier!=0?modifier>0?"(+"+modifier+")":"("+modifier+")":"");
    }

    @Override
    public String display() {
        return
                display.preset("obj") + display.capFirst(name) +
                        "\n  " +display.preset("var") + "Points" + display.preset(":") + " " + display.color("yellow") + statPoints + display.base() +
                        "\n  " +display.preset("var") + "Base" + display.preset(":") + " " + display.color("yellow") + base + display.base() +
                        "\n  " +display.preset("var") + "Max" + display.preset(":") + " " + display.color("yellow") + max + display.base() +
                        "\n  " +display.preset("var") + "Current" + display.preset(":") + " " + display.color("yellow") + current + display.base();
    }
}
