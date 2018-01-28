package com.gmail.stefvanschiedev.bfide.application.menu.file.newmenu;

import com.gmail.stefvanschiedev.bfide.application.stage.MainStage;
import com.gmail.stefvanschiedev.bfide.application.util.AlertBuilder;
import com.gmail.stefvanschiedev.bfide.application.util.DirectoryChooserBuilder;
import com.gmail.stefvanschiedev.bfide.application.util.FXMLUtils;
import com.gmail.stefvanschiedev.bfide.file.Project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
                alert = new AlertBuilder()
                        .setType(Alert.AlertType.INFORMATION)
                        .setTitle("New Project")
                        .setHeaderText(null)
                        .build();

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
                    new AlertBuilder()
                            .setType(Alert.AlertType.ERROR)
                            .setTitle("Error")
                            .setHeaderText(null)
                            .setContextText("Unable to create project")
                            .showAndWait();
                    return;
                }

                stage.getOpenProjects().add(project);
                stage.updateFileTree();
            }

            @FXML
            public void initialize() {
                projectLocationButton.setOnMouseClicked(event -> {
                    File selectedDirectory = new DirectoryChooserBuilder()
                            .setTitle("Select Project Directory")
                            .showDialog(alert.getOwner());

                    if (selectedDirectory == null)
                        return; //dialog was cancelled

                    projectLocation.setText(selectedDirectory.getPath());
                });
            }
        });
    }
}
