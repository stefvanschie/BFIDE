package com.gmail.stefvanschiedev.bfide.execution;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * A configuration on how the code should be executed
 */
public class RunConfiguration {

    private final CellValueRange cellValueRange;
    private final int cellCount;
    private final InputStream input;
    private final PrintStream output;

    /**
     * Create a new configuration
     *
     * @param cellValueRange the range of the cells' valid values
     * @param cellCount the count of cells
     * @param input the input for the input operation
     * @param output the output for the output operation (auto-flush isn't required)
     */
    public RunConfiguration(CellValueRange cellValueRange, int cellCount,
                            InputStream input, PrintStream output) {
        this.cellValueRange = cellValueRange;
        this.cellCount = cellCount;
        this.input = input;
        this.output = output;
    }

    /**
     * @return the minimum cell value (0 by default)
     */
    public long getMinCellValue() {
        return cellValueRange.min;
    }

    /**
     * @return the maximum cell value (255 by default)
     */
    public long getMaxCellValue() {
        return cellValueRange.max;
    }

    /**
     * @return the count of cells (30,000 by default)
     */
    public int getCellCount() {
        return cellCount;
    }

    /**
     * @return the input for the input operation (System.in by default)
     */
    public InputStream getInput() {
        return input;
    }

    /**
     * @return the output for the output operation (System.out by default)
     */
    public PrintStream getOutput() {
        return output;
    }

    /**
     * A builder to build run configurations
     */
    public static class Builder {
        private CellValueRange cellValueRange = CellValueRange.UNSIGNED_BYTE;
        private int cellCount = 30000;
        private InputStream input = System.in;
        private PrintStream output = System.out;

        public RunConfiguration build() {
            return new RunConfiguration(cellValueRange, cellCount, input, output);
        }

        public Builder setCellValueRange(CellValueRange cellValueRange) {
            this.cellValueRange = cellValueRange;
            return this;
        }

        public Builder setCellCount(int cellCount) {
            this.cellCount = cellCount;
            return this;
        }

        public Builder setInput(InputStream input) {
            this.input = input;
            return this;
        }

        public Builder setOutput(PrintStream output) {
            this.output = output;
            return this;
        }
    }

    /**
     * The range of the cells' valid values
     */
    public enum CellValueRange {
        UNSIGNED_BYTE(0, Byte.MAX_VALUE - Byte.MIN_VALUE),
        SIGNED_BYTE(Byte.MIN_VALUE, Byte.MAX_VALUE),
        UNSIGNED_SHORT(0, Short.MAX_VALUE - Short.MIN_VALUE),
        SIGNED_SHORT(Short.MIN_VALUE, Short.MAX_VALUE),
        UNSIGNED_INT(0, (long)Integer.MAX_VALUE - Integer.MIN_VALUE),
        SIGNED_INT(Integer.MIN_VALUE, Integer.MAX_VALUE),
        SIGNED_LONG(Long.MIN_VALUE, Long.MAX_VALUE);

        private final long min, max;

        CellValueRange(long min, long max) {
            this.min = min;
            this.max = max;
        }

        public long getMin() {
            return min;
        }

        public long getMax() {
            return max;
        }
    }
}