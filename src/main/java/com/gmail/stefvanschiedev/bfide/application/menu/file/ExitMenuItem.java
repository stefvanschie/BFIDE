package com.gmail.stefvanschiedev.bfide.application.menu.file;

import javafx.application.Platform;
import javafx.scene.control.MenuItem;

/**
 * Exits the application
 */
public class ExitMenuItem extends MenuItem {

    public ExitMenuItem() {
        super("Exit");

        setOnAction(event -> Platform.exit());
    }
}