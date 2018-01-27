package com.gmail.stefvanschiedev.bfide.application.menu;

import com.gmail.stefvanschiedev.bfide.application.menu.view.EnterFullScreenMenuItem;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

/**
 * The view menu
 */
public class ViewMenu extends Menu {

    public ViewMenu(Stage stage) {
        super("View");

        getItems().add(new EnterFullScreenMenuItem(stage));
    }
}