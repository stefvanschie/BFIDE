package com.gmail.stefvanschiedev.bfide.application.stage;

import com.gmail.stefvanschiedev.bfide.application.CodeTab;
import com.gmail.stefvanschiedev.bfide.file.Directory;
import com.gmail.stefvanschiedev.bfide.file.Project;
import com.gmail.stefvanschiedev.bfide.application.util.FXMLUtils;
import com.gmail.stefvanschiedev.bfide.application.menu.FileMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.HelpMenu;
import com.gmail.stefvanschiedev.bfide.application.menu.ViewMenu;
import com.gmail.stefvanschiedev.bfide.file.PsiFile;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main stage for BFIDE
 */
public class MainStage extends Stage {

    @FXML private MenuBar menuBar;
    @FXML private TreeView<File> treeView;
    @FXML private TabPane tabPane;

    private final List<Project> openProjects = new ArrayList<>();

    public MainStage() throws IOException {
        setTitle("BrainFuck IDE");

        Scene scene = new Scene(FXMLUtils.loadFXML("stage/main", this));
        scene.getStylesheets().add(MainStage.class.getResource("/highlighting.css").toExternalForm());
        setScene(scene);

        //logo by rubbaboy
        getIcons().add(new Image(MainStage.class.getResourceAsStream("/icons/logo.png")));
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

        treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() != 2)
                return;

            Node node = event.getPickResult().getIntersectedNode();
            // Accept clicks only on node cells, and not on empty spaces of the TreeView
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                File file = treeView.getSelectionModel().getSelectedItem().getValue();

                if (file instanceof PsiFile)
                    openFile((PsiFile) file);
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

        if (!(file instanceof Directory))
            return;

        for (File child : ((Directory)file).getChildren())
            updateFileTree(child, item);
    }

    public void openFile(PsiFile file) {
        tabPane.getTabs().add(new CodeTab(file));
    }

    public List<Project> getOpenProjects() {
        return openProjects;
    }
}
