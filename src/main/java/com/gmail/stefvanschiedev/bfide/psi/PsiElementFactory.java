package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.psi.element.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A thread safe factory for parsing BrainFuck code into a psi
 */
public class PsiElementFactory {

    private static final PsiFactory<?>[] BUILDERS = new PsiFactory<?>[] {
            PsiDecrementByteElement.Factory.INSTANCE,
            PsiDecrementPointerElement.Factory.INSTANCE,
            PsiIncrementByteElement.Factory.INSTANCE,
            PsiIncrementPointerElement.Factory.INSTANCE,
            PsiInputByteElement.Factory.INSTANCE,
            PsiLoopElement.Factory.INSTANCE,
            PsiOutputByteElement.Factory.INSTANCE
    };

    /**
     * Parses the text into the psi
     *
     * @param text the text to parse
     * @param offset the current offset of the text
     * @param parent the parent of the elements being parsed or null, if the element is top-level one
     * @return the newly parsed elements
     */
    @NotNull
    public static PsiElement[] parseText(String text, int offset, @Nullable PsiElement parent) {
        int prevLength = text.length();
        List<PsiElement> parsed = new ArrayList<>(20);

        while (!text.isEmpty()) {
            for (PsiFactory<?> builder : BUILDERS) {
                int length = builder.parse(text, offset, parent, parsed);
                if (length == -1)
                    continue;

                offset += text.substring(0, length).chars().filter(ch -> ch != '\r').count();
                text = text.substring(length);
                break;
            }

            //if we can't read this character, just skip it
            if (prevLength == text.length()) {
                if (text.charAt(0) != '\r')
                    offset++;

                text = text.substring(1);
            }

            prevLength = text.length();
        }

        return parsed.toArray(new PsiElement[parsed.size()]);
    }

    private PsiElementFactory() {}
}
