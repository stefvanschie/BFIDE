package com.gmail.stefvanschiedev.bfide;

import com.gmail.stefvanschiedev.bfide.psi.PsiFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a directory in the file tree
 */
public class Directory extends File {

    private final List<File> children = new ArrayList<>();

    public Directory(File file) {
        super(file.getPath());

        File[] childrenFiles = file.listFiles();
        if (childrenFiles == null)
            return;

        for (File f : childrenFiles) {
            //we don't want to show this folder in the tree
            if (f.getName().equals(".bfide"))
                continue;

            if (f.isDirectory())
                children.add(new Directory(f));
            else if (f.getName().endsWith(".b") || f.getName().endsWith(".bf"))
                children.add(new PsiFile(f));
            else
                children.add(f);
        }
    }

    public List<File> getChildren() {
        return children;
    }
}
