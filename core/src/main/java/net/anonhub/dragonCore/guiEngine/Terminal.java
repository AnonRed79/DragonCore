package net.anonhub.dragonCore.guiEngine;

import net.anonhub.dragonCore.engine.Renders;

public class Terminal extends TextInput{
    private final TextDisplay terminalHistory;
    public Terminal(Renders renders) {
        super(renders);
        terminalHistory = new TextDisplay(renders, 'h');
        terminalHistory.setTitle("Terminal");
    }

    @Override
    public GlyphBuilder enter() {
        Commands.run(text.toString());
        terminalHistory.addLine(text);
        return super.enter();
    }

    public void draw() {
        terminalHistory.draw();
        super.draw(10, 20);
    }
}
