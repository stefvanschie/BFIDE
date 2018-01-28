package com.gmail.stefvanschiedev.bfide.application.menu.file;

import com.gmail.stefvanschiedev.bfide.application.menu.file.newmenu.ProjectMenuItem;
import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.Menu;

/**
 * The new menu
 */
public class NewMenu extends Menu {

    public NewMenu(MainStage stage) {
        super("New");

        getItems().add(new ProjectMenuItem(stage));
    }
}