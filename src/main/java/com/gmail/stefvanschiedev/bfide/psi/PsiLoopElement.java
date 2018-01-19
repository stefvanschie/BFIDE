package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElementFactory;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

import java.util.regex.Pattern;

/**
 * Represent a looping structure in BrainFuck
 */
public class PsiLoopElement extends PsiElement {

    public PsiLoopElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (PsiElement element : getChildren())
            builder.append(element);

        return builder.append("]").toString();
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiLoopElement> {

        @Override
        public boolean isParsable(String text) {
            if (!text.startsWith("["))
                return false;

            int brackets = 0;

            for (char c : text.toCharArray()) {
                if (c == '[')
                    brackets++;
                else if (c == ']') {
                    brackets--;

                    if (brackets == 0)
                        break;
                }
            }

            return brackets == 0;
        }

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            //-1 because it will match the first one as well
            int brackets = 0;
            int length = 0;

            for (char c : text.toCharArray()) {
                length++;

                if (c == '[')
                    brackets++;
                else if (c == ']') {
                    brackets--;

                    if (brackets == 0) {
                        PsiLoopElement element = new PsiLoopElement(new TextRange(offset, length), parent);

                        PsiElementFactory.INSTANCE.parseText(text.substring(1, length - 1), offset + 1,
                                element);

                        parent.addChild(element);

                        break;
                    }
                }
            }

            return length;
        }
    }
}