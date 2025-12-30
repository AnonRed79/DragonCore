package net.anonhub.dragonCore.engine;

import net.anonhub.commonlib.*;
import net.anonhub.dragonCore.guiEngine.Commands;
import net.anonhub.dragonCore.engine.mode.SubMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Settings {
    public static final Commands commands = new Commands();
    public static final ConfigParser config;

    static {
        HashMap<String, HashMap<String, ArrayList<String>>> map = new HashMap<>();
        map.put("version", new HashMap<>());
        map.put("tickSpeed", new HashMap<>());
        map.put("difficulty", new HashMap<>());
        map.put("actions", new HashMap<>());

        map.get("version").put("current", new ArrayList<>(List.of("0.0.0")));
        map.get("version").put("current"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("\"")));

        map.get("tickSpeed").put("current", new ArrayList<>(List.of("20")));
        map.get("tickSpeed").put("current"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("b")));
        map.get("tickSpeed").put("default", new ArrayList<>(List.of("20")));
        map.get("tickSpeed").put("default"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("b")));

        map.get("difficulty").put("default", new ArrayList<>(List.of("20")));
        map.get("difficulty").put("default"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("b")));
        map.get("difficulty").put("max", new ArrayList<>(List.of("100")));
        map.get("difficulty").put("max"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("b")));
        map.get("difficulty").put("min", new ArrayList<>(List.of("10")));
        map.get("difficulty").put("min"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("b")));
        map.get("difficulty").put("current", new ArrayList<>(List.of("20")));
        map.get("difficulty").put("current"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("b")));

        map.get("actions").put("openTerminal", new ArrayList<>(List.of("66")));
        map.get("actions").put("openTerminal"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s")));
        map.get("actions").put("exit", new ArrayList<>(List.of("111")));
        map.get("actions").put("exit"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s")));
        map.get("actions").put("quit", new ArrayList<>(List.of("111")));
        map.get("actions").put("quit"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s")));
        map.get("actions").put("moveUp", new ArrayList<>(List.of("51", "19")));
        map.get("actions").put("moveUp"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s", "s")));
        map.get("actions").put("moveDown", new ArrayList<>(List.of("47", "20")));
        map.get("actions").put("moveDown"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s", "s")));
        map.get("actions").put("moveLeft", new ArrayList<>(List.of("29", "21")));
        map.get("actions").put("moveLeft"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s", "s")));
        map.get("actions").put("moveRight", new ArrayList<>(List.of("32", "22")));
        map.get("actions").put("moveRight"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s", "s")));
        map.get("actions").put("sprint", new ArrayList<>(List.of("59", "60")));
        map.get("actions").put("sprint"+ConfigParser.DATA_TYPE_CHAR, new ArrayList<>(List.of("s", "s")));

        config = new ConfigParser("./assets/.cfg", map);
    }
    public static Logger logger;
    static {logger = new Logger("./assets/logs/log-" + LocalDate.now());}
    public static String name = "GameEngine";
    public static Ticker ticker = new Ticker();
    public static Text display = new Text();
    public static byte difficulty = config.getByteSingle("difficulty/current");
    public static int previousTick = 0;
    public static SubMode subMode = new SubMode();
}
