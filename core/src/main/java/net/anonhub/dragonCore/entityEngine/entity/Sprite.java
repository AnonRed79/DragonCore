package net.anonhub.dragonCore.entityEngine.entity;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.anonhub.dragonCore.engine.Renders;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.engine.interfaces.IDisplayable;
import net.anonhub.dragonCore.engine.interfaces.ITickable;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.entityEngine.entity.sprite.TextureSet;


public class Sprite implements ITickable, IDisplayable {
    private final Entity entity;
    public final TextureSet texture;
    public final Renders renders;

    private int previousTime = 0;
    private double lag = 0;

    public Sprite(Entity entity, String texturePath, float width, float height, Renders renders) {
        this.entity = entity;
        texture = new TextureSet(texturePath, width, height);
        this.renders = renders;
        addToList();
    }

    public Sprite(Entity entity, String texturePath, Renders renders) {
        Sprite sprite = new Sprite(entity, texturePath, 1f,1f, renders);
        this.entity = sprite.entity;
        this.texture = new TextureSet(texturePath);
        this.renders = renders;
        addToList();
    }


//    TODO interpolate
    public void draw() {
        renders.batch.begin();
        renders.batch.setProjectionMatrix(renders.camera.combined);
//        int currentTime = LocalDateTime.now().get(ChronoField.MILLI_OF_SECOND);
//        lag += ((currentTime - previousTime)*.01);
//        previousTime = currentTime;
//        while (lag >= ticker.tickInterval) {
//            lag -= ticker.tickInterval;
//        }
//        double alpha = lag;

//        float x = (float) ((entity.data.prevCoords.renderCoords()[0]+((entity.data.coords.map[0] - entity.data.prevCoords.map[0]) * alpha))-(texture.width/2f));
//        float y = (float) ((entity.data.prevCoords.renderCoords()[1]+((entity.data.coords.map[1] - entity.data.prevCoords.map[1]) * alpha))-(texture.height/2f));

        float x = entity.data.coords.renderCoords()[0] - (texture.width/2f);
        float y = entity.data.coords.renderCoords()[1] - (texture.height/2f);


        renders.batch.draw(texture.currentTexture,x,y,texture.width,texture.height);
        renders.batch.end();
    }

    public float getBottom() {
        return entity.data.coords.absolute[1]-texture.height/2f;
    }
    public float getTop() {
        return entity.data.coords.absolute[1]+texture.height/2f;
    }
    public float getRight() {
        return entity.data.coords.absolute[0]+texture.width/2f;
    }
    public float getLeft() {
        return entity.data.coords.absolute[0]-texture.width/2f;
    }
    public float[] getCenter() {
        return new float[]{entity.data.coords.absolute[0], entity.data.coords.absolute[1]};
    }
    public float[] getTopLeft() {
        return new float[]{getLeft(), getTop()};
    }
    public float[] getTopRight() {
        return new float[]{getRight(), getTop()};
    }
    public float[] getBottomLeft() {
        return new float[]{getLeft(), getBottom()};
    }
    public float[] getBottomRight() {
        return new float[]{getRight(), getBottom()};
    }


    @Override
    public void tick() {
//        coords.screenX += entity.data.getVelocity()[0];
//        coords.screenY += entity.data.getVelocity()[1];
    }

//    TODO fix this, should display the current animation plus it's frame
    @Override
    public String display() {
        return "";
    }
}
