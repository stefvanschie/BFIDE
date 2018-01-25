package com.gmail.stefvanschiedev.bfide.execution;

import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiElementHolder;

/**
 * The execution environment for this code
 */
public class CodeExecution {

    private final long[] cells;
    private int pointer = 0;
    private final RunConfiguration configuration;
    private final PsiElementHolder holder;

    /**
     * Creates a new code execution environment
     *
     * @param configuration {@link #configuration}
     */
    public CodeExecution(RunConfiguration configuration, PsiElementHolder holder) {
        cells = new long[configuration.getCellCount()];
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