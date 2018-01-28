package com.gmail.stefvanschiedev.bfide.file;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * A project created in BFIDE, which contains files and directories
 */
public class Project extends Directory {

    @NotNull private String name;

    private Project(File file, @NotNull String name) {
        super(file);

        this.name = name;
    }

    /**
     * Creates a new project from scratch
     *
     * @return the new project
     */
    @Nullable
    @Contract(pure = true)
    public static Project create(File parent, @NotNull String name) {
        File directory = new File(parent, name);
        if (!directory.mkdirs())
            return null;

        //a marker that this is a bfide project
        File bfideDirectory = new File(directory, ".bfide");
        if (!bfideDirectory.mkdirs())
            return null;

        return new Project(directory, name);
    }
}