package com.gmail.stefvanschiedev.bfide.psi.builder;

import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

/**
 * A builder to build psi elements
 *
 * @param <T> the class it will build
 */
public interface PsiBuilder<T extends PsiElement> {

    /**
     * @return true if this builder can parse this, false otherwise
     */
    boolean isParsable(String text);

    /**
     * Parses the text
     *
     * @param text the text to parse
     * @return the amount of characters removed from the start of the text
     */
    int parse(String text, int offset, PsiElement parent);
}
