package com.gmail.stefvanschiedev.bfide.psi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a BrainFuck file which belongs to the PSI
 */
public class PsiFile extends File {

    private PsiElement[] children;

    public PsiFile(@NotNull File file) {
        super(file.getPath());

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.children = PsiElementFactory.parseText(new String(bytes), 0, null);
    }

    /**
     * Parses the new text again and updates the elements
     *
     * @param text the new text
     */
    public void parse(String text) {
        this.children = PsiElementFactory.parseText(text, 0, null);
    }

    /**
     * Returns all children, fully nested
     *
     * @return a list of elements
     */
    public List<PsiElement> allChildren() {
        List<PsiElement> elements = new ArrayList<>();

        for (PsiElement child : children) {
            elements.add(child);

            if (child instanceof PsiLoopElement)
                elements.addAll(allChildren((PsiLoopElement) child));
        }

        return elements;
    }

    /**
     * Returns all children, fully nested
     *
     * @param parent the paren element to start from
     * @return a list of elements
     */
    private List<PsiElement> allChildren(PsiLoopElement parent) {
        List<PsiElement> elements = new ArrayList<>();

        for (PsiElement element : parent.getChildren()) {
            elements.add(element);

            if (element instanceof PsiLoopElement)
                elements.addAll(allChildren((PsiLoopElement) element));
        }

        return elements;
    }
}
