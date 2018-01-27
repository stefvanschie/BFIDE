package com.gmail.stefvanschiedev.bfide;

import java.io.File;

/**
 * A project created in BFIDE, which contains files and directories
 */
public class Project extends Directory {

    private String name;

    public Project(String name, File file) {
        super(file);

        this.name = name;
    }
}