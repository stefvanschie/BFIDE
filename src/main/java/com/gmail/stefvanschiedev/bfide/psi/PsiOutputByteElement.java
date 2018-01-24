package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

/**
 * Represents an output byte instruction in BrainFuck
 */
public class PsiOutputByteElement extends PsiElement {

    public PsiOutputByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        System.out.print((char) cells[pointer]);
        return pointer;
    }

    @Override
    public String toString() {
        return ".";
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiOutputByteElement> {

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            if (!text.startsWith("."))
                return -1;

            parent.addChild(new PsiOutputByteElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}