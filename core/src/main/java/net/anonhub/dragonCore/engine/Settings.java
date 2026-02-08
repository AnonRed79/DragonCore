package net.anonhub.dragonCore.engine;

import net.anonhub.dragonlib.*;
import net.anonhub.dragonCore.guiEngine.Commands;
import net.anonhub.dragonCore.engine.mode.SubMode;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class Settings {
    private static String json;
    static {
        try {
            json = Files.readString(Path.of("config.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonConfig config = new Gson().fromJson(json, JsonConfig.class);
    public static final Commands commands = new Commands();
    public static Logger logger;
    static {logger = new Logger("./logs/log-" + LocalDate.now());}
    public static String name = "GameEngine";
    public static Ticker ticker = new Ticker();
    public static Text display = new Text();
    public static int previousTick = 0;
    public static SubMode subMode = new SubMode();
}
