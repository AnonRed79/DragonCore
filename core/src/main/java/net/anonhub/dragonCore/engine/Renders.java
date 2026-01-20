package net.anonhub.dragonCore.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
/**
 * Encapsulate, SpriteBatch, ShapeRenderer, BitmapFont, and OrthographicCamera into one class
 */
public class Renders {
    public SpriteBatch batch;
    public ShapeRenderer shape;
    public BitmapFont font;
    public OrthographicCamera camera;
    public Renders(SpriteBatch batch, ShapeRenderer shape, BitmapFont font, OrthographicCamera camera) {
        this.camera = camera;
        this.camera.setToOrtho(false);
        this.batch = batch;
        this.shape = shape;
        this.font = font;
    }
}
