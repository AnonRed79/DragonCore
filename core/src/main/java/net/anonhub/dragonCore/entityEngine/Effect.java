package net.anonhub.dragonCore.entityEngine;

import net.anonhub.dragonCore.engine.Settings;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.entityEngine.entity.Status;
import net.anonhub.dragonCore.entityEngine.interfaces.IEffect;

public class Effect implements IDisplayable {
    public Status status;
    public int durationLeft;

    private boolean started = false;
    private boolean ended = false;
    private int strength;
    private int duration;

    private final String name;
    private final IEffect onStart;
    private final IEffect onApply;
    private final IEffect onEnd;



    public Effect(String name, IEffect onStart, IEffect onApply, IEffect onEnd){
        this.name = name;
        this.onStart = onStart;
        this.onApply = onApply;
        this.onEnd = onEnd;
    }

    public Effect(Effect effect, int strength, int duration){
        this.name = effect.name;
        this.onStart = effect.onStart;
        this.onApply = effect.onApply;
        this.onEnd = effect.onEnd;
        this.strength = strength;
        // TODO duration*tickSpeed
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isStarted() {return started;}

    public boolean isEnded() {return ended;}


    public void start(){
        started = true;
        onStart.apply(status, strength);
        durationLeft = duration;
    }

    public void apply(){
        onApply.apply(status, strength);
        durationLeft--;
    }

    public void end(){
        ended = true;
        onEnd.apply(status, strength);
    }





    @Override
    public String display () {
        return name;
    }
}
