package com.gmail.stefvanschiedev.bfide.application;

import javafx.fxml.FXMLLoader;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class FXMLUtils {
    /**
     * @param name the name of the file to load (without the fxml extension) (path separator: '/')
     * @param controller the controller of the hierarchy being loaded or null, if none should be set
     * @param <T> the type of the hierarchy being loaded
     * @return the newly loaded hierarchy
     * @throws IOException in case the loading fails
     */
    public static <T> T loadFXML(String name, @Nullable Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(BFIDE.class.getResource("/fxml/" + name + ".fxml"));
        loader.setControllerFactory((controllerClass) -> controller);
        return loader.load();
    }
}