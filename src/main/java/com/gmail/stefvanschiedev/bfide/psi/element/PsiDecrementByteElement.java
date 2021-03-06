package com.gmail.stefvanschiedev.bfide.psi.element;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiFactory;
import com.gmail.stefvanschiedev.bfide.psi.TextRange;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents a decrement byte instruction in BrainFuck
 */
public class PsiDecrementByteElement extends PsiElement {

    public PsiDecrementByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        cells[pointer]--;

        if (cells[pointer] < configuration.getMinCellValue())
            cells[pointer] = configuration.getMaxCellValue();

        return pointer;
    }

    @Override
    public String toString() {
        return "-";
    }

    public static class Factory implements PsiFactory<PsiDecrementByteElement> {

        public static final Factory INSTANCE = new Factory();

        private Factory() {}

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, List<PsiElement> holder) {
            if (!text.startsWith("-"))
                return -1;

            holder.add(new PsiDecrementByteElement(new TextRange(offset, offset + 1), parent));
            return 1;
        }
    }
}
