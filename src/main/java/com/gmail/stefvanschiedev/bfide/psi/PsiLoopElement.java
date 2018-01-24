package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElementFactory;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

/**
 * Represent a looping structure in BrainFuck
 */
public class PsiLoopElement extends PsiElement {

    public PsiLoopElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        while (cells[pointer] != 0) {
            for (PsiElement child : getChildren())
                pointer = child.execute(cells, pointer, configuration);
        }

        return pointer;
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
        public int parse(String text, int offset, PsiElement parent) {
            if (!text.startsWith("["))
                return -1;

            int brackets = 0;
            for (int length = 1; length <= text.length(); length++) {
                char character = text.charAt(length - 1);

                if (character == '[') {
                    brackets++;
                    continue;
                }

                if (character == ']')
                    brackets--;
                else
                    continue;

                if (brackets != 0)
                    continue;

                PsiLoopElement element = new PsiLoopElement(new TextRange(offset, length), parent);
                PsiElementFactory.INSTANCE.parseText(text.substring(1, length - 1), offset + 1, element);
                parent.addChild(element);
                return length;
            }

            return -1;
        }
    }
}