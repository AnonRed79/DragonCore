package net.anonhub.dragonCore.entityEngine.entity.sprite;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.ArrayList;
import java.util.Map;

import static net.anonhub.dragonCore.engine.Settings.logger;

// TODO basically need to do all of this. Implement all the ways a texture set can be used and changed.
public class TextureSet {
    public float width;
    public float height;

    public Map<String, ArrayList<Texture>> animations;
    public Texture currentTexture;

    public TextureSet(String texturePath) {
        try {
            currentTexture = new Texture(texturePath);
        } catch (
        GdxRuntimeException e) {
            if (String.valueOf(e).contains("Couldn't load file: ")) {
                logger.log("Missing asset: "+String.valueOf(e).split("Couldn't load file: ")[1]);
            } else {
                throw e;
            }
            currentTexture = new Texture(MissingTexture.get());
        }
        this.width = currentTexture.getWidth();
        this.height = currentTexture.getHeight();
    }
    public TextureSet(Texture texture) {
        currentTexture = texture;
        this.width = currentTexture.getWidth();
        this.height = currentTexture.getHeight();
    }
    public TextureSet(String texturePath, float width, float height) {
        try {
            currentTexture = new Texture(texturePath);
        } catch (
        GdxRuntimeException e) {
            if (String.valueOf(e).contains("Couldn't load file: ")) {
                logger.log("Missing asset: "+String.valueOf(e).split("Couldn't load file: ")[1]);
            } else {
                throw e;
            }
            currentTexture = new Texture(MissingTexture.get());
        }
        this.width = width;
        this.height = height;
    }
    public TextureSet(Texture texture, float width, float height) {
        currentTexture = texture;
        this.width = width;
        this.height = height;
    }
}
