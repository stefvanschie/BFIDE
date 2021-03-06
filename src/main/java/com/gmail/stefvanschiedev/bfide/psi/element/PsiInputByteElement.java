package com.gmail.stefvanschiedev.bfide.psi.element;

import com.gmail.stefvanschiedev.bfide.execution.InstructionException;
import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiFactory;
import com.gmail.stefvanschiedev.bfide.psi.TextRange;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * Represents an input byte instruction in BrainFuck
 */
public class PsiInputByteElement extends PsiElement {

    public PsiInputByteElement(TextRange range, PsiElement parent) {
        super(range, parent);
    }

    @Override
    public int execute(long[] cells, int pointer, RunConfiguration configuration) throws InstructionException {
        try {
            int input = configuration.getInput().read();
            if (configuration.getMaxCellValue() < 255) {
                //The cell values are in the signed byte range,
                //therefore put the input in the signed byte range as well
                input -= 128;
            }

            cells[pointer] = input;
            return pointer;
        } catch (IOException e) {
            throw new InstructionException("Error while reading input", this, e);
        }
    }

    @Override
    public String toString() {
        return ",";
    }

    public static class Factory implements PsiFactory<PsiInputByteElement> {

        public static final Factory INSTANCE = new Factory();

        private Factory() {}

        @Override
        public int parse(String text, int offset, @Nullable PsiElement parent, List<PsiElement> holder) {
            if (!text.startsWith(","))
                return -1;

            holder.add(new PsiInputByteElement(new TextRange(offset, offset + 1), parent));
            return 1;
        }
    }
}
