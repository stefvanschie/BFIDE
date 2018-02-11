package com.gmail.stefvanschiedev.bfide.application.menu.edit;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.MenuItem;

/**
 * Pastes the selected text
 */
public class PasteMenuItem extends MenuItem {

    public PasteMenuItem(MainStage stage) {
        super("Paste");

        setOnAction(event -> stage.getSelectedCodeTab().getCodeArea().paste());
    }
}
