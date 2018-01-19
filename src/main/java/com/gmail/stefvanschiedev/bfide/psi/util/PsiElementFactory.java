package com.gmail.stefvanschiedev.bfide.psi.util;

import com.gmail.stefvanschiedev.bfide.psi.*;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;

/**
 * A factory for parsing BrainFuck code into a psi
 */
public class PsiElementFactory {

    /**
     * The instance of this class
     */
    public static final PsiElementFactory INSTANCE = new PsiElementFactory();

    /**
     * An array of all possible builders
     */
    private final PsiBuilder<?>[] builders = new PsiBuilder<?>[] {
            new PsiCommentElement.Builder(),
            new PsiDecrementByteElement.Builder(),
            new PsiDecrementPointerElement.Builder(),
            new PsiIncrementByteElement.Builder(),
            new PsiIncrementPointerElement.Builder(),
            new PsiInputByteElement.Builder(),
            new PsiLoopElement.Builder(),
            new PsiOutputByteElement.Builder()
    };

    /**
     * Parses the text into the psi
     *
     * @param text the text to parse
     * @param offset the current offset of the text
     * @param parent the parent element
     */
    public void parseText(String text, int offset, PsiElement parent) {
        //safety catch for incorrect input
        String prevText = text;

        while (!text.isEmpty()) {
            for (PsiBuilder<?> builder : builders) {
                if (!builder.isParsable(text))
                    continue;

                int length = builder.parse(text, offset, parent);

                text = text.substring(length);
                offset += length;
                break;
            }

            if (prevText.equals(text))
                throw new IllegalArgumentException("Incorrect text, unable to parse");

            prevText = text;
        }
    }
}
