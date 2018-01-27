package com.gmail.stefvanschiedev.bfide.application.menu;

import com.gmail.stefvanschiedev.bfide.application.menu.file.ExitMenuItem;
import javafx.scene.control.Menu;

/**
 * The file menu
 */
public class FileMenu extends Menu {

    public FileMenu() {
        super("File");

        getItems().add(new ExitMenuItem());
    }
}