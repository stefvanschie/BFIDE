package com.gmail.stefvanschiedev.bfide.psi.util;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base class for all psi elements
 */
public abstract class PsiElement {

    /**
     * Parent element
     */
    private PsiElement parent;

    /**
     * Children elements
     */
    private PsiElement[] children;

    /**
     * The text range of this element
     */
    private TextRange range;

    /**
     * Create a new psi element
     *
     * @param range the text range
     * @param parent the parent element
     */
    public PsiElement(TextRange range, PsiElement parent) {
        this.range = range;
        this.parent = parent;

        this.children = new PsiElement[0];
    }

    /**
     * Adds a child to the children
     *
     * @param element the child to add
     */
    public void addChild(PsiElement element) {
        List<PsiElement> children = new ArrayList<>(Arrays.asList(this.children));
        children.add(element);
        this.children = children.toArray(new PsiElement[children.size()]);
    }

    /**
     * @return the array of children
     */
    public PsiElement[] getChildren() {
        return children;
    }

    /**
     * Specifies the behaviour when this piece of code is run.
     *
     * @param cells the cells (its content is mutable)
     * @param pointer the pointer
     * @param configuration the run configuration
     * @return the new pointer (which can be the same as the pointer parameter)
     */
    public abstract int execute(long[] cells, int pointer, RunConfiguration configuration);
}