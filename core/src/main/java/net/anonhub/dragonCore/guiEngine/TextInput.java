package net.anonhub.dragonCore.guiEngine;

import net.anonhub.dragonCore.engine.Renders;

public class TextInput {
    protected GlyphBuilder text;
    protected Renders renders;
    protected int currentIndex;
    protected int lineIndex;
    protected TextDisplay history;

    public TextInput(Renders renders) {
        this.renders = renders;
        this.text = new GlyphBuilder(renders);
        this.currentIndex = 0;
        this.lineIndex = 0;
        history = null;
    }

    public TextInput(Renders renders, TextDisplay history) {
        this.renders = renders;
        this.text = new GlyphBuilder(renders);
        this.currentIndex = 0;
        this.lineIndex = 0;
        this.history = history;
    }

    /**
     * append the text with the specified char then runs {@link #increaseIndex()}
     * @param character the character to append
     * @return this object
     */
    public TextInput addCharacter(char character) {
        text.insert(currentIndex, character);
        increaseIndex();
        return this;
    }

    /**
     * removes the char before the currently selected one and runs {@link #decreaseIndex()}
     * @return this object
     */
    public TextInput backspace() {
        decreaseIndex();
        if (text.length() > 0) {
            text.deleteCharAt(currentIndex);
        }
        return this;
    }

    /**
     * removes the currently selected char
     * @return this object
     */
    public TextInput delete() {
        if (text.length() > 0 && currentIndex < text.length()) {
            text.deleteCharAt(currentIndex);
        }
        return this;
    }

    public GlyphBuilder enter() {
        GlyphBuilder text = this.text;
        this.text.clear();
        currentIndex = 0;
        return text;
    }

    /**
     * Renders the text in the game window at the specified coordinates with a cursor
     */
    public void draw(float x, float y) {
        text.draw(x, y);
    }

    /**
     * @return the number of characters in the text
     */
    public int length() {
        return text.length();
    }

    /**
     * sets the selected char. <br> If it's set to greater than the last index it auto set to the last index, <br>if it's set to before the first index it auto sets to the first index.
     * @param index the new index
     * @return the new index
     */
    public int setIndex(int index) {
        return index;
    }

    /**
     * @return the index of the current selected char
     */
    public int getCurrentIndex() {
        return 0;
    }

    /**
     * Increase the {@code currentIndex} by a designated amount
     * @param amount the amount the {@code currentIndex} in increased by
     * @return the new index
     */
    public int increaseIndex(int amount) {
        currentIndex+=amount;
        if (currentIndex > text.length()) {
            currentIndex = text.length();
        }
        return currentIndex;
    }

    /**
     * Increase the {@code currentIndex} by 1
     * @return the new index
     */
    public int increaseIndex() {
        return increaseIndex(1);
    }

    /**
     * Decrease the {@code currentIndex} by a designated amount
     * @param amount the amount the {@code currentIndex} in decreased by
     * @return the new index
     */
    public int decreaseIndex(int amount) {
        currentIndex-=amount;
        if (currentIndex < 0) {
            currentIndex = 0;
        }
        return currentIndex;
    }

    /**
     * Decrease the {@code currentIndex} by 1
     * @return the new index
     */
    public int decreaseIndex() {
        return decreaseIndex(1);
    }




    @Override
    public String toString() {
        return "text: "+text.toString()+
            "\nIndex: "+currentIndex;
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
