package com.gmail.stefvanschiedev.bfide;

import com.gmail.stefvanschiedev.bfide.execution.CodeExecution;
import com.gmail.stefvanschiedev.bfide.execution.InstructionException;
import com.gmail.stefvanschiedev.bfide.execution.RunConfiguration;
import com.gmail.stefvanschiedev.bfide.psi.PsiElementFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/**
 * Tests the various BrainFuck instructions
 */
public class PsiElementInstructionTest {

    private final Charset charset = Charset.forName("ASCII");
    private final byte[] inputBytes = new byte[2];
    private final ByteArrayInputStream input = new ByteArrayInputStream(inputBytes);
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final RunConfiguration configuration = new RunConfiguration.Builder()
            .setCellValueRange(RunConfiguration.CellValueRange.UNSIGNED_BYTE)
            .setInput(input)
            .setOutput(output)
            .build();

    /**
     * @param code the code to execute
     * @return the output of the code (can be an empty string, but won't null)
     */
    private String execute(String code) {
        try {
            new CodeExecution(configuration,
                    PsiElementFactory.parseText(code, 0, null))
                    .execute();
        } catch (InstructionException e) {
            throw new RuntimeException(e);
        }

        String string = new String(output.toByteArray(), charset);
        output.reset();
        return string;
    }

    @Test
    public void testHelloWorld() {
        Assert.assertEquals("Hello World!", execute("++++++++[>++++[>++>+++>+++>+<<<<-]" +
                ">+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+."));
    }

    @Test
    public void testInput() {
        inputBytes[0] = 'b';
        inputBytes[1] = 'f';
        Assert.assertEquals("bf", execute(",.,."));
    }
}