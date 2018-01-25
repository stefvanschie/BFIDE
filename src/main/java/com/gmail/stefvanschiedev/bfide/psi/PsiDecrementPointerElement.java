package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;

/**
 * Represents a decrement pointer instruction in BrainFuck
 */
public class PsiDecrementPointerElement extends PsiElement {

    public PsiDecrementPointerElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        pointer--;

        if (pointer < 0)
            throw new IllegalStateException("Pointer outside of array bounds");

        return pointer;
    }

    @Override
    public String toString() {
        return "<";
    }

    public static class Builder implements PsiBuilder<PsiDecrementPointerElement> {

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, Queue<PsiElement> holder) {
            if (!text.startsWith("<"))
                return -1;

            holder.add(new PsiDecrementPointerElement(new TextRange(offset, offset + 1), parent));
            return 1;
        }
    }
}