package com.gmail.stefvanschiedev.bfide.file;

import com.gmail.stefvanschiedev.bfide.psi.PsiElement;
import com.gmail.stefvanschiedev.bfide.psi.PsiElementFactory;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    @Nullable private PsiElement[] elements = null;

    private PsiFile(File file) {
        super(file.getPath());
    }

    /**
     * @return the parsed contents on this file or null, if the contents of the file cannot be read
     */
    @Nullable
    public PsiElement[] getElements() {
        if (elements != null)
            return elements;

        try {
            elements = PsiElementFactory.parseText(new String(Files.readAllBytes(toPath())), 0, null);
            return elements;
        } catch (IOException e) {
            return null;
        }
    }
}
