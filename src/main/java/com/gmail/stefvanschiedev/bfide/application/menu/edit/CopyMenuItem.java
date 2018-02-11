package com.gmail.stefvanschiedev.bfide.application.menu.edit;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.MenuItem;

/**
 * Copies the selected text
 */
public class CopyMenuItem extends MenuItem {

    public CopyMenuItem(MainStage stage) {
        super("Copy");

        setOnAction(event -> stage.getSelectedCodeTab().getCodeArea().copy());
    }
}
