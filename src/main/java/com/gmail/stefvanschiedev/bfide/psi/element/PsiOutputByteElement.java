package com.gmail.stefvanschiedev.bfide.psi.element;

import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiFactory;
import com.gmail.stefvanschiedev.bfide.psi.TextRange;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStream;
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
        long value = cells[pointer];
        if (configuration.getMaxCellValue() < 255) {
            //The cell values are in the signed byte range,
            //therefore move the output into unsigned byte range
            value += 128;
        } else if (configuration.getMaxCellValue() > 255)
            value %= 256;

        try {
            OutputStream output = configuration.getOutput();
            output.write((int)value);
            output.flush();
            return pointer;
        } catch (IOException e) {
            throw new RuntimeException("Error while writing output", e);
        }
    }

    @Override
    public String toString() {
        return ".";
    }

    public static class Factory implements PsiFactory<PsiOutputByteElement> {

        public static final Factory INSTANCE = new Factory();

        private Factory() {}

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, Queue<PsiElement> holder) {
            if (!text.startsWith("."))
                return -1;

            holder.add(new PsiOutputByteElement(new TextRange(offset, offset + 1), parent));
            return 1;
        }
    }
}