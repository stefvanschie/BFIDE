package com.gmail.stefvanschiedev.bfide.psi;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.utils.TextRange;
import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;
import java.util.Queue;

/**
 * Represents an output byte instruction in BrainFuck
 */
public class PsiOutputByteElement extends PsiElement {

    public PsiOutputByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) {
        PrintStream output = configuration.getOutput();
        output.print((char)cells[pointer]);
        output.flush();
        return pointer;
    }

    @Override
    public String toString() {
        return ".";
    }

    public static class Builder implements PsiBuilder<PsiOutputByteElement> {

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, Queue<PsiElement> holder) {
            if (!text.startsWith("."))
                return -1;

            holder.add(new PsiOutputByteElement(new TextRange(offset, offset + 1), parent));
            return 1;
        }
    }
}