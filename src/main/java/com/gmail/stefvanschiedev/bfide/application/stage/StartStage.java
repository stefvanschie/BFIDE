package com.gmail.stefvanschiedev.bfide.application.stage;

import com.gmail.stefvanschiedev.bfide.application.FXMLUtils;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartStage extends Stage {
    public StartStage() throws IOException {
        setTitle("BrainFuck IDE");
        setScene(new Scene(FXMLUtils.loadFXML("start", this)));
    }
}