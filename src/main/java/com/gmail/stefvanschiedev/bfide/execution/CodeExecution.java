package com.gmail.stefvanschiedev.bfide.execution;

import com.gmail.stefvanschiedev.bfide.psi.PsiLoopElement;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;

import java.util.Scanner;

/**
 * The execution environment for this code
 */
public class CodeExecution {

    /**
     * The cells
     */
    private long[] cells;

    /**
     * The pointer
     */
    private int pointer = 0;

    /**
     * The run configuration
     */
    private RunConfiguration configuration;

    /**
     * The holder of the elements (that sounds like a movie title)
     */
    private PsiElement holder;

    /**
     * Creates a new code execution environment
     *
     * @param configuration {@link #configuration}
     */
    public CodeExecution(RunConfiguration configuration, PsiElement holder) {
        this.cells = new long[configuration.getArraySize()];
        this.configuration = configuration;
        this.holder = holder;
    }

    /**
     * Executes the code
     */
    public void execute() {
        for (PsiElement child : holder.getChildren())
            executeElement(child);
    }

    /**
     * Executes an individual element. If you want to run the entire code use {@link #execute()}
     *
     * @param element the element to execute
     */
    private void executeElement(PsiElement element) {
        if (element instanceof PsiLoopElement) {
            while (cells[pointer] != 0) {
                for (PsiElement child : element.getChildren())
                    executeElement(child);
            }

            return;
        }

        element.execute(this);
    }

    /**
     * Decrements the byte currently pointed at by the {@link #pointer}
     */
    public void decrementByte() {
        cells[pointer]--;

        if (cells[pointer] < configuration.getCellSizeMin())
            cells[pointer] = configuration.getCellSizeMax();
    }

    /**
     * Increments the byte currently pointed at by the {@link #pointer}
     */
    public void incrementByte() {
        cells[pointer]++;

        if (cells[pointer] > configuration.getCellSizeMax())
            cells[pointer] = configuration.getCellSizeMin();
    }

    /**
     * Decrements the pointer
     */
    public void decrementPointer() {
        pointer--;

        if (pointer < 0)
            throw new IllegalStateException("Pointer outside of array bounds");
    }

    /**
     * Increments the pointer
     */
    public void incrementPointer() {
        pointer++;

        if (pointer == cells.length)
            throw new IllegalStateException("Pointer outside of array bounds");
    }

    /**
     * Inputs a byte
     */
    public void inputByte() {
        cells[pointer] = new Scanner(System.in).next(".").charAt(0);
    }

    /**
     * Outputs a byte
     */
    public void outputByte() {
        System.out.print((char) cells[pointer]);
    }
}