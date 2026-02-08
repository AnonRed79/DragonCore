package net.anonhub.dragonCore.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import net.anonhub.dragonlib.Direction;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.IKeyMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.anonhub.dragonCore.engine.Main.terminalInput;
import static net.anonhub.dragonCore.engine.Settings.*;

public class KeyMethods {

    public static ArrayList<IKeyMethod> keyDownMethods = new ArrayList<>();
    public static ArrayList<IKeyMethod> keyUpMethods = new ArrayList<>();
//    TODO implement mouse methods
    public static ArrayList<IKeyMethod> mouseDownMethods = new ArrayList<>();
    public static ArrayList<IKeyMethod> mouseUpMethods = new ArrayList<>();
    public static byte keyTracker = 0;

    private static Map<String, Boolean> heldKeys = new HashMap<>();






//    in world actions
//        move up
    static {
        keyDownMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveUp) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('y').put("actions.moveUp", entity.status.getSpeed());
                            entity.data.direction.add(Direction.NORTH);
                        }
                    }
                }
            }
        });
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveUp) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('y').remove("actions.moveUp");
                            entity.data.direction.remove(Direction.NORTH);
                        }
                    }
                }
            }
        });
//        move down
        keyDownMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveDown) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('y').put("actions.moveDown", -entity.status.getSpeed());
                            entity.data.direction.add(Direction.SOUTH);
                        }
                    }
                }
            }
        });
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveDown) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('y').remove("actions.moveDown");
                            entity.data.direction.remove(Direction.SOUTH);
                        }
                    }
                }
            }
        });
//        move left
        keyDownMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveLeft) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity : Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('x').put("actions.moveLeft", -entity.status.getSpeed());
                            entity.data.direction.add(Direction.WEST);
                        }
                    }
                }
            }
        });
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveLeft) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('x').remove("actions.moveLeft");
                            entity.data.direction.remove(Direction.WEST);
                        }
                    }
                }
            }
        });
//        move right
        keyDownMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveRight) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('x').put("actions.moveRight", entity.status.getSpeed());
                            entity.data.direction.add(Direction.EAST);
                        }
                    }
                }
            }
        });
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.moveRight) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.velocitySource.get('x').remove("actions.moveRight");
                            entity.data.direction.remove(Direction.EAST);
                        }
                    }
                }
            }
        });
        keyDownMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.sprint) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.sprint = true;
                        }
                    }
                }
            }
        });
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.sprint) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    for (Entity entity:Entity.entities) {
                        if (entity.data.player) {
                            entity.data.sprint = false;
                        }
                    }
                }
            }
        });
//        open terminal
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.openTerminal) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    subMode.terminal = true;
                    subMode.menu = true;
                    subMode.dataDisplay = false;
                }
            }
        });
//        quit game
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.quit) {
                if (keyCode == settingKeyCode && !subMode.menu) {
                    Gdx.app.exit();
                }
            }
        });



//      terminal actions
//        move selector
        keyDownMethods.add((int keyCode)-> {
            if (keyCode == Input.Keys.LEFT && subMode.terminal) {
                terminalInput.decreaseIndex();
            }
        });
        keyDownMethods.add((int keyCode)-> {
            if (keyCode == Input.Keys.RIGHT && subMode.terminal) {
                terminalInput.increaseIndex();
            }
        });
        keyDownMethods.add((int keyCode)-> {
            if (keyCode == Input.Keys.UP && subMode.terminal) {
                terminalInput.increaseLineIndex();
            }
        });
        keyDownMethods.add((int keyCode)-> {
            if (keyCode == Input.Keys.DOWN && subMode.terminal) {
                terminalInput.decreaseLineIndex();
            }
        });
//        close
        keyUpMethods.add((int keyCode) -> {
            for (short settingKeyCode: config.actions.exit) {
                if (keyCode == settingKeyCode && subMode.terminal) {
                    subMode.terminal = false;
                    subMode.menu = false;
                }
            }
        });


//        key tracker
        keyDownMethods.add((int keyCode) -> {
            switch (keyTracker) {
                case 1 -> logger.log("Key: "+keyCode);
                case 2 -> logger.log("key down: "+keyCode);
            }
        });
        keyUpMethods.add((int keyCode) -> {
            if (keyTracker==2) {
                logger.log("key up: "+keyCode);
            }
        });
    }
}
