package com.gmail.stefvanschiedev.bfide.application.menu;

import com.gmail.stefvanschiedev.bfide.application.menu.edit.*;
import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

/**
 * The edit menu
 */
public class EditMenu extends Menu {

    public EditMenu(MainStage stage) {
        super("Edit");

        getItems().add(new UndoMenuItem(stage));
        getItems().add(new RedoMenuItem(stage));

        getItems().add(new SeparatorMenuItem());

        getItems().add(new CutMenuItem(stage));
        getItems().add(new CopyMenuItem(stage));
        getItems().add(new PasteMenuItem(stage));
        getItems().add(new DeleteMenuItem(stage));
    }
}
