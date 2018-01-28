package com.gmail.stefvanschiedev.bfide.application.stage;

import com.gmail.stefvanschiedev.bfide.Directory;
import com.gmail.stefvanschiedev.bfide.Project;
import com.gmail.stefvanschiedev.bfide.application.FXMLUtils;
import com.gmail.stefvanschiedev.bfide.application.menu.FileMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.HelpMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.ViewMenu;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Main stage for BFIDE
 */
public class StartStage extends Stage {

    @FXML private MenuBar menuBar;
    @FXML private TreeView<File> treeView;

    private List<Project> openProjects = new ArrayList<>();

    public StartStage() throws IOException {
        setTitle("BrainFuck IDE");
        setScene(new Scene(FXMLUtils.loadFXML("start", this)));
        //logo by rubbaboy
        getIcons().add(new Image(StartStage.class.getResourceAsStream("/icons/logo.png")));
    }

    @FXML
    public void initialize() {
        //load menu bar
        menuBar.getMenus().add(new FileMenu(this));
        menuBar.getMenus().add(new ViewMenu(this));
        menuBar.getMenus().add(new HelpMenu());

        //make sure the file names are displayed, not the file
        treeView.setCellFactory(param -> new TreeCell<File>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);

                setText(empty || item == null ? "" : item.getName());
            }
        });

        treeView.setRoot(new TreeItem<>());
        treeView.setShowRoot(false);
    }

    /**
     * Updates the file tree completely
     */
    public void updateFileTree() {
        treeView.getRoot().getChildren().clear();

        for (Project project : openProjects) {
            TreeItem<File> treeItem = new TreeItem<>(project);
            treeView.getRoot().getChildren().add(treeItem);

            for (File file : project.getChildren())
                updateFileTree(file, treeItem);
        }
    }

    /**
     * Updates the file tree from the given file and item
     */
    private void updateFileTree(File file, TreeItem<File> parent) {
        TreeItem<File> item = new TreeItem<>(file);
        parent.getChildren().add(item);

        if (file instanceof Directory) {
            for (File child : ((Directory) file).getChildren())
                updateFileTree(child, item);
        }
    }

    public List<Project> getOpenProjects() {
        return openProjects;
    }
}