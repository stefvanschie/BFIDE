package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

/**
 * Represents an increment pointer instruction in BrainFuck
 */
public class PsiIncrementPointerElement extends PsiElement {

    public PsiIncrementPointerElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        pointer++;
    
        if (pointer >= cells.length)
            throw new IllegalStateException("Pointer outside of array bounds");
        
        return pointer;
    }

    @Override
    public String toString() {
        return ">";
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiIncrementPointerElement> {

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            if (!text.startsWith(">"))
                return -1;

            parent.addChild(new PsiIncrementPointerElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}