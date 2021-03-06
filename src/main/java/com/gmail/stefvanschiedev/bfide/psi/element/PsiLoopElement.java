package com.gmail.stefvanschiedev.bfide.psi.element;

import com.gmail.stefvanschiedev.bfide.execution.InstructionException;
import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiElementFactory;
import com.gmail.stefvanschiedev.bfide.psi.PsiFactory;
import com.gmail.stefvanschiedev.bfide.psi.TextRange;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represent a looping structure in BrainFuck
 */
public class PsiLoopElement extends PsiElement {

    private PsiElement[] children;

    public PsiLoopElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) throws InstructionException {
        while (cells[pointer] != 0) {
            for (PsiElement child : children)
                pointer = child.execute(cells, pointer, configuration);
        }

        return pointer;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (PsiElement element : children)
            builder.append(element);

        return builder.append("]").toString();
    }

    public PsiElement[] getChildren() {
        return children;
    }

    public static class Factory implements PsiFactory<PsiLoopElement> {

        public static final Factory INSTANCE = new Factory();

        private Factory() {}

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, List<PsiElement> holder) {
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

                int actualLength = (int) text.substring(1, length - 1).chars().filter(ch -> ch != '\r').count();

                PsiLoopElement element = new PsiLoopElement(new TextRange(offset, offset + actualLength), parent);
                holder.add(element);

                element.children = PsiElementFactory.parseText(text.substring(1, length - 1),
                        offset + 1, element);
                return actualLength;
            }

            return -1;
        }
    }
}
