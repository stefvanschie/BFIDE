package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.builder.PsiBuilder;
import com.gmail.stefvanschiedev.bfide.psi.util.PsiElement;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;

import java.util.Scanner;

/**
 * Represents an input byte instruction in BrainFuck
 */
public class PsiInputByteElement extends PsiElement {

    public PsiInputByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        cells[pointer] = new Scanner(System.in).next(".").charAt(0);
        return pointer;
    }

    @Override
    public String toString() {
        return ",";
    }

    /**
     * A builder for this element
     */
    public static class Builder implements PsiBuilder<PsiInputByteElement> {

        @Override
        public int parse(String text, int offset, PsiElement parent) {
            if (!text.startsWith(","))
                return -1;

            parent.addChild(new PsiInputByteElement(new TextRange(offset, offset + 1), parent));

            return 1;
        }
    }
}