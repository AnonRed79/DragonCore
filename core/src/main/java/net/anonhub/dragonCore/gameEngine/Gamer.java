package net.anonhub.dragonCore.gameEngine;

import net.anonhub.dragonCore.engine.Renders;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import static net.anonhub.dragonCore.engine.Settings.logger;

public class Gamer {
    private final File modsFolder = new File("./mods");
    private Renders renders;
    private URLClassLoader gameLoader;
    public Gamer(Renders renders) {
        this.renders = renders;
    }
    public void loadGame() {
        modsFolder.mkdir();


        File[] jars = modsFolder.listFiles((d, n) -> n.endsWith(".jar"));
        if (jars == null || jars.length == 0) return;

        try {
            URL[] urls = new URL[jars.length];
            for (int i = 0; i < jars.length; i++) {
                urls[i] = jars[i].toURI().toURL();
                logger.log("Found mod jar: " + jars[i].getName());
            }

            // ONE shared loader, parent = main game classloader
            gameLoader = new URLClassLoader(urls, getClass().getClassLoader());

            logger.log("Mods added to runtime classpath");

        } catch (Exception e) {
            logger.log("Failed to load mods");
            e.printStackTrace();
        }

        try {
            Class<?> c = Class.forName(
                "net.anonhub.untitledPlatformer.Mod",
                true,
                gameLoader
            );

            c.getDeclaredConstructor(Renders.class)
                .newInstance(this.renders);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
