package com.gmail.stefvanschiedev.bfide.application.menu.file.newmenu;

import com.gmail.stefvanschiedev.bfide.file.Project;
import com.gmail.stefvanschiedev.bfide.application.util.FXMLUtils;
import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Allows you to create a new project from scratch
 */
public class ProjectMenuItem extends MenuItem {

    public ProjectMenuItem(MainStage stage) {
        super("Project");

        setOnAction(new EventHandler<ActionEvent>() {
            @FXML private TextField projectName;
            @FXML private TextField projectLocation;
            @FXML private Button projectLocationButton;

            private Alert alert;

            @Override
            public void handle(ActionEvent event) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("New Project");
                alert.setHeaderText(null);

                try {
                    alert.setDialogPane(FXMLUtils.loadFXML("alerts/newproject", this));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Optional<ButtonType> buttonType = alert.showAndWait();

                if (!buttonType.isPresent() || buttonType.get() != ButtonType.FINISH)
                    return;

                Project project = Project.create(new File(projectLocation.getText()), projectName.getText());
                if (project == null) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setHeaderText(null);
                    error.setContentText("Unable to create project");
                    error.showAndWait();
                    return;
                }

                stage.getOpenProjects().add(project);
                stage.updateFileTree();
            }

            @FXML
            public void initialize() {
                projectLocationButton.setOnMouseClicked(event -> {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle("Select project directory");
                    File selectedDirectory = directoryChooser.showDialog(alert.getOwner());

                    if (selectedDirectory == null)
                        return; //dialog was cancelled

                    projectLocation.setText(selectedDirectory.getPath());
                });
            }
        });
    }
}
