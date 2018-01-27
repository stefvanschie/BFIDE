package com.gmail.stefvanschiedev.bfide.application.menu;

import com.gmail.stefvanschiedev.bfide.application.menu.help.SubmitFeedbackMenuItem;
import javafx.scene.control.Menu;

/**
 * The help menu
 */
public class HelpMenu extends Menu {

    public HelpMenu() {
        super("Help");

        getItems().add(new SubmitFeedbackMenuItem());
    }
}
