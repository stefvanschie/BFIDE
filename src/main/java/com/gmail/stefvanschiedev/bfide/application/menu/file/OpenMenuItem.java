package com.gmail.stefvanschiedev.bfide.application.menu.file;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import com.gmail.stefvanschiedev.bfide.application.util.AlertBuilder;
import com.gmail.stefvanschiedev.bfide.application.util.DirectoryChooserBuilder;
import com.gmail.stefvanschiedev.bfide.file.Project;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

import java.io.File;

/**
 * Opens a project in the IDE
 */
public class OpenMenuItem extends MenuItem {

    public OpenMenuItem(MainStage stage) {
        super("Open");

        setOnAction(event -> {
            File directory = new DirectoryChooserBuilder()
                    .setTitle("Open project")
                    .showDialog(null);

            if (directory == null)
                return; //dialog was cancelled

            Project project = Project.load(directory);
            if (project == null) {
                new AlertBuilder()
                        .setType(Alert.AlertType.ERROR)
                        .setTitle("Incorrect directory")
                        .setHeaderText(null)
                        .setContextText("Directory isn't a project")
                        .showAndWait();
                return;
            }

            stage.getOpenProjects().add(project);
            stage.updateFileTree();
        });
    }
}
