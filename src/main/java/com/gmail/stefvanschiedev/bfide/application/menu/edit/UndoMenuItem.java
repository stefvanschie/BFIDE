package com.gmail.stefvanschiedev.bfide.application.menu.edit;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.MenuItem;

/**
 * Undoes the latest change
 */
public class UndoMenuItem extends MenuItem {

    public UndoMenuItem(MainStage stage) {
        super("Undo");

        setOnAction(event -> stage.getSelectedCodeTab().getCodeArea().undo());
    }
}
