package com.gmail.stefvanschiedev.bfide.application.stage;

import com.gmail.stefvanschiedev.bfide.application.FXMLUtils;
import com.gmail.stefvanschiedev.bfide.application.menu.FileMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.HelpMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.ViewMenu;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main stage for BFIDE
 */
public class StartStage extends Stage {

    @FXML private MenuBar menuBar;

    public StartStage() throws IOException {
        setTitle("BrainFuck IDE");
        setScene(new Scene(FXMLUtils.loadFXML("start", this)));
        //logo by rubbaboy
        getIcons().add(new Image(StartStage.class.getResourceAsStream("/icons/logo.png")));
    }

    @FXML
    public void initialize() {
        //load menu bar
        menuBar.getMenus().add(new FileMenu());
        menuBar.getMenus().add(new ViewMenu(this));
        menuBar.getMenus().add(new HelpMenu());
    }
}