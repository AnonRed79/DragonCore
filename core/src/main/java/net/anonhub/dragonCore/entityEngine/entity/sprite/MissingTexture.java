package net.anonhub.dragonCore.entityEngine.entity.sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class MissingTexture{
    private static final File file = new File("./assets/missingTexture.png");
    public static String get(){
        if (!file.exists()) {
            try {
                int size = 80;
                BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = image.createGraphics();

                int half = size / 2; // 40 pixels

                // Top-left: purple
                g.setColor(new Color(128, 0, 128)); // RGB purple
                g.fillRect(0, 0, half, half);

                // Top-right: black
                g.setColor(Color.BLACK);
                g.fillRect(half, 0, half, half);

                // Bottom-left: black
                g.setColor(Color.BLACK);
                g.fillRect(0, half, half, half);

                // Bottom-right: purple
                g.setColor(new Color(128, 0, 128));
                g.fillRect(half, half, half, half);

                g.dispose();

                ImageIO.write(image, "png", file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file.getPath();
    }
}
