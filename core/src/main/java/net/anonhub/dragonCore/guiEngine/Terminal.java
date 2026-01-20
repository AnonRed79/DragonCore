package net.anonhub.dragonCore.guiEngine;

import net.anonhub.dragonCore.engine.Renders;

public class Terminal extends TextInput{
    protected final TextDisplay terminalHistory;
    protected int lineIndex;

    public Terminal(Renders renders) {
        super(renders);
        terminalHistory = new TextDisplay(renders, 'h');
        terminalHistory.setTitle("Terminal");
        lineIndex = 0;
    }

    @Override
    public GlyphBuilder enter() {
        if (text.startWith('/')) {
            Commands.run(text.substring(1).toString());
        }
        terminalHistory.addLine(text);
        lineIndex ++;
        return super.enter();
    }

    public void draw() {
        terminalHistory.draw();
        super.draw(10, 20);
    }


    public int decreaseLineIndex(int amount) {
        if (history==null) {
            return -1;
        }
        lineIndex = amount;
        if (lineIndex < 0) {
            lineIndex = 0;
        }
        return lineIndex;
    }

    public int decreaseLineIndex() {
        return decreaseLineIndex(1);
    }

    public int increaseLineIndex(int amount) {
        if (history==null) {
            return -1;
        }
        lineIndex = amount;
        if (lineIndex > history.size()) {
            lineIndex = history.size();
        }
        return lineIndex;
    }

    public int increaseLineIndex() {
        return increaseLineIndex(1);
    }
}
