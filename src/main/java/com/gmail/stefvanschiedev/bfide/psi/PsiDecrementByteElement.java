package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

/**
 * Represents a decrement byte instruction in BrainFuck.
 */
public class PsiDecrementByteElement extends PsiElement {

    public PsiDecrementByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        cells[pointer]--;
    
        if (cells[pointer] < configuration.getCellSizeMin())
            cells[pointer] = configuration.getCellSizeMax();
        
        return pointer;
    }

    @Override
    public String toString() {
        return "-";
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiDecrementByteElement> {

        @Override
        public boolean isParsable(String text) {
            return text.startsWith("-");
        }

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            parent.addChild(new PsiDecrementByteElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}