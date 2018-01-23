package com.gmail.stefvanschiedev.bfide.execution;

import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;

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
            pointer = child.execute(cells, pointer, configuration);
    }
}