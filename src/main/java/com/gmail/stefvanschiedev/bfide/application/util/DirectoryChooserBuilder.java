package com.gmail.stefvanschiedev.bfide.application.util;

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * A builder for JavaFX directory choosers
 */
public class DirectoryChooserBuilder {
    private final DirectoryChooser chooser = new DirectoryChooser();

    public File showDialog(@Nullable Window ownerWindow) {
        return chooser.showDialog(ownerWindow);
    }

    public DirectoryChooser build() {
        return chooser;
    }

    public DirectoryChooserBuilder setInitialDirectory(File value) {
        chooser.setInitialDirectory(value);
        return this;
    }

    public DirectoryChooserBuilder setTitle(String value) {
        chooser.setTitle(value);
        return this;
    }
}
