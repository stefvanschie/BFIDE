package com.gmail.stefvanschiedev.bfide.application.menu.edit;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.MenuItem;

/**
 * Redoes the latest change
 */
public class RedoMenuItem extends MenuItem {

    public RedoMenuItem(MainStage stage) {
        super("Redo");

        setOnAction(event -> stage.getSelectedCodeTab().getCodeArea().redo());
    }
}
