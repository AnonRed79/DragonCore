package net.anonhub.dragonCore.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.anonhub.dragonCore.guiEngine.TextDisplay;

public class DataDisplay extends TextDisplay {
    public DataDisplay(Renders renders) {
        super(renders);
    }

    @Override
    public void draw() {
        super.draw();
        clearLines();
    }
}
