package com.gmail.stefvanschiedev.bfide.application.menu.file;

import com.gmail.stefvanschiedev.bfide.Project;
import com.gmail.stefvanschiedev.bfide.application.stage.StartStage;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * Opens a project in the IDE
 */
public class OpenMenuItem extends MenuItem {

    public OpenMenuItem(StartStage stage) {
        super("Open");

        setOnAction(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Open project");
            File directory = chooser.showDialog(null);

            if (!new File(directory, ".bfide").exists()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect directory");
                alert.setHeaderText(null);
                alert.setContentText("Directory isn't a project");
                alert.showAndWait();
                return;
            }

            stage.getOpenProjects().add(new Project(directory.getName(), directory));
            stage.updateFileTree();
        });
    }
}
