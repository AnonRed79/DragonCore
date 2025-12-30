package net.anonhub.dragonCore.modules.base_module;

import net.anonhub.dragonCore.entityEngine.Effect;
import net.anonhub.dragonCore.entityEngine.entity.Status;
import net.anonhub.dragonCore.entityEngine.Effect;
import net.anonhub.dragonCore.entityEngine.entity.Status;

import static net.anonhub.dragonCore.engine.Settings.config;


public class Effects {
    public static final Effect HEAL = new Effect(
        "heal",
        (Status status, int strength)->{},
        (Status status, int strength)->status.vitality.current += (double) strength / config.getByteSingle("tickSpeed/current"),
        (Status status, int strength)->{}
    );
}
