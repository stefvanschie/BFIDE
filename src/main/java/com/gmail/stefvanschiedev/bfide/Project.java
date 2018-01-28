package com.gmail.stefvanschiedev.bfide;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * A project created in BFIDE, which contains files and directories
 */
public class Project extends Directory {

    @NotNull private String name;

    public Project(@NotNull String name, File file) {
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
    public static Project create(File parent, String name) {
        File directory = new File(parent, name);
        if (!directory.mkdirs())
            return null;

        //a marker that this is a bfide project
        File bfideDirectory = new File(directory, ".bfide");
        if (!bfideDirectory.mkdirs())
            return null;

        return new Project(name, directory);
    }
}