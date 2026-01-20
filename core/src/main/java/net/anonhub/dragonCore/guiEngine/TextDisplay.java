package net.anonhub.dragonCore.guiEngine;

import net.anonhub.dragonCore.engine.Renders;

import java.util.ArrayList;

/**
 * TextDisplay displays text in the game window in the top left corner
 * */
public class TextDisplay {
    protected final ArrayList<GlyphBuilder> lines = new ArrayList<>();
    protected final GlyphBuilder title;
    protected Renders renders;
    protected int maxLines = 5;
    protected char mode;


    public TextDisplay(Renders renders) {
        this.renders = renders;
        this.title = new GlyphBuilder(renders);
    }

    /**
     * h = history: displays in order from bottom to top
     * <br>
     * w = writing: displays in order form top to bottom
     * */
    public TextDisplay(Renders renders, char mode) {
        this.renders = renders;
        this.title = new GlyphBuilder(renders);
        this.mode = mode;
        ArrayList<Character> validModes = new ArrayList<>();
        validModes.add('h');
        validModes.add('w');
        if (!validModes.contains(mode)) {
            throw new IllegalArgumentException("Unknown TextDisplay mode \""+mode+"\"");
        }
    }

    public void draw() {
        float[] location = {10, 40};

        for (int i = 0; i < Math.min(maxLines, lines.size()); i++) {
            lines.get((lines.size()-1)-i).draw(location[0], location[1]+(i*20));
        }
    }


    /**
     * append a line of text to the text display
     * <br>
     * @param line object converted to convert a string
     * */
    public void addLine(Object line) {
        lines.add(new GlyphBuilder(renders, line.toString()));
    }
    /**
     * adds a blank line of text to the text display
     * */
    public void addLine() {
        lines.add(new GlyphBuilder(renders, ""));
    }
    /**
     * Removes a specific GlyphBuilder Line form the text display
     * @param line GlyphBuilder to remove
     */
    public void removeLine(GlyphBuilder line) {
        lines.remove(line);
    }
    /**
     * Removes a specific GlyphBuilder Line form the text display
     * @param line index of line to remove
     */
    public void removeLine(int line) {
        lines.remove(line);
    }

    /**
     * clears on lines from the text display
     */
    public void clearLines() {
        lines.clear();
    }
    /**
     * Sets the title of the text display to be rendered at the top
     * @param obj object that's set as the title
     */
    public void setTitle(Object obj) {
        this.title.setText(obj);
    }

    public void setMaxLines(int maxLines) {
        if (maxLines < 1) {
            throw new IllegalArgumentException("max lines must be at least 1");
        }
        this.maxLines = maxLines;
    }

    public int size() {
        return lines.size();
    }
}
