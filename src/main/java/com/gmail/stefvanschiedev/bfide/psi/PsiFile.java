package com.gmail.stefvanschiedev.bfide.psi;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Represents a BrainFuck file which belongs to the PSI
 */
public class PsiFile extends File {

    private PsiElement[] elements;

    public PsiFile(@NotNull File file) {
        super(file.getPath());

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        elements = PsiElementFactory.parseText(new String(bytes), 0, null);
    }
}
