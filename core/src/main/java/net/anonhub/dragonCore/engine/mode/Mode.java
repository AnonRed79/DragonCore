package net.anonhub.dragonCore.engine.mode;

import net.anonhub.dragonCore.engine.Settings;

import static net.anonhub.dragonCore.engine.Settings.logger;

public enum Mode {
    TEST,
    GAME;

    private static Mode currentMode = TEST;
    static {
        Settings.logger.log("Mode set to: " + currentMode);
    }


    public static void setMode(Mode newMode) {
        currentMode = newMode;
        Settings.logger.log("Mode set to: "+currentMode);
    }
    public static Mode getCurrentMode() {
        return currentMode;
    }
}
