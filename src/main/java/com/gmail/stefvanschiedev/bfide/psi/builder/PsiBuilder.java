package com.gmail.stefvanschiedev.bfide.psi.builder;

import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;

/**
 * A builder to build psi elements
 *
 * @param <T> the class it will build
 */
public interface PsiBuilder<T extends PsiElement> {
    /**
     * Attempts to parse the text
     *
     * @param text the text to parse
     * @param offset the offset
     * @param parent the parent of the element being parsed
     * @return the amount of characters removed from the start of the text or -1, if the text cannot be parsed
     */
    int parse(String text, int offset, PsiElement parent);
}
