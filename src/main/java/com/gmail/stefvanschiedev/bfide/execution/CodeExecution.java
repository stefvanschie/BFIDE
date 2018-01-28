package com.gmail.stefvanschiedev.bfide.execution;

import com.gmail.stefvanschiedev.bfide.psi.PsiElement;

/**
 * The execution environment for this code. Single-use only: calling the
 * {@link #execute()} method more than once will result in an exception.
 * This class is <b>not</b> thread safe
 */
public class CodeExecution {

    private final long[] cells;
    private int pointer = 0;
    private final RunConfiguration configuration;
    private final PsiElement[] elements;
    private boolean executed;

    /**
     * Creates a new code execution environment
     *
     * @param configuration the configuration used for the execution
     * @param elements the elements from the source code
     * (the array is not copied, therefore it mustn't be changed)
     */
    public CodeExecution(RunConfiguration configuration, PsiElement[] elements) {
        cells = new long[configuration.getCellCount()];
        this.configuration = configuration;
        this.elements = elements;
    }

    /**
     * Executes the code
     */
    public void execute() throws InstructionException {
        if (executed)
            throw new IllegalStateException("The execute method can only be called once");

        executed = true;

        for (PsiElement child : elements)
            pointer = child.execute(cells, pointer, configuration);
    }
}