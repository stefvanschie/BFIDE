package com.gmail.stefvanschiedev.bfide.application;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
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
        //TODO use the primaryStage instead if there won't be any stages except MainStage
        new MainStage().show();
    }
}