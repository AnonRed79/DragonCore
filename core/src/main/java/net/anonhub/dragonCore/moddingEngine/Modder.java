package net.anonhub.dragonCore.moddingEngine;

import net.anonhub.dragonCore.engine.Renders;

import java.io.File;

import static net.anonhub.dragonCore.engine.Settings.logger;

public class Modder {
    private final File modsFolder = new File("./mods");
    private Renders renders;
    public Modder(Renders renders) {
        this.renders = renders;
    }
    public void mod() {
        System.out.println("here");
        System.out.println("here");
        if (modsFolder.mkdir()) {
            logger.log("mods folder not found, new folder created");
        } else {
            logger.log("mods folder found");
        }
        System.out.println("here");
        System.out.println("here");
    }
}
