package com.gmail.stefvanschiedev.bfide.application.menu.help;

import javafx.scene.control.MenuItem;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Redirects you to the GitHub repository of this project
 */
public class SubmitFeedbackMenuItem extends MenuItem {

    private static URI uri = null;

    public SubmitFeedbackMenuItem() {
        super("Submit Feedback");

        setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    static {
        try {
            uri = new URL("https://github.com/stefvanschie/BFIDE").toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
