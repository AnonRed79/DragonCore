package net.anonhub.dragonCore.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.anonhub.dragonCore.engine.interfaces.IKeyMethod;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.guiEngine.Terminal;
import net.anonhub.dragonCore.guiEngine.TextDisplay;
import net.anonhub.dragonCore.modules.base_module.Mod;
import net.anonhub.dragonCore.tagEngine.Tag;
import net.anonhub.dragonCore.tagEngine.Tags;

import java.util.ArrayList;
import java.util.List;

import static net.anonhub.dragonCore.engine.Settings.logger;
import static net.anonhub.dragonCore.entityEngine.Entity.entities;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    public static final ArrayList<ITickable> tickables = new ArrayList<>();
//    public static final ArrayList<String> modules = new ArrayList<>();

    public Settings settings;

    private static Renders renders;

    public static DataDisplay dataDisplay;
    public static Terminal terminalInput;

    @Override
    public void create() {
        logger.log("app started");
        renders = new Renders(new SpriteBatch(), new ShapeRenderer(), new BitmapFont(), new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        terminalInput = new Terminal(renders);
        dataDisplay = new DataDisplay(renders);
        dataDisplay.setTitle("Data Display:");

        new Mod(renders);


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyTyped(char character) {
                if (Settings.subMode.terminal) {
                    if ('\b' == character) {
                        terminalInput.backspace();
                    } else if (127 == character) {
                        terminalInput.delete();
                    } else if ('\n' == character || '\r' == character) {
                        terminalInput.enter();
                    } else {
                        terminalInput.addCharacter(character);
                    }
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyDown(int keyCode) {
                for(IKeyMethod keyDownMethod: KeyMethods.keyDownMethods) {
                    keyDownMethod.keyMethod(keyCode);
                }
                return true;
            }

            @Override
            public boolean keyUp(int keyCode) {
                for(IKeyMethod keyUpMethod: KeyMethods.keyUpMethods) {
                    keyUpMethod.keyMethod(keyCode);
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return super.touchDown(screenX, screenY, pointer, button);
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return super.touchUp(screenX, screenY, pointer, button);
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        renders.camera.setToOrtho(false, width, height);
        renders.camera.update();
    }

    @Override
    public void render() {
        if (!Settings.ticker.ticking) {
            Settings.ticker.startTicking();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        draws grid
//        TODO make it so the grid resizes base on the screen size
        if (Settings.subMode.grid) {
            renders.shape.begin(ShapeRenderer.ShapeType.Line);
            renders.shape.setProjectionMatrix(renders.camera.combined);
            renders.shape.setColor(1,1,1,1);
            for (int yPos = 1; yPos < Gdx.graphics.getHeight(); yPos+=20) {
                for (int xPos = 1; xPos < Gdx.graphics.getWidth(); xPos+=20) {
                    renders.shape.rect(xPos, yPos, 20, 20);
                }
            }
            renders.shape.end();
        }

        for (Tag tag:List.of(Tags.FLOOR, Tags.WALL, Tags.ENTITY, Tags.OBJECT, Tags.CEILING)){
            for (Entity entity: entities) {
                if (entity.data.tags.contains(tag)) {
//                    entity.tick();
                    entity.sprite.draw();
                    entity.drawBoxes();
                }
            }
        }

        if (Settings.subMode.terminal) {
            terminalInput.draw();
        } else if (Settings.subMode.dataDisplay) {
            dataDisplay.addLine("X: "+entities.getPlayer().data.coords.absolute[0]);
            dataDisplay.addLine("Y: "+entities.getPlayer().data.coords.absolute[1]);
//            dataDisplay.addLine("X: "+entities.get(1).data.coords.absolute[0]);
//            dataDisplay.addLine("Y: "+entities.get(1).data.coords.absolute[1]);
            dataDisplay.draw();
        }
    }

    @Override
    public void dispose() {
        Settings.ticker.stop();
        renders.shape.dispose();
        renders.batch.dispose();
        renders.font.dispose();
        logger.log("app ended");
    }




//    TODO
//    public static void loadModules(File modulesDir) {
//        if (!modulesDir.exists() || !modulesDir.isDirectory()) return;
//
//        File[] jarFiles = modulesDir.listFiles((dir, name) -> name.endsWith(".jar"));
//        if (jarFiles == null) return;
//
//        for (File jar : jarFiles) {
//            try {
//                URL jarUrl = jar.toURI().toURL();
//                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, net.anonhub.dragonCore.engine.Main.class.getClassLoader());
//
//                try (JarFile jarFile = new JarFile(jar)) {
//                    JarEntry entry = jarFile.getJarEntry("main.meta");
//                    if (entry == null) continue;
//
//                    InputStream input = jarFile.getInputStream(entry);
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    String className = reader.readLine().trim(); // e.g. net.anonhub.base_module.module
//
//                    Class<?> clazz = classLoader.loadClass(className);
//                    Object instance = clazz.getDeclaredConstructor().newInstance();
//                    if (instance instanceof ITickable) {
//                        tickables.add((ITickable) instance);
////                        modules.add(className);
//                        Settings.display.println("Loaded module: " + className);
//                    } else {
//                        Settings.display.println("Class " + className + " does not implement ITickable, skipping.");
//                    }
//                    System.out.println("Loaded module: " + className);
//
//                } catch (Exception e) {
//                    System.err.println("Failed to load module from " + jar.getName());
//                    e.printStackTrace();
//                }
//
//            } catch (Exception e) {
//                System.err.println("Failed to process jar: " + jar.getName());
//                e.printStackTrace();
//            }
//        }
//    }
}
