package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.CodeExecution;
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
    public void execute(CodeExecution execution) {
        execution.incrementPointer();
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
        public boolean isParsable(String text) {
            return text.startsWith(">");
        }

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            parent.addChild(new PsiIncrementPointerElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}