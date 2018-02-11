package com.gmail.stefvanschiedev.bfide.application.menu.edit;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.MenuItem;
import org.fxmisc.richtext.CodeArea;

/**
 * Deletes the selected text
 */
public class DeleteMenuItem extends MenuItem {

    public DeleteMenuItem(MainStage stage) {
        super("Delete");

        setOnAction(event -> {
            CodeArea codeArea = stage.getSelectedCodeTab().getCodeArea();

            codeArea.deleteText(codeArea.getSelection());
        });
    }
}
