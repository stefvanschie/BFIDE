package com.gmail.stefvanschiedev.bfide.utils;

/**
 * Specifies a range of text inside a file
 */
public class TextRange {

    /**
     * The text offset
     */
    private int offset;

    /**
     * The length of this text range
     */
    private int length;

    /**
     * Create a new text range
     *
     * @param offset {@link #offset}
     * @param length (@link #length)
     */
    public TextRange(int offset, int length) {
        this.offset = offset;
        this.length = length;
    }

    /**
     * @return the offset of this range
     */
    public int getOffset() {
        return offset;
    }
}