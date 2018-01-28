package com.gmail.stefvanschiedev.bfide.file;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file tree
 */
public class Directory extends File {

    /**
     * Loads an existing directory
     *
     * @param directory the directory to load
     */
    @Nullable
    @Contract(pure = true)
    public static Directory load(File directory) {
        if (!directory.isDirectory())
            return null;

        return new Directory(directory);
    }

    private final List<File> children = new ArrayList<>();

    protected Directory(File directory) {
        super(directory.getPath());

        File[] childrenFiles = directory.listFiles();
        if (childrenFiles == null)
            return;

        for (File f : childrenFiles) {
            //we don't want to show this folder in the tree
            if (f.getName().equals(".bfide"))
                continue;

            File toAdd = null;
            if (f.isDirectory())
                toAdd = Directory.load(f);
            else if (f.getName().endsWith(".b") || f.getName().endsWith(".bf"))
                toAdd = PsiFile.load(f);

            children.add(toAdd == null ? f : toAdd);
        }
    }

    public List<File> getChildren() {
        return children;
    }
}
