package com.gmail.stefvanschiedev.bfide.execution;

import com.gmail.stefvanschiedev.bfide.psi.PsiElement;

/**
 * An exception which happens while performing a BrainFuck instruction
 */
public class InstructionException extends Exception {
    private final PsiElement source;

    /**
     * @param message the details of the error
     * @param source the element which was being executed when the error occurred
     * @param cause the cause of the error
     */
    public InstructionException(String message, PsiElement source, Throwable cause) {
        super(message, cause);
        this.source = source;
    }

    /**
     * @param message the details of the error
     * @param source the element which was being executed when the error occurred
     */
    public InstructionException(String message, PsiElement source) {
        super(message);
        this.source = source;
    }

    public PsiElement getSource() {
        return source;
    }
}
