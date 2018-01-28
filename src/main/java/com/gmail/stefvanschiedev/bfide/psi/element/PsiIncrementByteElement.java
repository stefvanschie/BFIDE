package com.gmail.stefvanschiedev.bfide.psi.element;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiFactory;
import com.gmail.stefvanschiedev.bfide.psi.TextRange;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;

/**
 * Represent an increment byte instruction in BrainFuck
 */
public class PsiIncrementByteElement extends PsiElement {

    public PsiIncrementByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        cells[pointer]++;

        if (cells[pointer] > configuration.getMaxCellValue())
            cells[pointer] = configuration.getMinCellValue();

        return pointer;
    }

    @Override
    public String toString() {
        return "+";
    }

    public static class Factory implements PsiFactory<PsiIncrementByteElement> {

        public static final Factory INSTANCE = new Factory();

        private Factory() {}

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, Queue<PsiElement> holder) {
            if (!text.startsWith("+"))
                return -1;

            holder.add(new PsiIncrementByteElement(new TextRange(offset, offset + 1), parent));
            return 1;
        }
    }
}