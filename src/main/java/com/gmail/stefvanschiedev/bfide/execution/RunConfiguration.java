package com.gmail.stefvanschiedev.bfide.execution;

/**
 * A configuration on how the code should be executed
 */
public class RunConfiguration {

    /**
     * The maximum and minimum values a cell can hold, defaults to 8-bit.
     */
    private long cellSizeMax = 127, cellSizeMin = -128;

    /**
     * The array size, defaults to 30,000.
     */
    private int arraySize = 30000;

    /**
     * @return the array size
     */
    public int getArraySize() {
        return arraySize;
    }

    /**
     * @return the maximum cell size
     */
    public long getCellSizeMax() {
        return cellSizeMax;
    }

    /**
     * @return the minimum cell size
     */
    public long getCellSizeMin() {
        return cellSizeMin;
    }
}