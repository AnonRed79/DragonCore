package net.anonhub.dragonCore.entityEngine.boundingBox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.anonhub.dragonCore.entityEngine.Entity;
import net.anonhub.dragonCore.entityEngine.Entity;

public class BoundingBox {
    public float[] definition;
    public Entity entity;
    public Color color;
    public BoundingBoxType type;


    public BoundingBox(Entity entity, Color color, BoundingBoxType boundingBoxType, float startX, float startY, float endX, float endY) {
        this.entity = entity;
        this.color = color;
        this.definition = new float[]{startX, startY, endX, endY};
        this.type = boundingBoxType;
    }
    public BoundingBox(Entity entity, Color color, BoundingBoxType boundingBoxType, float[] start, float[] end) {
        this.entity = entity;
        this.color = color;
        this.definition = new float[]{start[0], start[1], end[0], end[1]};
        this.type = boundingBoxType;
    }
    public BoundingBox(Entity entity, Color color, BoundingBoxType boundingBoxType, float[] definition) {
        this.entity = entity;
        this.color = color;
        this.definition = new float[]{definition[0], definition[1], definition[2], definition[3]};
        this.type = boundingBoxType;
    }

    public float getWidth() {
        return definition[2]-definition[0];
    }
    public float getHeight() {
        return definition[3]-definition[1];
    }

    public float[] getCenter() {
        return new float[]{
            entity.data.coords.absolute[0]+definition[0]+(getWidth()/2),
            entity.data.coords.absolute[1]+definition[1]+(getHeight()/2)
        };
    }

    public float[] getOffset() {
        return new float[]{
            getCenter()[0]-entity.data.coords.absolute[0],
            getCenter()[1]-entity.data.coords.absolute[1]
        };
    }

    public float getBottom() {
        return getCenter()[1]-(getHeight()/2);
    }

    public float getTop() {
        return getCenter()[1]+(getHeight()/2);
    }

    public float getRight() {
        return getCenter()[0]+(getWidth()/2);
    }

    public float getLeft() {
        return getCenter()[0]-(getWidth()/2);
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

    public void setWidth(float width) {
        definition[0] = -(width/2);
        definition[2] = width/2;
    }
    public void setHeight(float height) {
        definition[1] = -(height/2);
        definition[3] = height/2;
    }

    public void setOffset(float x, float y) {
        setOffset(new float[]{x,y});
    }
    public void setOffset(float[] pos) {
        float x = pos[0]+(getWidth()/2);
        definition[0] += x;
        definition[2] += x;
        float y = pos[1]+(getHeight()/2);
        definition[1] += y;
        definition[3] += y;
    }

    public void setCenter(float x, float y) {
        setCenter(new float[]{x,y});
    }
    public void setCenter(float[] pos) {
        this.entity.data.coords.absolute[0] = pos[0];
        this.entity.data.coords.absolute[1] = pos[1];
    }

    public void setBottom(float bottom) {
        this.entity.data.coords.absolute[1] = bottom-definition[1];
    }

    public void setTop(float top) {
        this.entity.data.coords.absolute[1] = top+definition[3];
    }

    public void setRight(float right) {
        this.entity.data.coords.absolute[0] = right+definition[2];
    }

    public void setLeft(float left) {
        this.entity.data.coords.absolute[0] = left-definition[0];
    }

    public void setTopLeft(float x, float y) {
        setTopLeft(new float[]{x,y});
    }
    public void setTopLeft(float[] pos) {
        setLeft(pos[0]);
        setTop(pos[1]);
    }

    public void setTopRight(float x, float y) {
        setTopRight(new float[]{x,y});
    }
    public void setTopRight(float[] pos) {
        setRight(pos[0]);
        setTop(pos[1]);
    }

    public void setBottomLeft(float x, float y) {
        setBottomLeft(new float[]{x,y});
    }
    public void setBottomLeft(float[] pos) {
        setLeft(pos[0]);
        setBottom(pos[1]);
    }

    public void setBottomRight(float x, float y) {
        setBottomRight(new float[]{x,y});
    }
    public void setBottomRight(float[] pos) {
        setRight(pos[0]);
        setBottom(pos[1]);
    }



    public void draw() {
        entity.sprite.renders.shape.begin(ShapeRenderer.ShapeType.Line);
        entity.sprite.renders.shape.setColor(color);
        float x1 = (entity.data.coords.renderCoords()[0]+getOffset()[0])-getWidth()/2;
        float y1 = (entity.data.coords.renderCoords()[1]+getOffset()[1])-getHeight()/2;
        entity.sprite.renders.shape.rect(x1, y1, getWidth(), getHeight());
        entity.sprite.renders.shape.end();
    }
}
