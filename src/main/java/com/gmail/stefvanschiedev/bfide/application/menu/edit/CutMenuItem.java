package com.gmail.stefvanschiedev.bfide.application.menu.edit;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.MenuItem;

/**
 * Cuts the selected text
 */
public class CutMenuItem extends MenuItem {

    public CutMenuItem(MainStage stage) {
        super("Cut");

        setOnAction(event -> stage.getSelectedCodeTab().getCodeArea().cut());
    }
}
