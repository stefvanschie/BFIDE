package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

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

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiDecrementPointerElement> {

        @Override
        public boolean isParsable(String text) {
            return text.startsWith("<");
        }

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            parent.addChild(new PsiDecrementPointerElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}