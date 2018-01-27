package com.gmail.stefvanschiedev.bfide.application.menu.view;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Enters or exits full screen mode
 */
public class EnterFullScreenMenuItem extends MenuItem {

    public EnterFullScreenMenuItem(Stage stage) {
        super("Enter Full Screen");

        setOnAction(event -> {
            stage.setFullScreen(!stage.isFullScreen());

            if (stage.isFullScreen())
                setText("Exit Full Screen");
            else
                setText("Enter Full Screen");
        });
    }
}
