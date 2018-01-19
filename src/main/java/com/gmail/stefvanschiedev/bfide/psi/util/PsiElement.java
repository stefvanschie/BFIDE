package com.gmail.stefvanschiedev.bfide.psi.util;

import com.gmail.stefvanschiedev.bfide.execution.CodeExecution;
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
        List<PsiElement> childs = new ArrayList<>(Arrays.asList(children));
        childs.add(element);
        children = childs.toArray(new PsiElement[childs.size()]);
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
     * @param execution the code execution environment
     */
    public abstract void execute(CodeExecution execution);
}