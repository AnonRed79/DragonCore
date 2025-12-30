package net.anonhub.dragonCore.guiEngine;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import net.anonhub.dragonCore.engine.Renders;

/**
 * A combination of a StringBuilder and GlyphLayout
 */
public class GlyphBuilder{
    private StringBuilder text = new StringBuilder();
    private Renders renders;

    public GlyphBuilder(Renders renders, char character) {
        text.append(character);
        this.renders = renders;
    }
    public GlyphBuilder(Renders renders, String str) {
        text.append(str);
        this.renders = renders;
    }
    public GlyphBuilder(Renders renders, StringBuilder str) {
        text.append(str);
        this.renders = renders;
    }
    public GlyphBuilder(Renders renders) {
        this.renders = renders;
    }

    public GlyphBuilder(Renders renders, GlyphBuilder glyphBuilder) {
        this.renders = renders;
        this.text = glyphBuilder.text;
    }

    /**
     * cuts a GlyphBuilder out of the current one
     * @param start starting index of the substring
     * @return a new GlyphBuilder that starts at the index <code>start</code>
     */
    public GlyphBuilder substring(int start) {
        return new GlyphBuilder(renders, text.substring(start));
    }

    /**
     * cuts a GlyphBuilder out of the current one
     * @param start starting index of the substring (inclusive)
     * @param end end index of the substring (exclusive)
     * @return a new GlyphBuilder that starts at the index <code>start</code>
     */
    public GlyphBuilder substring(int start, int end) {
        return new GlyphBuilder(renders, text.substring(start, end));
    }

    /**
     * @return the total width of the GlyphBuilder text with its current font
     */
    public float getWidth() {
        return new GlyphLayout(renders.font, text).width;
    }

    /**
     * renders the GlyphBuilder text in the game window
     * @param x distance from the left side of the window in pixels
     * @param y distance from the bottom of the window in pixels
     */
    public void draw(float x, float y) {
        renders.batch.begin();
        renders.batch.setProjectionMatrix(renders.camera.combined);
        renders.font.draw(renders.batch, text, x, y);
        renders.batch.end();
    }

    /**
     * @return number of characters in the GlyphBuilder
     */
    public int length() {
        return text.length();
    }


    /**
     * add a character at a specific index in the GlyphBuilder
     * @param charIndex index to add character at
     * @param character character tot add
     * @return this GlyphBuilder after the character is added
     */
    public GlyphBuilder insert(int charIndex, char character) throws IndexOutOfBoundsException{
        text.insert(charIndex, character);
        return this;
    }

    /**
     * add a character at the end of the GlyphBuilder
     * @param obj index to add character at
     * @return this GlyphBuilder after the character is added
     */
    public GlyphBuilder append(Object obj) {
        text.append(obj);
        return this;
    }

    /**
     * remove characters within a span of the GlyphBuilder
     * @param start index to start at (inclusive)
     * @param end index to stop at (exclusive)
     * @return this GlyphBuilder without the specified span
     * @throws     StringIndexOutOfBoundsException  if {@code start}
     *             is negative, greater than {@code length()}, or
     *             greater than {@code end}.
     */
    public GlyphBuilder delete(int start, int end) throws IndexOutOfBoundsException{
        if (start < 0 || start > length() || start > end) {
            throw new IndexOutOfBoundsException();
        }
        text.delete(start, end);
        return this;
    }

    /**
     * remove a specific character of the GlyphBuilder
     * @param index index to remove
     * @return this GlyphBuilder without the specified character
     * @throws     IndexOutOfBoundsException  if {@code index} is
     *             negative or greater than or equal to {@code length()}.
     */
    public GlyphBuilder deleteCharAt(int index) throws IndexOutOfBoundsException{
        if (index >= length()) {
            throw new IndexOutOfBoundsException("Length: "+length()+" Index: "+index);
        }
        text.deleteCharAt(index);
        return this;
    }

    /**
     * sets the text to the object
     * @param obj what set the text to
     * @return this GlyphBuilder with the new text
     */
    public GlyphBuilder setText(Object obj) {
        text = new StringBuilder(obj.toString());
        return this;
    }

    /**
     * Returns the {@code char} value in this sequence at the specified index.
     *
     * @param      index   the index of the desired {@code char} value.
     * @return     the {@code char} value at the specified index.
     * @throws     IndexOutOfBoundsException  if {@code index} is
     *             negative or greater than or equal to {@code length()}.
     */
    public char charAt(int index) throws IndexOutOfBoundsException{
        if (length() >= index) {
            throw new IndexOutOfBoundsException();
        }
        return text.charAt(index);
    }

    @Override
    public String toString() {
        return text.toString();
    }

    public GlyphBuilder clear() {
        text.delete(0, length());
        return this;
    }
}
