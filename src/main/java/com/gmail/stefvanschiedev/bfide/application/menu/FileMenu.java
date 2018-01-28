package com.gmail.stefvanschiedev.bfide.application.menu;

import com.gmail.stefvanschiedev.bfide.application.menu.file.ExitMenuItem;
import com.gmail.stefvanschiedev.bfide.application.menu.file.NewMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.file.OpenMenuItem;
import com.gmail.stefvanschiedev.bfide.application.stage.StartStage;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

/**
 * The file menu
 */
public class FileMenu extends Menu {

    public FileMenu(StartStage stage) {
        super("File");

        getItems().add(new NewMenu(stage));
        getItems().add(new OpenMenuItem(stage));

        getItems().add(new SeparatorMenuItem());

        getItems().add(new ExitMenuItem());
    }
}