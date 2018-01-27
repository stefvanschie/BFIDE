package com.gmail.stefvanschiedev.bfide.application;

import com.gmail.stefvanschiedev.bfide.application.stage.StartStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class
 */
public class BFIDE extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new StartStage().show();
    }
}