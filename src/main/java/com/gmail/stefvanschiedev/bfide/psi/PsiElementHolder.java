package com.gmail.stefvanschiedev.bfide.psi;

/**
 * An element which can hold other elements (those are called this element's children)
 */
public interface PsiElementHolder {

    /**
     * Sets the children of this element holder. This method should only be called once,
     * after all of the children have been created.
     * @param children the children to hold
     */
    void setChildren(PsiElement[] children);

    /**
     * @return an array containing the children; none of the elements are null.
     * The array is not a copy, therefore it shouldn't be modified
     */
    PsiElement[] getChildren();
}