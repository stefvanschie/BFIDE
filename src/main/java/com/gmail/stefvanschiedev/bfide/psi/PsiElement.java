package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for all psi elements
 */
public abstract class PsiElement {

    private final TextRange range;
    @Nullable private final PsiElement parent;

    /**
     * Create a new psi element
     *
     * @param range the text range specifying this element's position
     * @param parent the parent element or null, if this is a top-level element
     */
    public PsiElement(TextRange range, @Nullable PsiElement parent) {
        this.range = range;
        this.parent = parent;
    }

    /**
     * Specifies the behaviour when this piece of code is run
     *
     * @param cells the cells (its content is mutable)
     * @param pointer the pointer
     * @param configuration the run configuration
     * @return the new pointer (which can be the same as the pointer parameter)
     */
    public abstract int execute(long[] cells, int pointer, RunConfiguration configuration);
}