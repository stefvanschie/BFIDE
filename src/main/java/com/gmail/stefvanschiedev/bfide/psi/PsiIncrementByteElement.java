package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

/**
 * Represent an increment byte instruction in BrainFuck
 */
public class PsiIncrementByteElement extends PsiElement {

    public PsiIncrementByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public String toString() {
        return "+";
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiIncrementByteElement> {

        @Override
        public boolean isParsable(String text) {
            return text.startsWith("+");
        }

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            parent.addChild(new PsiIncrementByteElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}