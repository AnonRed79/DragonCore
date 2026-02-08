package net.anonhub.dragonCore.engine;

import net.anonhub.dragonCore.engine.interfaces.ITickable;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.anonhub.dragonCore.engine.Settings.previousTick;
import static net.anonhub.dragonCore.engine.interfaces.ITickable.tickables;

public class Ticker {
    public final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public boolean ticking = false;

    public byte tickSettings = Settings.config.tickSpeed.current;

    public long tickInterval = 1000L/tickSettings;

    private int sessionTick = 0;


    public Ticker() {
        if (tickSettings < 1 || tickSettings > 126) {
            throw new IllegalArgumentException("current ticker speed can be no more than 127 and no less than 1 but it is " + tickSettings);
        }
    }

    public void startTicking() {
        ticking = true;
        scheduler.scheduleAtFixedRate(() -> {
            try {
                sessionTick++;
                tickables.forEach(ITickable::tick);
            } catch (Exception e) {
                e.printStackTrace();}
        }, 20, tickInterval, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        scheduler.shutdownNow();
    }

    public int getSessionTick() {
        return sessionTick;
    }

    public boolean onNewTick() {
        if (getSessionTick() != Settings.previousTick) {
            Settings.previousTick = getSessionTick();
            return true;
        }
        return false;
    }
}
