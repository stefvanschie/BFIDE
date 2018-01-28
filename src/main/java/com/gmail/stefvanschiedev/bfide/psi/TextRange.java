package com.gmail.stefvanschiedev.bfide.psi;

/**
 * Specifies a piece of text by its starting position and its length
 */
public class TextRange {
    private final int start, length;

    public TextRange(int start, int length) {
        this.start = start;
        this.length = length;
    }

    /**
     * @return the position at which the text starts
     */
    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }
}