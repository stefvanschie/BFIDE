package com.gmail.stefvanschiedev.bfide.file;

import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiElementFactory;
import com.gmail.stefvanschiedev.bfide.psi.element.PsiLoopElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a BrainFuck file which belongs to the PSI
 */
public class PsiFile extends File {

    /**
     * Loads an existing file and handles it like it contains BrainFuck code
     *
     * @param file the file to load
     */
    @Nullable
    @Contract(pure = true)
    public static PsiFile load(File file) {
        if (!file.isFile())
            return null;

        return new PsiFile(file);
    }

    @Nullable private PsiElement[] elements;

    private PsiFile(File file) {
        super(file.getPath());

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.elements = PsiElementFactory.parseText(new String(bytes), 0, null);
    }

    /**
     * Returns all children, fully nested
     *
     * @return a list of elements
     */
    public List<PsiElement> allChildren() {
        List<PsiElement> elements = new ArrayList<>();

        for (PsiElement child : this.elements) {
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

    /**
     * Parses the new text again and updates the elements
     *
     * @param text the new text
     */
    public void parse(String text) {
        this.elements = PsiElementFactory.parseText(text, 0, null);
    }
}
