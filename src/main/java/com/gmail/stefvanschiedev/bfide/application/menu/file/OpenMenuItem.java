package com.gmail.stefvanschiedev.bfide.application.menu.file;

import com.gmail.stefvanschiedev.bfide.file.Project;
import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * Opens a project in the IDE
 */
public class OpenMenuItem extends MenuItem {

    public OpenMenuItem(MainStage stage) {
        super("Open");

        setOnAction(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Open project");
            File directory = chooser.showDialog(null);

            if (directory == null)
                return; //dialog was cancelled

            if (!new File(directory, ".bfide").exists()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect directory");
                alert.setHeaderText(null);
                alert.setContentText("Directory isn't a project");
                alert.showAndWait();
                return;
            }

            Project project = Project.create(directory, directory.getName());
            if (project == null) {
                //TODO handle
            }

            stage.getOpenProjects().add(project);
            stage.updateFileTree();
        });
    }
}
