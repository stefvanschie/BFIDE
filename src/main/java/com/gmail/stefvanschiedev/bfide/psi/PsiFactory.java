package com.gmail.stefvanschiedev.bfide.psi;

import org.jetbrains.annotations.Nullable;

import java.util.Queue;

/**
 * A builder to build psi elements
 *
 * @param <T> the class it will build
 */
public interface PsiFactory<T extends PsiElement> {
    /**
     * Attempts to parse the text
     *
     * @param text the text to parse
     * @param offset the offset
     * @param parent the parent of the element being parsed or null, if the element is a top-level one
     * @param holder the holder of the element being parsed
     * @return the amount of characters removed from the start of the text or -1, if the text cannot be parsed
     */
    int parse(String text, int offset, @Nullable PsiElement parent, Queue<PsiElement> holder);
}