package com.gmail.stefvanschiedev.bfide.file;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * A project created in BFIDE, which contains files and directories
 */
public class Project extends Directory {

    /**
     * Creates a new project from scratch
     *
     * @param parent the directory in which the project folder should be created
     * @param name the name of the project
     * @return the new project or null, if an error occurs
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

    /**
     * Loads an existing project
     *
     * @param directory the project's root directory
     * @return the newly loaded project or null, if the directory is not a valid project folder
     */
    @Nullable
    @Contract(pure = true)
    public static Project load(File directory) {
        if (!new File(directory, ".bfide").exists())
            return null;

        return new Project(directory, directory.getName());
    }

    @NotNull private String name;

    private Project(File file, @NotNull String name) {
        super(file);

        this.name = name;
    }
}