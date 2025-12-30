package net.anonhub.dragonCore.guiEngine;

import com.badlogic.gdx.Gdx;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.guiEngine.terminalErrors.IncorrectNumberOfArgumentsError;
import net.anonhub.dragonCore.engine.interfaces.ICommand;
import net.anonhub.dragonCore.guiEngine.terminalErrors.IncorrectNumberOfArgumentsError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.anonhub.dragonCore.engine.KeyMethods.keyTracker;
import static net.anonhub.dragonCore.engine.Settings.logger;
import static net.anonhub.dragonCore.engine.Settings.subMode;
import static net.anonhub.dragonCore.entityEngine.Entity.entities;

/**
 * creates Commands for the terminal
 */
public class Commands {
    public static Map<String, ICommand> commandMap = new HashMap<>();

    static {
        commandMap.put("kill-game", (ArrayList<String> arguments)-> {
            Gdx.app.exit();
            return true;
        });
        commandMap.put("close", (ArrayList<String> arguments)->{
            subMode.terminal=false;
            return true;
        });
        commandMap.put("key-tracker", (ArrayList<String> arguments)->{
            if (!arguments.isEmpty()) {
                keyTracker = Byte.parseByte(arguments.getFirst());
            } else {
                switch (keyTracker) {
                    case 0,2 -> keyTracker = 1;
                    case 1 -> keyTracker = 0;
                }
            }
            return true;
        });
        commandMap.put("boxes", (ArrayList<String> arguments)->{
            if (arguments.isEmpty()) {
                subMode.boxes= !subMode.boxes;
            } else {
                subMode.boxes = Boolean.parseBoolean(arguments.getFirst());
            }
            return true;
        });
        commandMap.put("tp", (ArrayList<String> arguments)->{
            if (arguments.size() != 2) {
                new IncorrectNumberOfArgumentsError("expected 2 arguments got " + arguments.size());
                return false;
            }

            getCoordValue(arguments.get(0), 0);
            getCoordValue(arguments.get(1), 1);

            return true;
        });
        commandMap.put("data", (ArrayList<String> arguments)->{
            if (!arguments.isEmpty()) {
                new IncorrectNumberOfArgumentsError("expected 0 arguments got " + arguments.size());
                return false;
            }
            subMode.menu = false;
            subMode.terminal = false;
            subMode.dataDisplay = true;
            return true;
        });
        commandMap.put("current", (ArrayList<String> arguments)->{
            Entity.entities.getPlayer().collisionBox.boxes.get("body").setRight(-100);
            return true;
        });
    }

    public static void run(String input) {
        String[] in = input.split(" ");
        String command = in[0];
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(in).subList(1, in.length));

        if (commandMap.containsKey(command)) {
            boolean result = commandMap.get(command).runCommand(arguments);
            if (result) {
                logger.log(command + " ran successfully");
            }
        } else {
            logger.log("Unknown command: " + input);
        }
    }
    public static void run(Object input) {
        run(input.toString());
    }

    private static void getCoordValue(String argument, int coord) {
        float x = 0;
        boolean tilda = false;
        if (argument.charAt(0)=='~') {
            argument = argument.substring(1);
            tilda = true;
        }
        try {
            if (!tilda || !argument.isEmpty()) {
                x = Float.parseFloat(argument);
            }
        } catch (NumberFormatException NFE) {
            throw new IllegalArgumentException("Expected a float or \"~\", but got \""+argument+"\"");
        }
        if (tilda) {
            Entity.entities.getPlayer().data.coords.absolute[coord] += x;
        } else {
            Entity.entities.getPlayer().data.coords.absolute[coord] = x;
        }
    }
}
