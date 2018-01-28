package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.psi.element.*;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A thread safe factory for parsing BrainFuck code into a psi
 */
public class PsiElementFactory {

    private static final PsiFactory<?>[] BUILDERS = new PsiFactory<?>[] {
            PsiCommentElement.Factory.INSTANCE,
            PsiDecrementByteElement.Factory.INSTANCE,
            PsiDecrementPointerElement.Factory.INSTANCE,
            PsiIncrementByteElement.Factory.INSTANCE,
            PsiIncrementPointerElement.Factory.INSTANCE,
            PsiInputByteElement.Factory.INSTANCE,
            PsiLoopElement.Factory.INSTANCE,
            PsiOutputByteElement.Factory.INSTANCE
    };

    private PsiElementFactory() {}

    /**
     * Parses the text into the psi
     *
     * @param text the text to parse
     * @param offset the current offset of the text
     * @param parent the parent of the elements being parsed or null, if the element is top-level one
     * @return the newly parsed elements
     */
    public static PsiElement[] parseText(String text, int offset, @Nullable PsiElement parent) {
        int prevLength = text.length();
        Queue<PsiElement> parsed = new LinkedList<>();

        while (!text.isEmpty()) {
            for (PsiFactory<?> builder : BUILDERS) {
                int length = builder.parse(text, offset, parent, parsed);
                if (length == -1)
                    continue;

                text = text.substring(length);
                offset += length;
                break;
            }

            if (prevLength == text.length())
                throw new IllegalArgumentException("Incorrect text, unable to parse");

            prevLength = text.length();
        }

        return parsed.toArray(new PsiElement[parsed.size()]);
    }
}