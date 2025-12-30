package net.anonhub.dragonCore.mapEngine;

import com.badlogic.gdx.Gdx;

public class Coords {
    public static float[] camera = new float[]{0,0};
    public float[] absolute = new float[]{0,0};
    public float[] previous = new float[]{0,0};

    public Coords() {}
    public Coords(float x, float y) {
        absolute = new float[]{x,y};
    }


    public float[] renderCoords(){
        return new float[]{(absolute[0]-camera[0])+Gdx.graphics.getWidth()/2f, (absolute[1]-camera[1])+Gdx.graphics.getHeight()/2f};
    }

    @Override
    public String toString() {
        return "X: "+String.format("%.2f", absolute[0])+" Y: "+String.format("%.2f", absolute[1]);
    }
}
