package net.anonhub.dragonCore.entityEngine.entity;

import net.anonhub.dragonCore.entityEngine.Effect;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.entityEngine.Effect;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.entityEngine.entity.status.stats.AbilityStat;
import net.anonhub.dragonCore.entityEngine.entity.status.stats.Experience;
import net.anonhub.dragonCore.entityEngine.entity.status.stats.PoolStat;
import net.anonhub.dragonCore.engine.interfaces.ITickable;

import java.util.ArrayList;

import static net.anonhub.dragonCore.engine.Settings.display;

public class Status implements ITickable, IDisplayable {
    private final Entity entity;
    private final ArrayList<Effect> effects = new ArrayList<>();

    public final Experience experience;

    public final PoolStat vitality;
    public final PoolStat stamina;
    public final PoolStat mana;

    public final AbilityStat constitution;
    public final AbilityStat strength;
    public final AbilityStat agility;
    public final AbilityStat will;
    public final AbilityStat intelligence;
    public final AbilityStat perception;



    public Status(Entity entity) {
        this.entity = entity;
        experience = new Experience(this, 3);

        vitality = new PoolStat(this, "vitality");
        stamina = new PoolStat(this, "stamina");
        mana = new PoolStat(this, "mana");

        constitution = new AbilityStat(this, "constitution");
        strength = new AbilityStat(this, "strength");
        agility = new AbilityStat(this, "agility");
        will = new AbilityStat(this, "will");
        intelligence = new AbilityStat(this, "intelligence");
        perception = new AbilityStat(this, "perception");
        addToList();
    }

    public float getSpeed() {
        return agility.getCurrent();
    }



    public void addEffect(Effect effect) {
        effect.status = this;
        effects.add(effect);
    }
    private void applyEffects() {
        for (int x = 0; x < effects.toArray().length; x++) {
            Effect effect = effects.get(x);
            if (!effect.isStarted()) {
                effect.start();
            }
            effect.apply();
        }
    }
    private void endEffects() {
        for (int x = 0; x < effects.toArray().length; x++) {
            Effect effect = effects.get(x);
            if (effect.durationLeft <= 0) {
                effect.end();
                effects.remove(x);
                x--;
            }
        }
    }

    public void removeEffect(Effect effect) {
        effects.remove(effect);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String display() {
        return
                "\n" + "  "   + display.preset("obj") + "Status" + display.base() +
                        "\n" + "    " + display.color("yellow") + experience.getLevelExperience() + display.reset() + display.preset("/") + display.color("yellow") + experience.getExperienceToNextLevel() + display.reset() +
                        "\n" + "    " + display.color("yellow") + experience.getDisplayLevel() + display.reset() +
                        " "           + display.color("pink")   + experience.getDisplayTier() + display.reset() +
                        " "           + display.color("mint")   + experience.getDisplayAscendance() + display.reset() +
                        "\n" + "    " + display.color("red")    + (int) vitality.current+"/"+ (int) vitality.getMax() + display.reset() +
                        " "           + display.color("green")  + (int) stamina.current+"/"+ (int) stamina.getMax() + display.reset() +
                        " "           + display.color("blue")   + (int) mana.current+"/"+ (int) mana.getMax() + display.reset() +
                        "\n" + "    " + display.preset("obj") + "constitution" + display.preset(":") + " " + display.color("banana") + constitution.getCurrent() + (constitution.getBase() == constitution.getCurrent()?"":(display.preset("op") + (constitution.getCurrent() - constitution.getBase() > 0?display.color("azure")+"+":display.color("red")) + (constitution.getCurrent() - constitution.getBase()) + display.preset("cp"))) +
                        "\n" + "    " + display.preset("obj") + "strength" + display.preset(":") + " " + display.color("banana") + strength.getCurrent() + (strength.getBase() == strength.getCurrent()?"":(display.preset("op") + (strength.getCurrent() - strength.getBase() > 0?display.color("azure")+"+":display.color("red")) + (strength.getCurrent() - strength.getBase()) + display.preset("cp"))) +
                        "\n" + "    " + display.preset("obj") + "agility" + display.preset(":") + " " + display.color("banana") + agility.getCurrent() + (agility.getBase() == agility.getCurrent()?"":(display.preset("op") + (agility.getCurrent() - agility.getBase() > 0?display.color("azure")+"+":display.color("red")) + (agility.getCurrent() - agility.getBase()) + display.preset("cp"))) +
                        "\n" + "    " + display.preset("obj") + "will" + display.preset(":") + " " + display.color("banana") + will.getCurrent() + (will.getBase() == will.getCurrent()?"":(display.preset("op") + (will.getCurrent() - will.getBase() > 0?display.color("azure")+"+":display.color("red")) + (will.getCurrent() - will.getBase()) + display.preset("cp"))) +
                        "\n" + "    " + display.preset("obj") + "intelligence" + display.preset(":") + " " + display.color("banana") + intelligence.getCurrent() + (intelligence.getBase() == intelligence.getCurrent()?"":(display.preset("op") + (intelligence.getCurrent() - intelligence.getBase() > 0?display.color("azure")+"+":display.color("red")) + (intelligence.getCurrent() - intelligence.getBase()) + display.preset("cp"))) +
                        "\n" + "    " + display.preset("obj") + "perception" + display.preset(":") + " " + display.color("banana") + perception.getCurrent() + (perception.getBase() == perception.getCurrent()?"":(display.preset("op") + (perception.getCurrent() - perception.getBase() > 0?display.color("azure")+"+":display.color("red")) + (perception.getCurrent() - perception.getBase()) + display.preset("cp"))) + display.reset();
    }

    @Override
    public void tick() {
        applyEffects();
        endEffects();
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }
}
