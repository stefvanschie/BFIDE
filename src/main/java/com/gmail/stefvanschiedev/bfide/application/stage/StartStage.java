package com.gmail.stefvanschiedev.bfide.application.stage;

import com.gmail.stefvanschiedev.bfide.application.FXMLUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main stage for BFIDE
 */
public class StartStage extends Stage {

    public StartStage() throws IOException {
        setTitle("BrainFuck IDE");
        setScene(new Scene(FXMLUtils.loadFXML("start", this)));
        //logo by rubbaboy
        getIcons().add(new Image(StartStage.class.getResourceAsStream("/icons/logo.png")));
    }
}